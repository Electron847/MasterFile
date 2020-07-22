#include <stdio.h>
#include <stdlib.h>

// 1. x++ not an L-value, temporary value ++
void f (int x) {
  printf("%d\n",x++);
}

// 2. Yes, array
void fArr (int x) {
  int arr[] = { 5, 6, 7, 8, 9, 10, 11 };
  printf("%d\n",arr[x]);
}

struct S {
  int x;
  int y;
};


struct S ft () {
  struct S s;
  s.x = 5;
  s.y = 6;
  return s;  // returns a copy of the "struct S", i.e., copies the two int members back
}

struct S *ft1 () {
  struct S *p = (struct S *) malloc (sizeof (struct S));
  p->x = 10;   // recall that p->x is just shorthand for (*p).x
  p->y = 12;
  return p;  // returns a copy of the pointer, i.e., copies just a pointer back
}

// 3. No, temporary value from f()
void g () {
  struct S r = ft();
  printf("%d\n",ft ().x);
}

// 4. Yes, struct
void gt () {
  struct S t = ft();
  printf("%d\n",t.x);
}

// 5. yes, pointer is temporary, but field is not
void gt1 () {
  struct S r = *ft1();
  printf("%d\n",(*(ft1())).x);
}

int main(void) {
  f(2);
  fArr(2);
  g();
  gt();
  gt1();
  return 0;
}