def silly(n):

   # 'Prints n amount of question marks, followed by n amount of exclamation points'

    if n<=0:
        #'Sets stopping condition'

        print('', end='')
 #       'Sets the print out to amend the output of func(silly1) right after the output of this function

    else:
 #       'Recursive step: n > 0'
        print('?', end='')
 #       'Prints n first and then'
        silly(n-1)
 #       'Counts down from n-1 to 0'
        silly1(n)
 #       'References the function silly1() n times once n<=0'

def silly1(n):
        
    print('!', end='')


def calcEmployeeSales(keys):

    try:
        file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
        text=file.read()

    except FileNotFoundError:
        print('Could not find this file')

    except IOError:
        print('Could not find this file')

    lst_info=text.split('\n')
    lst_info.remove('')
    mydict={}
    #'keeps total outside of inner loop and compounds addition'
    for line in lst_info:
        indv_lst=line.split(',')
        keys=indv_lst[0]
        nums=indv_lst[1:13]
        numbers=[]
        
        for num in nums:
            x=int(num)
            numbers.append(x)
        
        mydict[keys]=sum(numbers)

    print(mydict)
    file.close()


def total_widget_sales():
    
    file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
    text=file.read()
    lst_info=text.split('\n')
    lst_info.remove('')
    numbers=[]
    mydict={}
    #'keeps total outside of inner loop and compounds addition'
    for line in lst_info:
        indv_lst=line.split(',')
        keys=indv_lst[0]
        nums=indv_lst[1:13]

        for num in nums:
            x=int(num)
            numbers.append(x)

        mydict[keys]=sum(numbers)

    print(sum(numbers))

    file.close()



        



       
                

        



        

    


    

        

    
