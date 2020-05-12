import math
pisqoversix = 0
numterms = 500000
for i in range(1,numterms):
    pisqoversix += 1/(i*i)
pisqoversix += 1/numterms # The integral then lower bounds remaining sum.
print("pi^2/6 is about " + str(pisqoversix))
piapprox = math.sqrt(pisqoversix*6)
print("so pi is about " + str(piapprox))