#define _GNU_SOURCE
#include <sched.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/mount.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>
#include <signal.h>
#include <stdio.h>

/* A simple error-handling function: print an error message based
   on the value in 'errno' and terminate the calling process */

#define errExit(msg)    do { perror(msg); exit(EXIT_FAILURE); \
                        } while (0)

static int              /* Start function for cloned child */
childFunc(void *arg)
{
    printf("childFunc(): PID  = %ld\n", (long) getpid());
    printf("childFunc(): PPID = %ld\n", (long) getppid());

    char *mount_point = arg;
    //char *mount_point = "/proc//"

    if (mount_point != NULL) {
        mkdir(mount_point, 0555);       /* Create directory for mount point */
        printf("Mounting procfs at %s\n", mount_point);
        if (mount("proc", mount_point, "proc", 0, NULL) == -1)
            errExit("mount");
        //printf("Mounting procfs at %s\n", mount_point);
    }

    //execlp("bash", "bash", "-c", (char *) NULL);
    //sleep(600);
    //execlp("sleep", "sleep", "600", (char *) NULL);
    execlp("/bin/sh","/bin/sh", "-c", "ls -l /proc/1", (char *)NULL);
    errExit("execlp");  /* Only reached if execlp() fails */
}

#define STACK_SIZE (1024 * 1024)

static char child_stack[STACK_SIZE];    /* Space for child's stack */

int
main(int argc, char *argv[])
{
    pid_t child_pid;

    child_pid = clone(childFunc,
                    child_stack + STACK_SIZE,   /* Points to start of
                                                   downwardly growing stack */
                    CLONE_NEWPID | SIGCHLD, argv[1]);

    if (child_pid == -1)
        errExit("clone");

    printf("PID returned by clone(): %ld\n", (long) child_pid);

    if (waitpid(child_pid, NULL, 0) == -1)      /* Wait for child */
        errExit("waitpid");

    exit(EXIT_SUCCESS);
}
