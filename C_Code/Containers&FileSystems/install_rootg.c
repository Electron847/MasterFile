#define _GNU_SOURCE
#include <errno.h>
#include <stdio.h>
#include <sched.h>
#include <stdlib.h>
#include <sys/mount.h>
#include <sys/stat.h>
#include <sys/syscall.h>
#include <unistd.h>
#include "img.h"

#define TRY(x) if (x) fatal_errno(__LINE__)

void fatal_errno(int line)
{
   printf("IR: error at line %d, errno=%d\n", line, errno);
   exit(1);
}

/* Note <dir> refers to an absolute path where rootfs is present. 
 * Remember the child invokes mounting new images. */

static int
pivot_root(const char *new_root, const char *put_old)
{
    return syscall(SYS_pivot_root, new_root, put_old);
}

int install_root(void)
{
    char **args = arg;
    char *new_root = args[0];
    const char *put_old = "/oldrootfs";
    char path[PATH_MAX];
    
    /*  Claim the image for mount namespace by recursively bind-mounting the parent of <dir> over*/
    int mountflags = MS_REC | MS_BIND | MS_PRIVATE;
    //mount(image source, image target, ext4, mountflags, data);
    if (mount(NULL, "/", MS_REC | MS_BIND | MS_PRIVATE) == 1)
        errExit("mountflags");

   /* Make a place for the old (intermediate) root filesystem to land. */
   /* mkdir <oldroot> under <dir>. Remember <dir> is now just directly under '/' */
    
    /* You can write this mkdir as part of an if condition, and
    * check if there is error in creating a directory with EEXIST
    * Error numbers can be printed with the TRY ststement */
    
    snprintf(path, sizeof(path), "%s/%s", new_root, put_old);
    if (mkdir(path, 0777) == -1)
        errExit("mkdir");
    
    /* Pivot root on <dir> and put old root under <oldroot>*/
    if (pivot_root(new_root, path) == -1)
        errExit("pivot_root");

   /* Finally, chdir to <dir> which is our "real" newroot into the root filesystem. */
    if (chdir("/") == -1)
        errExit("chdir");

   /* Pivot root on <dir> and put old root under <oldroot>*/
    
    /* Unmount the old filesystem and it's gone for good. Use MNT_DETACH*/
   if (umount2(put_old, MNT_DETACH) == -1)
       perror("umount2");
   if (rmdir(put_old) == -1)
       perror("rmdir");

   /* Report success. */
   printf("ok\n");
   return 0;
}
