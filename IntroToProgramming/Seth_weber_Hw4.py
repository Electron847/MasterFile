#Seth Conner Weber
#Problem 1 Assignment 4

o=['hamlet','beyonce','thou','wherefore art thou romeo']

def search_shakespeare():
    

    fin=open('shakespeare.txt' , 'r' , encoding='utf-8')
    text=fin.read()
    s=text.lower()
    for word in o:
        b=s.count(word)
        print('The term', word, 'appears', b, 'times in this document')

    v=eval(input('Would you like to search for your own term? Enter 1 for Yes or 2 for No: '))
    if v==1:
        c=input('Enter a term to seach for: ')
        d=c.lower()
        a=s.count(d)
        print("The term", c, 'appears', a, 'times in this document')
    fin.close()

#Seth Conner Weber
#Problem 2 assignment 4

print("to access function 2 type 'newfile()' and enter two arguments in function")

def newfile(y,u):

    a=open('weblog.txt', 'r', encoding='utf-8')
    for line in range(u):
        x=a.readline()
        p.append(x)
        p1=str(p)

    for g in p:
        print(g)
        
    b=open('output_log.txt', 'w', encoding='utf-8')
    for g in p:
        b.write(g)
    
    b.close()
    a.close()






    fin.close()
