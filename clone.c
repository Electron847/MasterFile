#include<stdio.h>
#include<sched.h>
#includ<stdlib.h>
#define STACKSIZE (1024 * 1024)

int child_proc(void *argc)
{
   printf("child pid %d\n",getpid());
   printf("child process arg = %s\n", (char *)arg);
   sleep(10);
   return 0;
}



int main(int argc, char *argv[])
{
  
  pid_t pid;
  int status;
  char *str = "hello world\n";
  char *stack;
  char *stackhead;
  stack = (char *)malloc(STACKSIZE);
  stackhead = stack + STACKSIZE;
  pid = clone(child_proc, stackhead, SIGCHLD, str);
  printf("parent pid %d\n", getpid()); 
  if(pid == -1) 
  {
   fprintf(stderr, "unable to clone\n";
   free(stack);
   exit(1);
  }
  waitpid(pid, &status,0);

  return 0;
}

