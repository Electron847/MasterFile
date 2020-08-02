#include<unistd.h>
#include<stdio.h>

int main(int  argc, char **argv) {
char *name = argv[0];
int  child_pid = fork();

if (child_pid == 0) {
printf("Child of %s sees PID of %d\n", name, child_pid);
return 0;
} else {
printf("I am the parent %s. My child is %d\n", name, child_pid);
return 0;
}
}
