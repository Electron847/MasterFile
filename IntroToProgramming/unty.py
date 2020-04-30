#students=[]

#print('{0:10} {1:10} {2:10} {3:12}'.format('Last', 'First', 'Class', 'Avg'))

#students.append(['DeMoines', 'Jim', 'Sophomore', 3.45])
#students.append(['Pierre', 'Sophie', 'Sophomore', 4.0])
#students.append(['Columbus','Maria','Senior', 2.5])
#students.append(['Phoenix','River','Junior', 2.45])
#students.append(['Olympis','Edgar','Junior',3.99])

#Last=str(students[0])
#First=str(students[1])
#Class=str(students[2])
#Avg=str(students[3])

#for line in students:
#    print('{0}\n{1:6}\n{2:6}\n{3:10}'.format(Last, First, Class, Avg),sep=' ')

#=========


#myDict={'1234':'Johnny'+' '+'567', '2345':'Oscar Kilo'}

#print(myDict['1234'])

#file=open('example.txt','r',encoding='utf-8')

#text=file.read()

#newTxt=text.split()
#'returns a list of strings'

#for word in newTxt:
 #   aDict={}
#    aDict[0]=aDict[0]+=1



#def lookup(dctPhoneNumbers):
#    phonebook = {('Luca', 'Elam'): '(312) 123-4567',\
#	     ('Djengo', 'Thomas'): '(773) 987-6543',\
#	     ('Devon', 'Reilly'): '(520) 454-6677'}

#    x=input('Enter the First Name: ')
#    y=input('Enter the Last Name: ')

#    return [x,y]

#def getNames(dctPhineNumbers):

#    name=lookup()
#    name=tuple([name])


def calc_square(x):
   return x*x

def do_stuff():
    done = False
    while not done:
        val = eval(input("Enter a number: "))
        if val == 0:
            done = True
        else:
            square = calc_square(val)
            #In the above line, 'do_stuff' is the "calling program"
            #Recall that Python will note precisely where we were in this
            #calling program when we invoked calc_square()

            print('The square of {} is {}.'.format(val, square))

    

    
