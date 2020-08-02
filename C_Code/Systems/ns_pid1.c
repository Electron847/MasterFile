#define _GNU_SOURCE
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mount.h>
#include <grp.h>
#include <alloca.h>
#include <errno.h>
#include <sched.h>

#define errExit(msg)    do { perror(msg); exit(EXIT_FAILURE); \
} while (0)

static int callee();
const int u = 65534;

int main(int argc, char *argv[])
{
	int r;
	pid_t mypid;
    struct utsname uts;
    void *mem = 1024 * 1024;
	mypid = clone(callee, mem, SIGCHLD | CLONE_NEWIPC | CLONE_NEWPID | CLONE_NEWNS | CLONE_FILES, NULL);
	while (waitpid(mypid, &r, 0) < 0 && errno == EINTR)
	{
		continue;
	}
	if (WIFEXITED(r))
	{
		return WEXITSTATUS(r);
	}
	return EXIT_FAILURE;
}
static int callee()
{
    struct utsname uts;
	int ret;
    
    if (sethostname(arg, strlen(arg)) == -1)
           errExit("sethostname");
	mount("proc", "/proc", "proc", 0, "");
	ret = setgid(u);
	setgroups(0, NULL);
	ret = setuid(u);
	ret = execl("/bin/bash", "/bin/bash", NULL);
	return ret;
}
