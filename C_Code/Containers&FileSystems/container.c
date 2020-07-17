#define _GNU_SOURCE
#include <sys/wait.h>
#include <errno.h>
#include <grp.h>
#include <sys/capability.h>
#include <sys/utsname.h>
#include <sched.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/mount.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <limits.h>
#include <dirent.h>
#include <sys/prctl.h>
#include <sys/mman.h>

/* A simple error-handling function: print an error message based
   on the value in 'errno' and terminate the calling process */

#define errExit(msg)    do { perror(msg); exit(EXIT_FAILURE); \
                        } while (0)
pid_t globalPID;

const int u = 65534;
struct child_args
{
    char **argv;        /* Command to be executed by child, with args */
    int    pipe_fd[2];  /* Pipe used to synchronize parent and child */
};

struct params
{
	char clone_name[30];  //for additional mount point
//	char **argv;
	int cpu_pct;
	int mem_limit;
	int num_levels;
};


static void update_map(char *mapping, char *map_file)
{
  int fd, j;
  size_t map_len;     /* Length of 'mapping' */

  /* Replace commas in mapping string with newlines */
  map_len = strlen(mapping);
   for (j = 0; j < map_len; j++)
     if (mapping[j] == ',')
         mapping[j] = '\n';

  fd = open(map_file, O_RDWR);
      if (fd == -1) {
          errExit("mapfile open");
          exit(EXIT_FAILURE);
      }
      if (write(fd, mapping, map_len) != map_len) {
          errExit("mapfile write");
          exit(EXIT_FAILURE);
      }
   close(fd);
}



static void proc_setgroups_write(pid_t child_pid, char *str)
       {   
           setuid(0);
           char setgroups_path[PATH_MAX];
           int fd;

           snprintf(setgroups_path, PATH_MAX, "/proc/%ld/setgroups",
                   (long) child_pid);

           fd = open(setgroups_path, O_RDWR);
           if (fd == -1) {

               /* We may be on a system that doesn't support
                  /proc/PID/setgroups. In that case, the file won't exist,
                  and the system won't impose the restrictions that Linux 3.19
                  added. That's fine: we don't need to do anything in order
                  to permit 'gid_map' to be updated.

                  However, if the error from open() was something other than
                  the ENOENT error that is expected for that case,  let the
                  user know. */

               if (errno != ENOENT)
                   errExit("error open");;
                   exit(EXIT_FAILURE);
 }
           if (write(fd, str, strlen(str)) == -1)
               printf("ERROR: write %s: try again\n", setgroups_path);
           close(fd);
}

static void make_cpu_cgroup_dir(pid_t child_pid) {
  char path[PATH_MAX];
  printf("PID in make cpu dir: %d\n", child_pid);
  snprintf(path, PATH_MAX, "/sys/fs/cgroup/cpuacct/cgdemo/%d", child_pid);

  if (!access(path, F_OK))
    return;

  if (mkdir(path, S_IRWXU | S_IRGRP | S_IXGRP | S_IROTH | S_IXOTH))
    errExit("Could not create cgroup dir");
}

static int              /* Start function for cloned child */
childFunc(void *arg)
{
//    setpgid(0,0);
    char path[100];
    int ret;
    struct params *clone_namespace = arg;
    pid_t clone_pid = getpid();    
    printf("Clone PID: %d\n", clone_pid);
    printf("Parent PID: %d\n", getppid());

    /* Mount the proc */    
    char *mount_point = "/proc";
    if (mount_point != NULL); 
    {
	mkdir(mount_point, 0555);
	mount("proc", mount_point, "proc", 0, NULL);
	setgroups(0,NULL);
	printf("Making procfs at %s\n", mount_point);
	
    }
//    if (setpgid(0,0) == -1)
//	{
//	  errExit("setpgid");
//	}
//    if (tcsetpgrp(STDIN_FILENO, getpgrp()) == -1)
//	errExit("tcsetpgrp-child");

    /* Change hostname in UTS namespace of child */
	//was arg in both parameters
    if (sethostname((*clone_namespace).clone_name, strlen((*clone_namespace).clone_name)) == -1)
        errExit("sethostname");

    ret = setgid(u);
    setgroups(0,NULL);
    ret = setuid(u);

    /* Retrieve and display hostname */  
    printf("uts.nodename in child:  %s\n", (*clone_namespace).clone_name);
    /* Execute a shell */
    ret = execl("/bin/bash", "/bin/bash", NULL);
    return ret;           /* Terminates child */
}


