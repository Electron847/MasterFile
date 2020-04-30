numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

x=numbers

y=[]

a=[]

print('Welcome')
print('Would you like to count the odd numbers (type 1), or count the even numbers (type 2)?')

x=eval(input('Enter 1 or 2 here: '))

if x==1:
    for num in numbers:
        if num%2==1:
            y.append(num)
    print('There are ', len(y), 'odd numbers in the list')
if x==2:
    for num in numbers:
        if num%2==0:
            a.append(num)
    print('There are {} even numbers in the list and {} odd numbers in the list'.format(len(a),len(y)))
    
            

