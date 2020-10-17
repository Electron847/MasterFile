#Seth Weber
#Cryptology
#Problem 6 -> Assignment 3
import random

def isProbablyPrime(p):
    for i in range(0,20):
        a = random.randint(2, p-2)
        if pow(a, p-1, p) != 1:
            return False
    return True

for index in range(16):
    loopCounter = 0
    while True:
        randNum = random.randint(2**30, 2**31)
        isProbablyPrime(randNum)
        loopCounter+=1
        if(isProbablyPrime(randNum) == True):
            break
    print("The prime generated: ", randNum)
    print("Number of loops to find prime: ", loopCounter)

#loopCounter = 0
#while True:
#    randNum = random.randint(2**30, 2**31)
#    isProbablyPrime(randNum)
#    loopCounter+=1
#    if(isProbablyPrime(randNum) == True):
#        break
#print("The prime generated: ", randNum)
#print("Number of loops to find prime: ", loopCounter)