static void death_handler (int sig) {
  char path[PATH_MAX];
  
  kill(globalPID, SIGKILL);
  /* if we don't wait for the child to
   * completely die here, cgroups won't let us remove
   * the subdirectories
   */
  waitpid(globalPID, NULL, 0);

  snprintf(path, PATH_MAX, "/sys/fs/cgroup/cpuacct/cgdemo/%d", globalPID);
  if (!access(path, F_OK))
    return;

  if (rmdir(path))
    errExit("Could not remove cgroup dir");
}

int cpu_pcct = 50;



static void write_cpu_cgroup_files(pid_t pid, int cpu_pcct) {
  char path[PATH_MAX];
  int fd;
  
  snprintf(path, PATH_MAX, "/sys/fs/cgroup/cpuacct/cgdemo/%d/cpu.cfs_quota_us", pid);

  if ((fd = open(path, O_WRONLY | O_TRUNC)) == -1)
    errExit("Can't open cpu.cfs_quota_us");

  dprintf(fd, "%d", cpu_pcct * 1000);
  close(fd);
    
  snprintf(path, PATH_MAX, "/sys/fs/cgroup/cpuacct/cgdemo/%d/tasks", pid);

  if ((fd = open(path, O_WRONLY | O_TRUNC)) == -1)
    errExit("Can't open tasks");

  dprintf(fd, "%d", pid);
  close(fd);  
}

#define STACK_SIZE (1024 * 1024)    /* Stack size for cloned child */

static char child_stack[STACK_SIZE];

int
main(int argc, char *argv[])
{
    pid_t pid;
    int flags, opt, map_zero;
    char *uid_map, *gid_map;
    const int MAP_BUF_SIZE = 100;
    char map_buf[MAP_BUF_SIZE];
    char map_path[PATH_MAX];
    struct params *params;
    struct child_args argsChld;

    if (argc < 2) {
        fprintf(stderr, "Usage: %s <child-hostname>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
     
    flags = CLONE_NEWUTS | CLONE_NEWUSER | CLONE_NEWNS | CLONE_NEWPID | CLONE_NEWIPC | CLONE_NEWNET |  SIGCHLD;
    gid_map = NULL;
    uid_map = NULL;
    map_zero = 0;
        
  //  void *child_stack_mmap =mmap(0, STACK_SIZE, PROT_EXEC | PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    
    argsChld.argv = &argv[1];
    if(pipe(argsChld.pipe_fd) == -1)
	errExit("pipe");

    pid = clone(childFunc, child_stack + STACK_SIZE, flags, argv[1]);
   if (pid == -1)
        errExit("clone");
    printf("PID of child created by clone() is %ld\n", (long) pid); 

/* Update the UID and GID maps in the child */
    if (uid_map != NULL || map_zero) {
               snprintf(map_path, PATH_MAX, "/proc/%ld/uid_map",
                       (long) pid);
               if (map_zero) {
                   snprintf(map_buf, MAP_BUF_SIZE, "0 %ld 1", (long) getuid());
                   uid_map = map_buf;
               }
               update_map(uid_map, map_path);
           }

           if (gid_map != NULL || map_zero) {
               proc_setgroups_write(pid, "deny");

               snprintf(map_path, PATH_MAX, "/proc/%ld/gid_map",
                       (long) pid);
               if (map_zero) {
                   snprintf(map_buf, MAP_BUF_SIZE, "0 %ld 1", (long) getgid());
                   gid_map = map_buf;
               }
               update_map(gid_map, map_path);
           }
    
    close(argsChld.pipe_fd[1]);
    make_cpu_cgroup_dir(pid);
    globalPID = pid; // put this in a static so sighandler can find it
    signal(SIGINT, death_handler);
    printf("param.clone_name in parent: %s\n", (*params).clone_name);
    if (waitpid(pid, NULL, 0) == -1)      /* Wait for child */
        errExit("waitpid");
    printf("child has terminated\n");

    exit(EXIT_SUCCESS);
}
