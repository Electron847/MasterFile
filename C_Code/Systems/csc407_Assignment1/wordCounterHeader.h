//---    Common inclusions:---//                                                
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


//---    Common constants:---//                                                 
#define SMALL_TEXT_LEN 256


//---      Common types:---//                                                   
struct TreeNode
{
  char* wordCPtr_;
  int count_;
  struct TreeNode* leftPtr_;
  struct TreeNode* rightPtr_;
};


struct ListNode{
  char* wordCPtr_;
  int count_;
  struct ListNode* nextPtr_;
};


//---    Declarations of global functions:---//                                 
extern struct TreeNode* buildTree (FILE* filePtr);
extern struct ListNode* buildList (FILE* filePtr);
extern void printTree (struct TreeNode* nodePtr);
extern void freeTree (struct TreeNode* nodePtr);
extern void printList (struct ListNode* firstPtr);
extern void freeList (struct ListNode* firstPtr);
extern int didGetWord (char** positionHandle, char* word);
extern int textLen;

