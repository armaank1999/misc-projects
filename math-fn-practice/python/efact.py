eapprox = 0
numterms = 500
currmult = 1
for i in range(1,numterms):
    eapprox += currmult
    currmult /= i
print(f"e is about {eapprox}")