
#numbers = [3,0,1,2,3,6,2,4,5,6,5]
#def check_list(numbers):
#    results = []
#    for x in range(1,len(numbers)):
#        currentX = numbers[x]
#        lastX = numbers[x-1]
#        if lastX*2 == currentX:
#             results.append(currentX)     
#    print (results)



            
    
file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
text=file.readlines()

mydict={}

for line in text:
    y=line.split(',')
    ID_key=y[0]
    info=y[1:12]
    
    

    

    print(info)

    file.close()




    



                

    


    

    

   

    

    

    

    


