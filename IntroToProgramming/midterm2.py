def calcEmployeeSales(n):

    file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
    text=file.read()
    lst_info=text.split('\n')
    lst_info.remove('')
    certs=[]
 #   numbers=[]
 #   mydict={}
 
    #'keeps total outside of inner loop and compounds addition'
    for line in lst_info:
        mydict={}
        indv_lst=line.split(',')
        keys=indv_lst[0]
        certs.append(keys)
        nums=indv_lst[1:13]
        numbers=[]
        
        for num in nums:
            x=int(num)
            numbers.append(x)


        mydict[keys]=sum(numbers)

    print(mydict)
    #return sum(numbers)

    #print(sum(numbers))

    file.close()



def silly(n):

 #   'Prints n amount of question marks, followed by n amount of exclamation points'

    if n<=0:
 #       'Sets stopping condition'

        print('', end='')
 #       'Sets the print out to amend the output of func(silly1) right after the output of this function

    else:
 #       'Recursive step: n > 0'
        print('?', end='')
 #       'Prints n first and then'
        silly(n-1)
 #       'Counts down from n-1 to 0'
        silly1(n)
 #       'References the function silly() n times once n<=0'

def silly1(n):
        
    print('!', end='')

