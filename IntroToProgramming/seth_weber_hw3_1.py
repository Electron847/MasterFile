numbers = [76, 93,  3, 35, 30, 74,  8, 27, 19, 96, 33, 16, 16, 56, 98, 28, 19, 14, 63, 53,  2, 60,  4, 93, 61,

3, 56, 31, 25, 74]

y=[]

def search_for_higher_number(x,numbers):
    for num in numbers:
        if num > x:
            y.append(num)
    print(len(y))
        
            
            
            
    
