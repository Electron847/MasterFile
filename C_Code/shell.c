/**
* -------------- Seth Weber's Linux Shell ----------------
* --------------- CSC 595 May 10, 2020 -------------------
* -------------------------------------------------------- 
* --------------- acknowledged sources -------------------
* --------------------------------------------------------
* --- https://gist.github.com/tam5/be8e818d4c77dc480451 --
* --------------------------------------------------------
* --- https://github.com/brenns10/lsh/blob/407938170e8b 
* --- 40d231781576e05282a41634848c/src/main.c           
* --------------------------------------------------------
* --------------------------------------------------------
*/

#include <signal.h>
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <ctype.h>
#include <stdlib.h>
#define MAX_LINE 1024 

//global variables
FILE *pFile;
char myBuf[256];
int runFlag = 1;  // flag to determine exit
int waitFlag = 1; // flag to determine if process should run in background

void handler(int signum) //signal handler executable
{
  write(STDOUT_FILENO, "Continuing shell   \n", 21);
}

/**
 * equivalent to ' < '
 */
void redirectSTDIN(char *file_to_read)
{
    int stdinNewHome = open(file_to_read, O_RDONLY); //open a file in read only
    dup2(stdinNewHome, 0); //duplicate the stdin file descriptor 
    close(stdinNewHome);   //close the file descriptor

}

/**
 * equivalent to 'command > [file_to_write].txt/log
 */
void redirectSTDOUT(char *file_to_write)
{
    int outNewHome = open(file_to_write, O_WRONLY | O_TRUNC | O_CREAT, 0666);
    dup2(outNewHome, 1);
    close(outNewHome);
}

/**
 * runs arguments from  
 */
void run(char *args[])
{
    int fd[2], nbytes;
    pid_t pid, pid2;
    char string[256];
    pipe(fd);
    
    if (strcmp(args[0], "exit") != 0)
        {   
	    printf("%s\n", args[0]);
            pFile = fopen("logfile.txt", "a+");
            pid = fork();
            if (pid < 0) { 
                fprintf(stderr, "Fork Failed");
            }
            else if ( pid == 0) { /* child process */
		int myPID = getpid();
		printf("Process ID: %d\n", getpid());
		dup2(fileno(pFile), STDOUT_FILENO);
                execvp(args[0], args);         
		exit(0);
            }
            else { /* parent process */
		pid2 = fork(); //create a child and parent process dichotomy
		if (pid2 == 0) //child process
		  { dup2(STDOUT_FILENO,1); //duplicates stdout to display
		    execvp(args[0],args); //executes the commandline argument
		  }
  		else /*parent process 2 */
		  {waitpid(pid2, NULL,0);} //waitpid waits for pid2
                if (waitFlag) { //if waitFlag is true
                    waitpid(pid, NULL, 0); //waitpid waits for pid
                } else {
                    waitFlag = 0; //sets waitFlag to false, ends waiting 
                }
            } 	   
            redirectSTDIN("/dev/tty"); //redirects stdin of current process
            redirectSTDOUT("/dev/tty"); //redirects stdout of current process
        }
    else {
        runFlag = 0;
    }
}

/**
 * function that allows ' | '
 * by shell
 */

void pipeTime(char *args[])
{
    int fd[2];
    pipe(fd);
    dup2(fd[1], 1);
    close(fd[1]);
    printf("args = %s\n", *args);
    run(args);
    dup2(fd[0], 0);
    close(fd[0]);
}

/**
 * Tokenizes terminal user input
 * Allows for easier parsing 
 */

char * tokenize(char *input)
{
    int x;	
    int y = 0;
    char *tokenized = (char *)malloc((MAX_LINE * 2) * sizeof(char));

    for (x = 0; x < strlen(input); x++) {
        if (input[x] != '>' && input[x] != '<' && input[x] != '|') {
            tokenized[y++] = input[x];
        } else {
            tokenized[y++] = ' ';
            tokenized[y++] = input[x];
            tokenized[y++] = ' ';
        }
    }
    tokenized[y++] = '\0';
    char *end_of_line;
    end_of_line = tokenized + strlen(tokenized) - 1;
    end_of_line--;
    *(end_of_line + 1) = '\0';

    return tokenized;
}

int main(void)
{

    struct sigaction action;
    action.sa_handler = handler;
    action.sa_flags = SA_RESTART;
    sigaction(SIGINT, &action, NULL);
    char *args[MAX_LINE]; 

    while (runFlag) {
        printf("Seth_Shell$ > "); //Welcome message
        fflush(stdout);		  //flush the display
        char input[MAX_LINE];     //create an array for the command line 
        fgets(input, MAX_LINE, stdin); //get the input from stdin
        char *tokens;		  //character token pointer
        tokens = tokenize(input); //send input to tokenize function

        if (tokens[strlen(tokens) - 1] == '&') { 
            waitFlag = 0;
            tokens[strlen(tokens) - 1] = '\0';
        }
        char *arg = strtok(tokens, " "); //space token
        int i = 0;			 
        while (arg) {
            if (*arg == '<') {
                redirectSTDIN(strtok(NULL, " ")); //if command line has '<'
            } else if (*arg == '>') {
                redirectSTDOUT(strtok(NULL, " ")); //if command line has '>'
	      } else if (*arg == '|') { 	  //if command line has '|'
                args[i] = NULL;			
                pipeTime(args);
                i = 0; //index reset to 0 after pipe established
            } else {
                args[i] = arg; //current index of args array populated by arg
                i++; //increments the argument array one past the last arg
            }
            arg = strtok(NULL, " ");
        }
        args[i] = NULL; //puts a NULL at the end of the argument array
        run(args); //send argument pointer to run() function	
    }
    fclose(pFile);
    return 0;
}
