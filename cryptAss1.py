key = 'abcdefghijklmnopqrstuvwxyz'

def decrypt(n, ciphertext):
    """Decrypt the string and return the plaintext"""
    result = ''

    for l in ciphertext: 
        try:
            i = (key.index(l) - n) % 26
            result += key[i]
        except ValueError:
            result += l

    return result


for n in range(0,25,1):
   print(decrypt(n,'ycvejqwvhqtdtwvwu'))
