name=open('transactions.csv.txt' , 'r' , encoding='utf-8')
p=[]
a=[]
b=[]
c=[]

for line in range(80):
    x=name.readline()
    p.append(x)
    p1=list(p)

print('Which card would you like to search for?\n \t 1=Visa\n \t 2=American Express\n \t 3=Mastercard')

x=eval(input('Enter 1-3: '))

if x==1:
    for g in p1:
        y=g.split(',')
        z=y[0:4]

        if 'Visa' in z:
            a.append(z)
    print('There are', len(a),'transactions that used this card')

if x==2:
    for g in p1:
        y=g.split(',')
        z=y[0:4]

        if 'Amex' in z:
            b.append(z)
    print('There are', len(b),'transactions that used this card')

if x==3:
    for g in p1:
        y=g.split(',')
        z=y[0:4]

        if 'Mastercard' in z:
            c.append(z)
    print('there are', len(c),'transactions that used this card')

name.close()




