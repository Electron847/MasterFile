nucleotides = ["A", "C", "G", "T"]
new_list = []

for nucleotide in nucleotides:
    x = nucleotides.index(nucleotide)
    new_nuke = (3 * x + 1) % 4
   # print (nucleotide)
   # print (x)
   # print (new_nuke)
    new_list.insert(new_nuke, nucleotide)

print("Original list of nucleotides is", nucleotides)
print("After implementation of the affine function 3x + 1 % 4 is", new_list)

    
