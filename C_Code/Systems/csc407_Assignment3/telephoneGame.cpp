/*--------------------------------------------------------------------------*
 *----																	----*
 *----		telephoneGame.cpp											----*
 *----									----*
 *----	    This program simulates the children's game of "telephone",	----*
 *----	where an original message mutates as it is imperfectly 		----*
 *----	transmited among children in a pairwise manner.			----*
 *----									----*
 *----	    It demonstrates Linux/Unix thread programming using		----*
 *----	pthread_mutex_t and pthread_cond_t.				----*
 *----									----*
 *----	Compile with:							----*
 *----		linux> g++ -lpthread telephoneGame.cpp -o telephoneGame	----*
 *----									----*
 *----	----	----	----	----	----	----	----	----	----*
 *----									----*
 *----	Version 1.0		Joseph Phillips				----*
 *----									----*
 *--------------------------------Seth Weber--------------------------------*/



/*--------------------------------------------------------------------------*
 *----									----*
 *----			Includes and namespace designations:		----*
 *----									----*
 *--------------------------------------------------------------------------*/

#include <cstdlib>
#include <iostream>
#include <string>
#include <pthread.h>

using	 namespace	std;



/*--------------------------------------------------------------------------*
 *----									----*
 *----			    Definitions of constants:			----*
 *----									----*
 *--------------------------------------------------------------------------*/

/*  PURPOSE:  To tell the number of children among whom to pass the message.
 */
const	 int	NUM_CHILDREN			= 10;



/*  PURPOSE:  To tell the number of terms in the sentence.
 */
const	 int	NUM_WORDS_IN_SENTENCE		= 9;



/*  PURPOSE:  To tell the number of possible mutations for each term.
 */
const	 int	NUM_CHOICES_PER_POSITION	= 3;



/*  PURPOSE:  To tell the possible terms for each position in the sentence.
 */
const string 	words[NUM_WORDS_IN_SENTENCE][NUM_CHOICES_PER_POSITION]
		= { {"The","He","Wee"},
		    {"quick","slick","thick"},
		    {"brown","round","found"},
		    {"fox","box","locks"},
		    {"jumped","thumped","pumped"},
		    {"over","clover","white cliffs of Dover"},
		    {"the","be","three"},
		    {"lazy","hazy","A to Z"},
		    {"dog","frog","hog"}
		  };	



/*--------------------------------------------------------------------------*
 *----									----*
 *----	    Definitions of classes and their methods and functions:	----*
 *----									----*
 *--------------------------------------------------------------------------*/

/*  PURPOSE:  To represent the current state of a Sentence.
 */
class	 Sentence
{

  int	wordChoices[NUM_WORDS_IN_SENTENCE];

  //  Disallow copy-assignment
  Sentence&	operator=(const Sentence&);

public :

  //  PURPOSE:  To initialize '*this' sentence to its default state.  No
  //  	parameters.  No return value.
  Sentence	()
  {
    for  (int index = 0;  index < NUM_WORDS_IN_SENTENCE;  index++)
      wordChoices[index] = 0;
  }

  //  PURPOSE:  To make '*this' a copy of 'source'.  No return value.
  Sentence	(const Sentence& source)
  {
    for  (int  index = 0;  index < NUM_WORDS_IN_SENTENCE;  index++)
      wordChoices[index] = source.wordChoices[index];
  }

  //  PURPOSE:  To release the resources of '*this'.  No parameters.  No
  //	return value.
  ~Sentence ()
  {
  }

  //  PURPOSE:  To return the current word at position 'index'.
  const string&	getWord	(int index) const
  {
    return(words[index][wordChoices[index]]);
  }

  //  PURPOSE:  To (potentially) mutate one term of '*this' sentence.   No
  //	parameters.  No return value.
  void	imperfectlyTransmit	()
  {
    wordChoices[rand()%NUM_WORDS_IN_SENTENCE] = rand()%NUM_CHOICES_PER_POSITION;
  }

};



