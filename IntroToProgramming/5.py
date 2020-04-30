
p=[]

def newfile(y,u):

    a=open('weblog.txt', 'r', encoding='utf-8')
    for line in range(u):
        x=a.readline()
        p.append(x)
        p1=str(p)

    for g in p:
        print(g)
        
    b=open('output_log.txt', 'w', encoding='utf-8')
    for g in p:
        b.write(g)
    
    b.close()
    a.close()
