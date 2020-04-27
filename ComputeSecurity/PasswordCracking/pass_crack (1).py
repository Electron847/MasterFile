#Seth Weber
#CSC 439 Computer Security
#Spring 2020

import hashlib

def search_dict_hash():
    infile_dict = open('dic-0294.txt',encoding='utf-8')
    dict_passwords = infile_dict.readlines()
    infile_dict.close()

    infile_pw1 = open('pw1.hex')
    pw1 = infile_pw1.readline()
    infile_pw1.close()
    pw1 = pw1.strip()

    infile_pw2 = open('pw2.hex')
    pw2 = infile_pw2.readline()
    infile_pw2.close()
    pw2 = pw2.strip()

    infile_pw3 = open('pw3.hex')
    pw3 = infile_pw3.readline()
    infile_pw3.close()
    pw3 = pw3.strip()
    
    for password in dict_passwords:
        password = password.strip()
        if hashlib.md5(password.encode()).hexdigest() == pw1:
            print("pw1:",password,"- md5")
        elif hashlib.sha1(password.encode()).hexdigest() == pw1:
            print("pw1:",password,"- sha1")
        elif hashlib.sha224(password.encode()).hexdigest() == pw1:
            print("pw1: ",password,"- sha224")
        elif hashlib.sha256(password.encode()).hexdigest() == pw1:
            print("pw1: ",password,"- sha256")

        if hashlib.md5(password.encode()).hexdigest() == pw2:
            print("pw2:",password,"- md5")
        elif hashlib.sha1(password.encode()).hexdigest() == pw2:
            print("pw2: ",password,"- sha1")
        elif hashlib.sha224(password.encode()).hexdigest() == pw2:
            print("pw2: ",password,"- sha224")
        elif hashlib.sha256(password.encode()).hexdigest() == pw2:
            print("pw2: ",password,"- sha256")

        if hashlib.md5(password.encode()).hexdigest() == pw3:
            print("pw3:",password,"- md5")
        elif hashlib.sha1(password.encode()).hexdigest() == pw3:
            print("pw3: ",password,"- sha1")
        elif hashlib.sha224(password.encode()).hexdigest() == pw3:
            print("pw3: ",password,"- sha224")
        elif hashlib.sha256(password.encode()).hexdigest() == pw3:
            print("pw3: ",password,"- sha256")

def search_dict_salt():
    infile_dict = open('dic-0294.txt')
    dict_passwords = infile_dict.readlines()
    infile_dict.close()

    infile_salt = open('salt.hex')
    salt = infile_salt.readline()
    infile_salt.close()
    salt = salt.strip()
    salt = bytearray.fromhex(salt)

    infile_spw1 = open('spw1.hex')
    spw1 = infile_spw1.readline()
    infile_spw1.close()
    spw1 = spw1.strip()
    spw1 = bytearray.fromhex(spw1)

    infile_spw2 = open('spw2.hex')
    spw2 = infile_spw2.readline()
    infile_spw2.close()
    spw2 = spw2.strip()
    spw2 = bytearray.fromhex(spw2)

    infile_spw3 = open('spw3.hex')
    spw3 = infile_spw3.readline()
    infile_spw3.close()
    spw3 = spw3.strip()
    spw3 = bytearray.fromhex(spw3)

    for password in dict_passwords:
        password = password.strip()
        password = bytearray(password,'utf-8')
        salted_pass = salt+password
 
        if hashlib.md5(salted_pass).digest() == spw1:
            print("spw1:",password,"- md5")
        elif hashlib.sha1(salted_pass).digest() == spw1:
            print("spw1:",password,"- sha1")
        elif hashlib.sha224(salted_pass).digest() == spw1:
            print("spw1: ",password,"- sha224")
        elif hashlib.sha256(salted_pass).digest() == spw1:
            print("spw1: ",password,"- sha256")
        elif hashlib.sha384(salted_pass).digest() == spw1:
            print("spw1: ",password,"- sha384")
        elif hashlib.sha512(salted_pass).digest() == spw1:
            print("spw1: ",password,"- sha512")

        if hashlib.md5(salted_pass).digest() == spw2:
            print("spw2:",password,"- md5")
        elif hashlib.sha1(salted_pass).digest() == spw2:
            print("spw2:",password,"- sha1")
        elif hashlib.sha224(salted_pass).digest() == spw2:
            print("spw2: ",password,"- sha224")
        elif hashlib.sha256(salted_pass).digest() == spw2:
            print("spw2: ",password,"- sha256")
        elif hashlib.sha384(salted_pass).digest() == spw2:
            print("spw2: ",password,"- sha384")
        elif hashlib.sha512(salted_pass).digest() == spw2:
            print("spw2: ",password,"- sha512")

        if hashlib.md5(salted_pass).digest() == spw3:
            print("spw3:",password,"- md5")
        elif hashlib.sha1(salted_pass).digest() == spw3:
            print("spw3:",password,"- sha1")
        elif hashlib.sha224(salted_pass).digest() == spw3:
            print("spw3: ",password,"- sha224")
        elif hashlib.sha256(salted_pass).digest() == spw3:
            print("spw3: ",password,"- sha256")
        elif hashlib.sha384(salted_pass).digest() == spw3:
            print("spw3: ",password,"- sha384")
        elif hashlib.sha512(salted_pass).digest() == spw3:
            print("spw3: ",password,"- sha512")
 
print('Finding a match...')
search_dict_hash()
search_dict_salt()
print('Congrats! You solved all 6 passwords')
