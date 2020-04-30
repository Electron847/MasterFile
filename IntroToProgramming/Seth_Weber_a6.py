def generateDict():
    'function generates the dictionary based on file cities_by_continent.csv.txt'
    try:
        file=open('cities_by_continent.csv.txt','r',encoding='utf-8')

    except FileNotFoundError:
        print('Could not find this file')

    except IOError:
        print('Could not open this file')

    content=file.read()
    text=content.split('\n')
    mydict={}
    countries_global=[]

    #text.split(',')
    text.remove('')

    for line in text:
        spot=line.split(',')

        for index in spot:
            continents=spot[0]
            countries=spot[1:(len(spot))]
            countries_global.append(countries)
            mydict[continents]=countries
    return mydict
    

def getContinentChoice(dictionary):
    'function asks user for continent choice and prints out cities within that continent based on file cities_by_continent.csv.txt'
    for key in d:
        print(key)
    user_choice=input('Choose a continent from the list above:')
    if user_choice in d:
        var1=d[user_choice]
        print('Some important countries in this continent are',var1[0],',',var1[1],',and',var1[2])
       

d=generateDict()
s=getContinentChoice(d)






