def characterSearch(filename,character,n):
    'function reads input file and searches file for desired character in each word of input file until desired number of words'
    try:
        file=open('example.txt','r',encoding='utf-8')

    except FileNotFoundError:
        print(-1)
        
    text=file.read()
    mylist=[]
    for ch in '.,!?\n\t':
        text = text.replace(ch,' ')
        newtxt=text.split(' ')

    

    for index in newtxt:
        
        if character in index:
            mylist.append(index)
    print(mylist[0:n])

#Do not remove the commented code below
#You may temporarily uncomment it to test your own code,
#but please do not remove it from this file
#characterSearch('example.txt','e',6)
#characterSearch('does_not_exist.txt','e',6)

def emptDict():
    'function takes input file and converts information into readable dictionary, allowing for user to interface with age value information'
    
    file=open('employees.csv','r',encoding='utf-8')
    text=file.read()
    x=eval(input('Enter an age:'))
   
    txt=text.split('\n')
    myDict={}
    newDict={}
    for index in txt:
        
        individual=index.split(',')
        individual[3]=int(individual[3])
        key=tuple(individual[0:2])
        info_values=individual[2:len(txt)]
        myDict[key]=info_values

        if x < individual[3]:
            newDict[key]=info_values
    print(newDict)


#Please do not remove the line below
#print( emptDict() )

#Code to replace punctuation:
'''
for ch in '.,!?\n\t':
	someString = someString.replace(ch,' ')
'''
