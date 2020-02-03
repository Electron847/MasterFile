#Seth Weber
#Cryptology 440
#January 2019
#Assignment 3 Problem 6
#Generating Primes

import random

def isProbablyPrime(p):
    for i in range(0,20):
        a = random.randint(2,p-2)
        if pow(a,p-1,p) != 1:
            return False
    return True


for index in range(16):
    cycles = 0;
    while True:
        randNum = random.randint(2**30, 2**31)
        isProbablyPrime(randNum)
        cycles+=1
        if(isProbablyPrime(randNum) == True):
            break

    print("Prime number generated: ", randNum)
    print("Number of loops until prime was generated: ", cycles)
