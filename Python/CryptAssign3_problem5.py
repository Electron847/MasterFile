#Seth Weber
#Cryptology 440
#January 2019
#Assignment 3
#Generating one-time pad key sequence with BlumBlumShub PRNG



x = 873245647888478349013
n = 9788476140853110794168855217413715781961
x0 = (x**2) % n
temp = 0
parityList = []
tempList = []
cipherList = []

def string2bits(s=""):
    return [bin(ord(x))[2:].zfill(8) for x in s]

def bits2string(b=None):
    return ''.join([chr(int(x,2)) for x in b])

print("Enter a message to encrypt: ")
plaintext = input()
binaryTranslation = string2bits(plaintext)
#print(binaryTranslation)

#convertedback=bits2string(b)
#print(convertedback)
binaryString=''.join(binaryTranslation)
print ('Plaintext:\n' + binaryString + '\n')
plainList=[]
for chr in binaryString:
    intPlainBin = int(chr)
    plainList.append(intPlainBin)
#print(plainList)   
keySequence = []

#print("The x0 seed is", x0)
for i in range(1,9):
    
    x1 = (x0**2) % n
    temp = x1
    if i <= 9:
        x0 = temp
        parity = x0 % 2
        parityList.append(parity)
 #       print("xsub",i,"is",x1,"with parity", parity)

#print(parityList)
parityString = ''.join(str(i) for i in parityList)
#print(parityString)

for i in range(0,len(binaryTranslation)):
    for chr in parityString:
        keySequence.append(chr)

keySequenceString = ''.join(str(i) for i in keySequence)
for chr in keySequenceString:
    intKeyBin = int(chr)
    tempList.append(intKeyBin)
#print(tempList)
print('Key Sequence:\n' + keySequenceString + '\n')

for index in range(len(plainList)):
    cipherBit = plainList[index] ^ tempList[index]
    cipherList.append(cipherBit)

#print(cipherList)
cipherString = ''.join(str(i) for i in cipherList)
space = 4
sl = [cipherString[i:i+space] for i in range(0,len(cipherString),space)]
#print(sl)
resultingCipherString = ' '.join(sl)
print('Your cipherbit string is as follows:\n' + resultingCipherString)
