##Seth Conner Weber


#o=['Hamlet','Beyonce','thou','wherefore art thou romeo']
#def search_shakespeare():
    
#
#    fin=open('shakespeare.txt' , 'r' , encoding='utf-8')
#    text=fin.read()
#    s=text.lower()
#    for word in s:
#        a=s.count('hamlet')
#    print(a)
#
#    fin.close()
        




def search_shakespeare():
    

    fin=open('shakespeare.txt' , 'r' , encoding='utf-8')
    text=fin.read()
    s=text.lower()
    t=s.count('hamlet')
    print("The term 'hamlet' appears", t, 'times in this document')

    y=s.count('beyonce')
    print("The term 'beyonce' appears", y, 'times in this document')

    x=s.count('thou')
    print("The term 'thou' appears", x, 'times in this document')

    u=s.count('wherefore art thou romeo')
    print("The term 'wherefore art thou romeo' appears", u, 'times in this document')

    v=eval(input('Would you like to search for your own term? Enter 1 for Yes or 2 for No: '))
    if v==1:
        c=input('Enter a term to search for: ')
        b=c.lower()
        a=s.count(b)
        print("The term appears", a, 'times in this document')

    fin.close()




        
