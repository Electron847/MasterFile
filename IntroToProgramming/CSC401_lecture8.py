#def stuff():
#    x=3
#    prit(x)

#stuff()
#print(x)

#will cause error at the end of the code because 'x' isn't defined in the
#execution of the print()

import random
lst_nums=[]

for x in range(20):
    num=random.randint(1,100)
    lst_nums.append(num)

    newlst_nums=[]

    for x in range(1,len(lst_nums)):
        lastx=lst_nums[x-1]
        currentX=lst_nums[x]
        if lastx > currentX:
            newlst_nums.append(lastx)
        
print(lst_nums)
print(newlst_nums)
print(sum(newlst_nums))
    

#n1=5
#n2=6

#print(n1.__add__(n2))

# this ^ shows the dunder method for the add operation



    
