numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

x=numbers

y=[]

def count_odds(x):
    for num in numbers:
        if num%2==1:
            y.append(num)
    print(len(y))

numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

x=numbers

y=[]

def count_even(x):
    for num in numbers:
        if num%2==0:
            y.append(num)
    print(len(y))



print("Use the list 'numbers'  without quotes to enter as the second argument of the function")

numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

z=[]
y=[]
    
    
def list_first_numbers(x,y):
    for i in y:
        z.append(i)
    print(numbers[0:x])

numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

y=[]

def search_for_higher_number(x,numbers):
    for num in numbers:
        if num > x:
            y.append(num)
    print(len(y))
        

