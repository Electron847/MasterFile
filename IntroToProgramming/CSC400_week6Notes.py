#new use for the 'in' operator - it can be a boolean variable
#as part of the 'if' operator it will return a true or false value

def upAbbrev (string):
    'returns a string w/ all uppercase letters in "string"'
    upperCaseLetters=''
    for ch in string:
        #print(ch) to test
        if ch.isupper():
            upperCaseLetters+=ch
    return upperCaseLetters
    print(ch)

#while loops


def startsWithVowel():
    while True:
        word = input("Enter a word: ")
        if word[0] not in 'aeiouAEIOU':
            continue
        else:
            print("Starts with a vowel.")


students={'1111':'Bob Smith','2222':'Alison Whatever'}
print(students.keys())
'prints the dictionary keys in the list of string'






        

