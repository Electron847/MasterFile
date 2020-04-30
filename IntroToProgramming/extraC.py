numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

x=numbers

y=[]
a=[]


print("Would you like to count odd or even numbers in the list 'numbers' ?")
z=eval(input("Enter 1 for 'odd' or 2 for 'even': "))
if z==1:
    for num in numbers:
        if num%2==1:
            y.append(num)
    print(len(y))
else:
    for nums in numbers:
        if nums%2==0:
            a.append(nums)
    print(len(a))

