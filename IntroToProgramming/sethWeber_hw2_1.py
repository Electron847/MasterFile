##Seth Weber
##Homework 2 Problem #1

z=[]
z.append(eval(input('Enter the first number:')))
z.append(eval(input('Enter the second number:')))
z.append(eval(input('Enter the third number:')))
print('The highest number is:', max(z))
print('The lowest number is:', min(z))
x=round(((z[0] + z[1] + z[2])/3),1)
print('The average is:', x)

