file=open('/Users/sethweber/Desktop/Amsterdam_Trip_2019.txt','r',encoding='utf-8')
text=file.read()

mydict={}

newtext=text.split('\n')
lineCount = len(newtext)

for section in newtext:
 #   mydict={}

    line=section.split(',')
    
    ID_keys=line[0]
    ' = ' in ID_keys
 #   ID_keys = ID_keys.split(' = ')
    key=ID_keys
    mydict[key]=line[1:len(line)]
print(lineCount)
print(mydict)

file.close()

        


    







#for index in text:s
#    new_text=index.remove('\n')
    
#print(newtext)
    