/*  PURPOSE:  To print the text of Sentence 'sentence' to output stream 'os'
 *	and to return 'os'.
 */
ostream&	operator<<	(ostream& os, const Sentence& sentence)
{
  for  (int index = 0;  index < NUM_WORDS_IN_SENTENCE;  index++)
  {
    os << sentence.getWord(index);
    os << ((index == (NUM_WORDS_IN_SENTENCE-1)) ? '.' : ' ');
  }

  return(os);
}



/*  PURPOSE:  To hold the state of the messaging system, including the
 *	mutexes, conditions and sentence buffers.
 */
class	MessageSystem
{
  pthread_mutex_t 	mutexes[NUM_CHILDREN+1];	
  
  pthread_cond_t	condArray[NUM_CHILDREN+1];
  

  Sentence*			sentenceArray[NUM_CHILDREN+1];
  

  //  Disallow copy-construction
  MessageSystem		(const MessageSystem&);

  //  Disallow copy-assignment
  MessageSystem&	operator=(const MessageSystem&);

public :

  //  PURPOSE:  To initialize the array of mutexes, the array of conditions
  //  	and the array of sentence pointers to be NULL.
  MessageSystem	()
  {
    for  (int index = 0;  index <= NUM_CHILDREN;  index++)
    {
      sentenceArray[index] = NULL;
	   pthread_mutex_init(getLockPtr(index),NULL);	
	   pthread_cond_init(getCondPtr(index),NULL);	
	   
    }
  }


  //  PURPOSE:  To destroy the mutexes in their array, to destroy the conditions
  //	in their array, to delete() the sentence pointers in their array.  No
  //	parameters.  No return value.
  ~MessageSystem	()
  {
    for  (int index = 0;  index <= NUM_CHILDREN;  index++)
    {
       delete(sentenceArray[index]);
	   pthread_mutex_destroy(&mutexes[index]); 	
	   pthread_cond_destroy(&condArray[index]); 	
    }
  }


  //  PURPOSE:  To return a *pointer* to the lock at position 'index'.  // return address of mutex
  pthread_mutex_t*	getLockPtr	(int	index)
  {
    return(&mutexes[index]);
  }


  //  PURPOSE:  To return a *pointer* to the condition at position 'index'. // return address of condition
  pthread_cond_t*	getCondPtr	(int	index)
  {
    return(&condArray[index]);
  }


  //  PURPOSE:  To "give away" (set equal to NULL) the pointer to the sentence
  //	at position 'index'.
  Sentence*	giveSentencePtr (int index)
  {
    Sentence*	ptr = sentenceArray[index];

    sentenceArray[index]	= NULL;
    return(ptr);
  }


  //  PURPOSE:  To set the sentence pointer at position 'index' equal to
  //	'sentencePtr'.  No return value.
  void		setSentencePtr	(int index, Sentence* sentencePtr)
  {
    sentenceArray[index]	= sentencePtr;
  }



  //  PURPOSE:  To return 'true' if the sentence at position 'index' is ready
  //	to be transmitted.
  bool		isReady		(int index) const
  {
    return(sentenceArray[index] != NULL);
  }

};



/*--------------------------------------------------------------------------*
 *----									----*
 *----			Definitions of global variables:		----*
 *----									----*
 *--------------------------------------------------------------------------*/

/*  PURPOSE:  To hold the global messaging system.
 */
MessageSystem	messageSystem;



/*--------------------------------------------------------------------------*
 *----									----*
 *----			Definitions of global functions:		----*
 *----									----*
 *--------------------------------------------------------------------------*/

/*  PURPOSE:  To get the necessary locks, get the sentence, print it,
 *	transmit it (imperfectly), and unlock and signal the next child.
 */
