file=open('widget_sales_id.csv.txt','r',encoding='utf-8')
text=file.readlines()

mydict={}

for line in text:

    new_text=line.split(',')

    new_text.remove('\n')

    ID_key=new_text[0]

    info=new_text[1:13]

    Keys=ID_key

    mydict[Keys]=info

print(mydict)

print(mydict['34P1'])

    

 #   for key in range(len(ID_key)):
#
#        mydict[key]=info

#    print(mydict)







mydict={}

mydict['111']='lookin sexy','hey brosuf',1111

#print(mydict)
