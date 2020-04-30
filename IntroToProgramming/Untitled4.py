#file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
#text=file.read()

#print(text)

#mydict={}

o=['1','2','3','4','5']
n=[]

for ch in o:
    numbers=int(ch)
    n.append(numbers)

print(sum(n))
        


x='12345,2,3,4,5\n11111,3,4,5,6\n'

y=x.split('\n')

y.remove('')

#print(y)

for index in y:

    listicle=index.split(',')

    key=listicle[0]

    therest=listicle[1:5]

    #print(therest)

    p=[]

    for num in therest:

        numbers=int(num)

        p.append(numbers)

#        woo=sum(p)

    print(sum(p))

        


   # mydict[key]=therest

        #for num in therest:

