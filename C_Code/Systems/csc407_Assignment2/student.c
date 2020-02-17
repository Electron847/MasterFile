 *------*                                                                       
 *---student.c---*                                                              
 *------*                                                                       
 *--------------------------------------*                                       
 *------*                                                                       
 *---Version 1a                                            Seth Weber---*       
 *------*                                                                       
 *-------------------------------------------------------------------------*/
#include "sleepyProfHeaders.h"

//Integer variable to hold '1' while class is still in session and '0' after    
//class.                                                                        
int isStillClassTime = 1;

//Snore number variable initialization                                          
int snoreNumber = 0;

//Number of complaints variable                                                 
int complaintNumber = 0;

//Professor process id                                                          
pid_t profPid;

//Dean process id                                                               
pid_t deanPid;

//End sleep() loop in main and has student go to lunch                          
void classDismissedSigHandler(int sigNum){
  isStillClassTime = 0;
  printf("Student \"Time for lunch!\"\n");

}

//Print 'Student scribbles notes' upon receiving the PROF_TEACH_SIGNAL          
void profTeachSigHandler(int sigNum){
  printf("(Student scribbles notes)\n");
}

//Handle PROF_SNORE_SIGNAL sent from professor                                  
void profSnoreSigHandler(int sigNum){
  snoreNumber++;
  if(snoreNumber < NUM_SNORES_BEFORE_STUDENT_COMPLAINS){
    printf("(Snore)\n");
    printf("Student \"%s?\"\n",PROFS_NAME);
  }
  else{
    snoreNumber = 0;
    complaintNumber++;
    if(complaintNumber < NUM_COMPLAINTS_TO_PROF_BEFORE_COMPLAIN_TO_DEAN){
      kill(profPid, COMPLAIN_SIGNAL);
      printf("Student \"%s!\"\n",PROFS_NAME);
    }
    else if(complaintNumber > NUM_COMPLAINTS_TO_PROF_BEFORE_COMPLAIN_TO_DEAN){
      complaintNumber = 0;
      kill(deanPid,COMPLAIN_SIGNAL);
    }
  }
}

//Install the signal handlers                                                   
void installHandlers()
{
  struct sigaction act;
  memset(&act,'\0',sizeof(act));
  act.sa_handler = classDismissedSigHandler;
  sigaction(CLASS_DISMISSED_SIGNAL,&act,NULL);
  act.sa_handler = profTeachSigHandler;
  sigaction(PROF_TEACH_SIGNAL,&act,NULL);
  act.sa_handler = profSnoreSigHandler;
  sigaction(PROF_SNORE_SIGNAL,&act,NULL);

}

//main() function call                                                          
int main (int argc, char* argv[]){
  printf("you are now in student.c \"\n");

  //struct sigaction act;                                                       
  //memset(&act,'\0',sizeof(act));                                              
  //act.sa_handler = profTeachSigHandler;                                       
  //sigaction(PROF_TEACH_SIGNAL,&act,NULL);                                     
  //char text[MAX_LINE];                                                        
  //fgets(text,MAX_LINE,stdin);                                                 
  deanPid = strtol(argv[1],NULL,10);
  profPid = getppid();
  //deanPid = atoi(argv[1]);                                                    
  installHandlers();

  if(argc < 2){
    printf("Student \"Hey! you did not tell me the Dean's number so I can compl\
ain if I need to\"\n");
    exit(EXIT_FAILURE);
  }
  while(isStillClassTime){
    sleep(1);
  }
  return(EXIT_SUCCESS);
}
