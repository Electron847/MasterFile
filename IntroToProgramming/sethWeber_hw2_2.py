## Seth Weber
## Homework 2 Problem #2


x=['green','apple','baseball','fake news']
x.append(input('Enter a word: '))

print ('Here is your list: ' ,x)

y=(len(x[0])+len(x[1])+len(x[2])+len(x[3])+len(x[4]))/5

print ('The average length of each string is: ', y)

print ('The first character in each word is: ', x[0][0]+ x[1][0] +x[2][0]+x[3][0]+x[4][0])
print ('The last character in each word is: ', x[0][-1]+ x[1][-1] +x[2][-1]+x[3][-1]+x[4][-1])

z=input('Enter a word to search: ')
if z in x:
    print ('The word '+ z + ' is present: True')
else:
    print ('The word ' + z +' is present: False')
