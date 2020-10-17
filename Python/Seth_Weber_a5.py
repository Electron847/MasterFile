#Seth Conner Weber
#HW Assignment 5


def creditCardCount(x):
    try:
        name=open('transactions.csv.txt' , 'r' , encoding='utf-8')

        content=name.readlines()
    except FileNotFoundError:
        print('Could not find this file')

    except IOError:
        print('Could not find this file')

    print('Which card would you like to search for?\n \t 1=Visa\n \t 2=American Express\n \t 3=Mastercard')

    x=eval(input('Enter 1-3: '))

    list_of_cards=[]

    for line in content:
        y=line.split(',')
        credit_card=y[3]
        list_of_cards.append(credit_card)
    if x==1:
        s= 'There are {0} transactions that used {1}'.format(list_of_cards.count('Visa'), 'Visa')
        print(s)
    if x==2:
        s= 'There are {0} transactions that used {1}'.format(list_of_cards.count('Amex'), 'Amex')
        print(s)
    if x==3:
        s= 'There are {0} transactions that used {1}'.format(list_of_cards.count('Mastercard'), 'Mastercard')
        print(s)


numbers = [3,0,1,2,3,6,2,4,5,6,5]

def find_doubles(numbers):
    
    list_of_doubles = []
    
    for x in range(1,len(numbers)):
        
        currentX = numbers[x]
        
        lastX = numbers[x-1]
        
        if lastX*2 == currentX:
            
             list_of_doubles.append(currentX)
             
    print (list_of_doubles)

    

        
    

        

    
    

    
    


    
