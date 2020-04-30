#file=open('weblog.txt', 'r', encoding='utf-8')
#y=file
#p=[]

def newfile(y,u):

    a=open('weblog.txt', 'r', encoding='utf-8')
    for line in range(u):
        x=a.readline()
        

        b=open('output_log.txt', 'w', encoding='utf-8')
        for line in range(u):
            b.write(x)
        

    b.close()
    a.close()
