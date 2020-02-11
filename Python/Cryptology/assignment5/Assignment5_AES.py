#Seth Weber
#Assignment 5
#

#resorted to using longhanded mapping of each hex 
#character due to truncation of some of the pairs
#that generated leading '0's and crash program
#(example: '0a' truncated to 'a' so s[0] and
#s[1] for hex character mapping would crash because
#s[1] doesn't exist


def subBytes1(NextRoundResult):
    sb0 = ['63','7c','77','7b','f2','6b','6f','c5','30','01','67','2b','fe','d7','ab','76']
    sb1 = ['ca','82','c9','7d','fa','59','47','f0','ad','d4','a2','af','9c','a4','72','c0']
    sb2 = ['b7','fd','93','26','36','3f','f7','cc','34','a5','e5','f1','71','d8','31','15']
    sb3 = ['04','c7','23','c3','18','96','05','9a','07','12','80','e2','eb','27','b2','75']
    sb4 = ['09','83','2c','1a','1b','6e','5a','a0','52','3b','d6','b3','29','e3','2f','84']
    sb5 = ['53','d1','00','ed','20','fc','b1','5b','6a','cb','be','39','4a','4c','58','cf']
    sb6 = ['d0','ef','aa','fb','43','4d','33','85','45','f9','02','7f','50','3c','9f','a8']
    sb7 = ['51','a3','40','8f','92','9d','38','f5','bc','b6','da','21','10','ff','f3','d2']
    sb8 = ['cd','0c','13','ec','5f','97','44','17','c4','a7','7e','3d','64','5d','19','73']
    sb9 = ['60','81','4f','dc','22','2a','90','88','46','ee','b8','14','de','5e','0b','db']
    sbA = ['e0','32','3a','0a','49','06','24','5c','c2','d3','ac','62','91','95','e4','79']
    sbB = ['e7','c8','37','6d','8d','d5','4e','a9','6c','56','f4','ea','65','7a','ae','08']
    sbC = ['ba','78','25','2e','1c','a6','b4','c6','e8','dd','74','1f','4b','bd','8b','8a']
    sbD = ['70','3e','b5','66','48','03','f6','0e','61','35','57','b9','86','c1','1d','9e']
    sbE = ['e1','f8','98','11','69','d9','8e','94','9b','1e','87','e9','ce','55','28','df']
    sbF = ['8c','a1','89','0d','bf','e6','42','68','41','99','2d','0f','b0','54','bb','16']
    
    r1 = NextRoundResult[0:8]
    r2 = NextRoundResult[8:16]
    r3 = NextRoundResult[16:24]
    r4 = NextRoundResult[24:32]

    b1 = r1[0:2]
    b2 = r1[2:4]
    b3 = r1[4:6]
    b4 = r1[6:8]
    b5 = r2[0:2]
    b6 = r2[2:4]
    b7 = r2[4:6]
    b8 = r2[6:8]
    b9 = r3[0:2]
    b10 = r3[2:4]
    b11 = r3[4:6]
    b12 = r3[6:8]
    b13 = r4[0:2]
    b14 = r4[2:4]
    b15 = r4[4:6]
    b16 = r4[6:8]

    byte1=''
    lst_bytes = [b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16]

    '''Redo if time'''
    for byte in lst_bytes:
        if (byte[0]=='0'):
            if (byte[1]=='0'):
                byte1=byte1+sb0[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb0[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb0[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb0[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb0[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb0[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb0[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb0[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb0[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb0[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb0[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb0[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb0[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb0[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb0[14]
            else:
                byte1=byte1+sb0[15]
        elif (byte[0]=='1'):
            if (byte[1]=='0'):
                byte1=byte1+sb1[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb1[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb1[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb1[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb1[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb1[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb1[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb1[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb1[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb1[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb1[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb1[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb1[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb1[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb1[14]
            else:
                byte1=byte1+sb1[15]
        elif (byte[0]=='2'):
            if (byte[1]=='0'):
                byte1=byte1+sb2[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb2[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb2[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb2[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb2[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb2[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb2[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb2[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb2[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb2[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb2[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb2[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb2[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb2[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb2[14]
            else:
                byte1=byte1+sb2[15]
        elif (byte[0]=='3'):
            if (byte[1]=='0'):
                byte1=byte1+sb3[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb3[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb3[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb3[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb3[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb3[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb3[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb3[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb3[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb3[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb3[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb3[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb3[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb3[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb3[14]
            else:
                byte1=byte1+sb3[15]
        elif (byte[0]=='4'):
            if (byte[1]=='0'):
                byte1=byte1+sb4[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb4[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb4[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb4[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb4[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb4[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb4[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb4[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb4[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb4[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb4[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb4[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb4[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb4[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb4[14]
            else:
                byte1=byte1+sb4[15]
        elif (byte[0]=='5'):
            if (byte[1]=='0'):
                byte1=byte1+sb5[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb5[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb5[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb5[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb5[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb5[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb5[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb5[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb5[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb5[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb5[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb5[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb5[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb5[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb5[14]
            else:
                byte1=byte1+sb5[15]
        elif (byte[0]=='6'):
            if (byte[1]=='0'):
                byte1=byte1+sb6[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb6[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb6[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb6[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb6[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb6[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb6[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb6[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb6[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb6[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb6[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb6[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb6[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb6[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb6[14]
            else:
                byte1=byte1+sb6[15]
        elif (byte[0]=='7'):
            if (byte[1]=='0'):
                byte1=byte1+sb7[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb7[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb7[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb7[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb7[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb7[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb7[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb7[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb7[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb7[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb7[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb7[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb7[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb7[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb7[14]
            else:
                byte1=byte1+sb7[15]
        elif (byte[0]=='8'):
            if (byte[1]=='0'):
                byte1=byte1+sb8[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb8[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb8[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb8[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb8[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb8[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb8[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb8[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb8[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb8[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb8[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb8[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb8[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb8[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb8[14]
            else:
                byte1=byte1+sb8[15]
        elif (byte[0]=='9'):
            if (byte[1]=='0'):
                byte1=byte1+sb9[0]
            elif (byte[1]=='1'):
                byte1=byte1+sb9[1]
            elif (byte[1]=='2'):
                byte1=byte1+sb9[2]
            elif (byte[1]=='3'):
                byte1=byte1+sb9[3]
            elif (byte[1]=='4'):
                byte1=byte1+sb9[4]
            elif (byte[1]=='5'):
                byte1=byte1+sb9[5]
            elif (byte[1]=='6'):
                byte1=byte1+sb9[6]
            elif (byte[1]=='7'):
                byte1=byte1+sb9[7]
            elif (byte[1]=='8'):
                byte1=byte1+sb9[8]
            elif (byte[1]=='9'):
                byte1=byte1+sb9[9]
            elif (byte[1]=='a'):
                byte1=byte1+sb9[10]
            elif (byte[1]=='b'):
                byte1=byte1+sb9[11]
            elif (byte[1]=='c'):
                byte1=byte1+sb9[12]
            elif (byte[1]=='d'):
                byte1=byte1+sb9[13]
            elif (byte[1]=='e'):
                byte1=byte1+sb9[14]
            else:
                byte1=byte1+sb9[15]
        elif (byte[0]=='a'):
            if (byte[1]=='0'):
                byte1=byte1+sbA[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbA[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbA[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbA[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbA[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbA[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbA[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbA[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbA[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbA[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbA[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbA[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbA[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbA[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbA[14]
            else:
                byte1=byte1+sbA[15]
        elif (byte[0]=='b'):
            if (byte[1]=='0'):
                byte1=byte1+sbB[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbB[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbB[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbB[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbB[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbB[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbB[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbB[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbB[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbB[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbB[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbB[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbB[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbB[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbB[14]
            else:
                byte1=byte1+sbB[15]
        elif (byte[0]=='c'):
            if (byte[1]=='0'):
                byte1=byte1+sbC[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbC[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbC[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbC[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbC[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbC[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbC[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbC[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbC[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbC[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbC[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbC[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbC[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbC[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbC[14]
            else:
                byte1=byte1+sbC[15]
        elif (byte[0]=='d'):
            if (byte[1]=='0'):
                byte1=byte1+sbD[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbD[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbD[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbD[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbD[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbD[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbD[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbD[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbD[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbD[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbD[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbD[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbD[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbD[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbD[14]
            else:
                byte1=byte1+sbD[15]
        elif (byte[0]=='e'):
            if (byte[1]=='0'):
                byte1=byte1+sbE[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbE[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbE[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbE[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbE[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbE[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbE[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbE[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbE[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbE[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbE[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbE[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbE[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbE[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbE[14]
            else:
                byte1=byte1+sbE[15]
        else:
            if (byte[1]=='0'):
                byte1=byte1+sbF[0]
            elif (byte[1]=='1'):
                byte1=byte1+sbF[1]
            elif (byte[1]=='2'):
                byte1=byte1+sbF[2]
            elif (byte[1]=='3'):
                byte1=byte1+sbF[3]
            elif (byte[1]=='4'):
                byte1=byte1+sbF[4]
            elif (byte[1]=='5'):
                byte1=byte1+sbF[5]
            elif (byte[1]=='6'):
                byte1=byte1+sbF[6]
            elif (byte[1]=='7'):
                byte1=byte1+sbF[7]
            elif (byte[1]=='8'):
                byte1=byte1+sbF[8]
            elif (byte[1]=='9'):
                byte1=byte1+sbF[9]
            elif (byte[1]=='a'):
                byte1=byte1+sbF[10]
            elif (byte[1]=='b'):
                byte1=byte1+sbF[11]
            elif (byte[1]=='c'):
                byte1=byte1+sbF[12]
            elif (byte[1]=='d'):
                byte1=byte1+sbF[13]
            elif (byte[1]=='e'):
                byte1=byte1+sbF[14]
            else:
                byte1=byte1+sbF[15]
    return byte1

def shiftRows(subBytesResult):
    r1 = subBytesResult[0:8]
    r2 = subBytesResult[8:16]
    r3 = subBytesResult[16:24]
    r4 = subBytesResult[24:32]

    newr2 = r2[2:8]+r2[0:2]
    newr3 = r3[4:8]+r3[0:4]
    newr4 = r4[6:8]+r4[0:6]

    shiftRowsResult = r1+newr2+newr3+newr4
    return shiftRowsResult

def mixColumns(shiftRowsResult):
    c1 = shiftRowsResult[0:2]+shiftRowsResult[8:10]+shiftRowsResult[16:18]+shiftRowsResult[24:26]
    c2 = shiftRowsResult[2:4]+shiftRowsResult[10:12]+shiftRowsResult[18:20]+shiftRowsResult[26:28]
    c3 = shiftRowsResult[4:6]+shiftRowsResult[12:14]+shiftRowsResult[20:22]+shiftRowsResult[28:30]
    c4 = shiftRowsResult[6:8]+shiftRowsResult[14:16]+shiftRowsResult[22:24]+shiftRowsResult[30:32]
    lst_c = [c1,c2,c3,c4]

    mixColumnsResult=''
    newByte1=''
    newByte2=''
    newByte3=''
    newByte4=''
    r1=''
    r2=''
    r3=''
    r4=''
    
    for col in lst_c:
        byte1 = int(col[0:2],16)
        byte2 = int(col[2:4],16)
        byte3 = int(col[4:6],16)
        byte4 = int(col[6:8],16)
        
        def multiplyBy2(byte):
            newByte2 = (byte<<1)
            if (newByte2 & 0x100) != 0:
                newByte2 ^= 0x11b
            return newByte2

        def multiplyBy3(byte):
            return multiplyBy2(byte)^byte

        newByte1 = (multiplyBy2(byte1)) ^ (multiplyBy3(byte2)) ^ (byte3) ^ (byte4)
        newByte1 = (hex(newByte1))[2:]

        if(newByte1 == '1'):
            newByte1 = '01'
        elif(newByte1 == '2'):
            newByte1 = '02'
        elif(newByte1 == '3'):
            newByte1 = '03'
        elif(newByte1 == '4'):
            newByte1 = '04'
        elif(newByte1 == '5'):
            newByte1 = '05'
        elif(newByte1 == '6'):
            newByte1 = '06'
        elif(newByte1 == '7'):
            newByte1 = '07'
        elif(newByte1 == '8'):
            newByte1 = '08'
        elif(newByte1 == '9'):
            newByte1 = '09'
        elif(newByte1 == 'a'):
            newByte1 = '0a'
        elif(newByte1 == 'b'):
            newByte1 = '0b'
        elif(newByte1 == 'c'):
            newByte1 = '0c'
        elif(newByte1 == 'd'):
            newByte1 = '0d'
        elif(newByte1 == 'e'):
            newByte1 = '0e'
        elif(newByte1 == 'f'):
            newByte1 = '0f'

        newByte2 = (byte1) ^ (multiplyBy2(byte2)) ^ (multiplyBy3(byte3)) ^ (byte4)
        newByte2 = (hex(newByte2))[2:]

        if(newByte2 == '1'):
            newByte2 = '01'
        elif(newByte2 == '2'):
            newByte2 = '02'
        elif(newByte2 == '3'):
            newByte2 = '03'
        elif(newByte2 == '4'):
            newByte2 = '04'
        elif(newByte2 == '5'):
            newByte2 = '05'
        elif(newByte2 == '6'):
            newByte2 = '06'
        elif(newByte2 == '7'):
            newByte2 = '07'
        elif(newByte2 == '8'):
            newByte2 = '08'
        elif(newByte2 == '9'):
            newByte2 = '09'
        elif(newByte2 == 'a'):
            newByte2 = '0a'
        elif(newByte2 == 'b'):
            newByte2 = '0b'
        elif(newByte2 == 'c'):
            newByte2 = '0c'
        elif(newByte2 == 'd'):
            newByte2 = '0d'
        elif(newByte2 == 'e'):
            newByte2 = '0e'
        elif(newByte2 == 'f'):
            newByte2 = '0f'

        newByte3 = (byte1) ^ (byte2) ^ (multiplyBy2(byte3)) ^ (multiplyBy3(byte4))
        newByte3 = (hex(newByte3))[2:]

        if(newByte3 == '1'):
            newByte3 = '01'
        elif(newByte3 == '2'):
            newByte3 = '02'
        elif(newByte3 == '3'):
            newByte3 = '03'
        elif(newByte3 == '4'):
            newByte3 = '04'
        elif(newByte3 == '5'):
            newByte3 = '05'
        elif(newByte3 == '6'):
            newByte3 = '06'
        elif(newByte3 == '7'):
            newByte3 = '07'
        elif(newByte3 == '8'):
            newByte3 = '08'
        elif(newByte3 == '9'):
            newByte3 = '09'
        elif(newByte3 == 'a'):
            newByte3 = '0a'
        elif(newByte3 == 'b'):
            newByte3 = '0b'
        elif(newByte3 == 'c'):
            newByte3 = '0c'
        elif(newByte3 == 'd'):
            newByte3 = '0d'
        elif(newByte3 == 'e'):
            newByte3 = '0e'
        elif(newByte3 == 'f'):
            newByte3 = '0f'

        newByte4 = (multiplyBy3(byte1)) ^ (byte2) ^ (byte3) ^ (multiplyBy2(byte4))
        newByte4 = (hex(newByte4))[2:]

        if(newByte4 == '1'):
            newByte4 = '01'
        elif(newByte4 == '2'):
            newByte4 = '02'
        elif(newByte4 == '3'):
            newByte4 = '03'
        elif(newByte4 == '4'):
            newByte4 = '04'
        elif(newByte4 == '5'):
            newByte4 = '05'
        elif(newByte4 == '6'):
            newByte4 = '06'
        elif(newByte4 == '7'):
            newByte4 = '07'
        elif(newByte4 == '8'):
            newByte4 = '08'
        elif(newByte4 == '9'):
            newByte4 = '09'
        elif(newByte4 == 'a'):
            newByte4 = '0a'
        elif(newByte4 == 'b'):
            newByte4 = '0b'
        elif(newByte4 == 'c'):
            newByte4 = '0c'
        elif(newByte4 == 'd'):
            newByte4 = '0d'
        elif(newByte4 == 'e'):
            newByte4 = '0e'
        elif(newByte4 == 'f'):
            newByte4 = '0f'

        r1 += newByte1
        r2 += newByte2
        r3 += newByte3
        r4 += newByte4

        mixColumnsResult = r1+r2+r3+r4

    return str(mixColumnsResult)

def startNextRound(mixColumnsResult,key):
        key = int(key,16)
        mixColumnsResult = int(mixColumnsResult,16)
        startNextRoundResult = bin(mixColumnsResult ^ key)
        startNextRoundResult = str(hex(int(startNextRoundResult,2)))
        return startNextRoundResult

'''hex plaintext'''
plaintext = '328831e0435a3137f6309807a88da234'
print("=== Plaintext ===")
print(plaintext[0:2]," ",plaintext[2:4]," ",plaintext[4:6]," ",plaintext[6:8])
print(plaintext[8:10]," ",plaintext[10:12]," ",plaintext[12:14]," ",plaintext[14:16])
print(plaintext[16:18]," ",plaintext[18:20]," ",plaintext[20:22]," ",plaintext[22:24])
print(plaintext[24:26]," ",plaintext[26:28]," ",plaintext[28:30]," ",plaintext[30:32],"\n\n")

'''key schedule'''
lst_keys = ['2b28ab097eaef7cf15d2154f16a6883c','a088232afa54a36cfe2c397617b13905','f27a5973c296355995b980f6f2437a7f','3d471e6d8016237a47fe7e887d3e443b','efa8b6db4452710ba55b25ad417f3b00','d47cca11d183f2f9c69db815f887bcbc','6d11dbca880bf900a33e86937afd41fd','4e5f844e545fa6a6f7c94fdc0ef3b24f','eab5317fd28d2b8d73baf52921d2602f','ac19285777fad15c66dc2900f321416e','d0c9e1b614ee3f63f9250c0ca889c8a6']

mixColumnsResult=''

for key in lst_keys:
    if lst_keys.index(key)==10:
        continue

    print("=== Round ",lst_keys.index(key)+1," ===")

    print("--- Start of round ---")
    if lst_keys.index(key)==0 and key == '2b28ab097eaef7cf15d2154f16a6883c':
        plaintext = int(plaintext,16)
        key = int(key,16)
        exor = bin(plaintext^key)
        startOfRound = str(hex(int(exor[:128],2)))
        startOfRound = startOfRound[2:]
        print(startOfRound[0:2]," ",startOfRound[2:4]," ",startOfRound[4:6]," ",startOfRound[6:8])
        print(startOfRound[8:10]," ",startOfRound[10:12]," ",startOfRound[12:14]," ",startOfRound[14:16])
        print(startOfRound[16:18]," ",startOfRound[18:20]," ",startOfRound[20:22]," ",startOfRound[22:24])
        print(startOfRound[24:26]," ",startOfRound[26:28]," ",startOfRound[28:30]," ",startOfRound[30:32],"\n")
        subBytesResult = subBytes1(startOfRound)
    else:
        mixColumnsResult = int(mixColumnsResult,16)
        key = int(key,16)
        startNextRoundResult = mixColumnsResult ^ key
        startNextRoundResult = hex(startNextRoundResult)[2:]
        print(startNextRoundResult[0:2]," ",startNextRoundResult[2:4]," ",startNextRoundResult[4:6]," ",startNextRoundResult[6:8])
        print(startNextRoundResult[8:10]," ",startNextRoundResult[10:12]," ",startNextRoundResult[12:14]," ",startNextRoundResult[14:16])
        print(startNextRoundResult[16:18]," ",startNextRoundResult[18:20]," ",startNextRoundResult[20:22]," ",startNextRoundResult[22:24])
        print(startNextRoundResult[24:26]," ",startNextRoundResult[26:28]," ",startNextRoundResult[28:30]," ",startNextRoundResult[30:32],"\n")
        subBytesResult = subBytes1(startNextRoundResult)

    print("--- After subBytes ---")
    print(subBytesResult[0:2]," ",subBytesResult[2:4]," ",subBytesResult[4:6]," ",subBytesResult[6:8])
    print(subBytesResult[8:10]," ",subBytesResult[10:12]," ",subBytesResult[12:14]," ",subBytesResult[14:16])
    print(subBytesResult[16:18]," ",subBytesResult[18:20]," ",subBytesResult[20:22]," ",subBytesResult[22:24])
    print(subBytesResult[24:26]," ",subBytesResult[26:28]," ",subBytesResult[28:30]," ",subBytesResult[30:32],"\n")
    shiftRowsResult = shiftRows(subBytesResult)
    print("--- After shiftRows ---")
    print(shiftRowsResult[0:2]," ",shiftRowsResult[2:4]," ",shiftRowsResult[4:6]," ",shiftRowsResult[6:8])
    print(shiftRowsResult[8:10]," ",shiftRowsResult[10:12]," ",shiftRowsResult[12:14]," ",shiftRowsResult[14:16])
    print(shiftRowsResult[16:18]," ",shiftRowsResult[18:20]," ",shiftRowsResult[20:22]," ",shiftRowsResult[22:24])
    print(shiftRowsResult[24:26]," ",shiftRowsResult[26:28]," ",shiftRowsResult[28:30]," ",shiftRowsResult[30:32],"\n")
    mixColumnsResult = mixColumns(shiftRowsResult)
    print("--- After mixColumns ---")
    print(mixColumnsResult[0:2]," ",mixColumnsResult[2:4]," ",mixColumnsResult[4:6]," ",mixColumnsResult[6:8])
    print(mixColumnsResult[8:10]," ",mixColumnsResult[10:12]," ",mixColumnsResult[12:14]," ",mixColumnsResult[14:16])
    print(mixColumnsResult[16:18]," ",mixColumnsResult[18:20]," ",mixColumnsResult[20:22]," ",mixColumnsResult[22:24])
    print(mixColumnsResult[24:26]," ",mixColumnsResult[26:28]," ",mixColumnsResult[28:30]," ",mixColumnsResult[30:32],"\n\n")

shiftRowsResult = int(shiftRowsResult,16)
key = int(key,16)
ciphertext = shiftRowsResult ^ key
ciphertext = hex(ciphertext)[2:]
print("=== Ciphertext ===")  
print(ciphertext[0:2]," ",ciphertext[2:4]," ",ciphertext[4:6]," ",ciphertext[6:8])
print(ciphertext[8:10]," ",ciphertext[10:12]," ",ciphertext[12:14]," ",ciphertext[14:16])
print(ciphertext[16:18]," ",ciphertext[18:20]," ",ciphertext[20:22]," ",ciphertext[22:24])
print(ciphertext[24:26]," ",ciphertext[26:28]," ",ciphertext[28:30]," ",ciphertext[30:32],"\n")