void*	child	(void*	argPtr)
{ 
  //  I.  Applicability validity check:
  //error: ISO C++ forbids comparison between 
  //pointer and integer [-fpermissive]
  /*
  if (argPtr < 1)
	{
		cout << "Something went wrong.." << endl;
		exit(EXIT_FAILURE);
	}
	*/

  //  II. Run for current child:
  //  II.A.  Get 'index':
  //  YOUR CODE HERE
  int 		index	= *(int*)argPtr;

  //  II.B.  Announce that this child is ready:
  //  YOUR CODE HERE
  cout << "Child " << index << " is ready to start" << endl;

  //  II.C.  Get both locks and wait until signaled (if need to):
  if  ( (rand() % 2) == 1)
  {
  
	pthread_mutex_lock(messageSystem.getLockPtr(index));
	pthread_mutex_lock(messageSystem.getLockPtr(index-1));
  }
  else
  {
  
	pthread_mutex_lock(messageSystem.getLockPtr(index-1));
	pthread_mutex_lock(messageSystem.getLockPtr(index));
  }


  cout << "Child " << index << " got all his/her locks" << endl;
  
  while (! messageSystem.isReady(index-1)) // prevoius child is ready
	{
		cout << "Child " << index << " is surrendering lock waiting for signal" << endl;
		pthread_cond_wait(messageSystem.getCondPtr(index-1),messageSystem.getLockPtr(index-1)); 
		
	}
	
  //  II.D.  Get pointer to sentence, print it and transmit it:
  
  Sentence* sentPtr;
  sentPtr = messageSystem.giveSentencePtr(index-1);
  
  cout << "Child " << index << " says: " << *sentPtr << endl;
  sentPtr -> imperfectlyTransmit();
  messageSystem.setSentencePtr(index,sentPtr);
  
  //  II.E.  Signal next child that message is ready and unlock their 
  
  pthread_cond_signal(messageSystem.getCondPtr(index));
  pthread_mutex_unlock(messageSystem.getLockPtr(index-1));
  pthread_mutex_unlock(messageSystem.getLockPtr(index));
 

  //  III.  Finished:
  return(NULL);
}

/*  PURPOSE:  To play the telephone game.  'argc' tells how many command line
 *	arguments there are.  'argv[]' points to each.  Returns 'EXIT_SUCCESS'
 *	to OS.
 */
int	main (int argc, const char* argv[])
{
  //  I.  Applicability validity check:


  //  II.  Play game:
  //  II.A.  Seed random number generator:
  int 	   	randNumSeed;

  if  (argc >= 2)
    randNumSeed = strtol(argv[1],NULL,10);
  else
  {
    string	entry;

    cout << "Random number seed? ";
    getline(cin,entry);
    randNumSeed = strtol(entry.c_str(),NULL,10);
  }
  srand(randNumSeed);

  //  II.B.  Play game:
  Sentence	sentence;
  int		childIndex;
  int		indices[NUM_CHILDREN+1];
  //  YOUR CODE HERE FOR AN ARRAY OF NUM_CHILDREN+1 THREADS
  pthread_t	childrenArray[NUM_CHILDREN+1];

  messageSystem.setSentencePtr(0,&sentence);

  for  (childIndex = NUM_CHILDREN;  childIndex > 0;  childIndex--)
  {
    indices[childIndex] = childIndex;
    //  YOUR CODE HERE TO INITIALIZE THREAD NUMBER childIndex
	pthread_create(&childrenArray[childIndex],NULL,child,&indices[childIndex]);
  }

  for  (childIndex = 1; childIndex <= NUM_CHILDREN;  childIndex++)
  {
    //  YOUR CODE HERE TO WAIT FOR THREAD NUMBER childIndex
	pthread_join(childrenArray[childIndex],NULL);
  }
  cout << "Finally we have: \"" << *messageSystem.giveSentencePtr(10) << "\""
       << endl;


  //  III.  Finished:

  return(EXIT_SUCCESS);
}