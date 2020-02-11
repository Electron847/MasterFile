#Assignment 4
#Problem 3

plainText = int(input("Enter Plaintext int: "));
plainTextBinary = format(plainText,'012b')
keyInt = int(input("Enter key int: "));
keyIntBinary = format(keyInt,'08b')

print("PlainText = " + str(plainText) +" ("+ str(plainTextBinary)+"); key = " + str(keyInt)+" ("+str(keyIntBinary)+")");

plainTextLeft = plainTextBinary[:6]
plainTextRight = plainTextBinary[6:12]

print("L0 = " + str(int(plainTextLeft,2)) +" ("+ str(plainTextLeft)+"); R0 = " + str(int(plainTextRight,2))+" ("+str(plainTextRight)+")");
print(len(plainTextRight))
def expander(plainTextRight):
    expandedString = plainTextRight[0]+plainTextRight[1]+plainTextRight[3]+plainTextRight[2]+plainTextRight[3]+plainTextRight[2]+plainTextRight[4]+plainTextRight[5];
    print("These are the expanded bits beginning the f-box " + expandedString)
    return (expandedString);

expandedString = expander(plainTextRight)
print("initial key is : " + keyIntBinary[:8])

keyIntBinaryLeft = (keyIntBinary[:8])

subKeyList = []
expandedIntList = []
keyIntBinaryLeftList = []

for char in expandedString:
    expandedInt = int(char)
    expandedIntList.append(expandedInt)

for char in keyIntBinaryLeft:
    keyIntBinaryLeftInt = int(char)
    keyIntBinaryLeftList.append(keyIntBinaryLeftInt)
                                
for index in range(len(expandedIntList)):
    subKeyBit = expandedIntList[index] ^ keyIntBinaryLeftList[index]
    subKeyList.append(subKeyBit);

subKeyString = ''.join(str(i) for i in subKeyList)

print("xor'ing thing together reaches " + subKeyString + " which needs to be split into two groups of 4")

#print("now into the s-boxes, let's hardcode each s-box table")
print("let's first split this string of 8 bits")
sBoxOneInput = str(subKeyString[:4])
sBoxOneLeftMostBit = sBoxOneInput[0]
sBoxOneRemaining3Bits = sBoxOneInput[1:4]
sBoxOneLeadBit = int(sBoxOneLeftMostBit)
print("s-box 1 input is: " + sBoxOneInput)
print("leftmost bit is: " + sBoxOneLeftMostBit)
print("The 3 ending bits of s-box1 are: " + sBoxOneRemaining3Bits)
print("new variable, integer status now acheived for sBoxOneLeftMostBit: ", sBoxOneLeadBit)

sBoxTwoInput = str(subKeyString[4:])
sBoxTwoLeftMostBit = sBoxTwoInput[0]
sBoxTwoRemaining3Bits = sBoxTwoInput[1:4]
sBoxTwoLeadBit = int(sBoxTwoLeftMostBit)
print("s-box 2 input is: " + sBoxTwoInput)
print("leftmost bit is: " + sBoxTwoLeftMostBit)
print("The 3 ending bits of s-box2 input are: " + sBoxTwoRemaining3Bits)
print("new variable, integer status now acheived for sBoxTwoLeftMostBit: ", sBoxTwoLeadBit)

sBoxOne_Table = [["101","001"],["010","100"],["001","110"],["110","010"],["011","000"],["100","111"],["111","101"],["000","011"]]
#print("sBox Number One Table: \n", sBoxOne_Table)
sBoxTwo_Table = [["100","101"],["000","011"],["110","000"],["101","111"],["111","110"],["001","010"],["011","001"],["010","100"]]
#print("sBox Number Two Table: \n", sBoxTwo_Table)
S1_zeroRow = []
S1_oneRow = []

duals_boxOutputList = []
remainingBitsChoices = ["000","001","010","011","100","101","110","111"]

#def sBoxOne()
if sBoxOneLeadBit == 0:
    for index in range(len(sBoxOne_Table)):
        S1_zeroRow.append(sBoxOne_Table[index][sBoxOneLeadBit])
    for i in range(len(remainingBitsChoices)):
        if sBoxOneRemaining3Bits == remainingBitsChoices[i]:
            indexForTable = i
            print("the 3 bits we get from s-box1 are: " + S1_zeroRow[indexForTable])
            duals_boxOutputList.append(S1_zeroRow[indexForTable])
if sBoxOneLeadBit == 1:
    for index in range(len(sBoxOne_Table)):
        S1_oneRow.append(sBoxOne_Table[index][1])
    for i in range(len(remainingBitsChoices)):
        if sBoxOneRemaining3Bits == remainingBitsChoices[i]:
            indexForTable = i
            print("the 3 bits we get from s-box1 are: " + S1_oneRow[indexForTable])
            duals_boxOutputList.append(S1_oneRow[indexForTable])
            
S2_zeroRow = []
S2_oneRow = []

if sBoxTwoLeadBit == 0:
    for index in range(len(sBoxTwo_Table)):
        S2_zeroRow.append(sBoxTwo_Table[index][sBoxTwoLeadBit])
    for i in range(len(remainingBitsChoices)):
        if sBoxTwoRemaining3Bits == remainingBitsChoices[i]:
            indexForTable = i
            print("the 3 bits we get from s-box2 are: " + S2_zeroRow[indexForTable])
            duals_boxOutputList.append(S2_zeroRow[indexForTable])

if sBoxTwoLeadBit == 1:
    for index in range(len(sBoxTwo_Table)):
        S2_oneRow.append(sBoxTwo_Table[index][sBoxTwoLeadBit])
    for i in range(len(remainingBitsChoices)):
        if sBoxTwoRemaining3Bits == remainingBitsChoices[i]:
            indexForTable = i
            print("the 3 bits we get from s-box2 are: " + S2_oneRow[indexForTable])
            duals_boxOutputList.append(S2_oneRow[indexForTable])
            
bothS_BoxOutputJoined2String = ''.join(str(i) for i in duals_boxOutputList)
print("after concatenation we get a string of " + bothS_BoxOutputJoined2String)

newR1 = []
for index in range(len(bothS_BoxOutputJoined2String)):
    newR1key = int(plainTextLeft[index]) ^ int(bothS_BoxOutputJoined2String[index])
    newR1.append(newR1key);

print("plaintextLeft = " +plainTextLeft)
print("sbox output = " +bothS_BoxOutputJoined2String)
print(newR1);

newR1String = ''.join(str(i) for i in newR1)
print(newR1String);
