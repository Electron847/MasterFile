#Seth Weber
#BlumBlumShub Implementation 


x = 873245647888478349013
n = 9788476140853110794168855217413715781961
x0 = (x**2) % n
temp = 0
parityList = []

print("The x0 seed is", x0)
for i in range(1,9):
    
    x1 = (x0**2) % n
    temp = x1
    if i <= 9:
        x0 = temp
        parity = x0 % 2
        parityList.append(parity)
        print("xsub",i,"is",x1,"with parity", parity)
print .join(parityList)




y = (2**10203) % 101


