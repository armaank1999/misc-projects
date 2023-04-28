import math
pisqoversix = 0
numterms = 500000
for i in range(1,numterms):
    pisqoversix += 1/(i*i)
pisqoversix += 1/numterms # The integral then lower bounds remaining sum.
print(f"pi^2/6 is about {pisqoversix}")
piapprox = math.sqrt(pisqoversix*6)
print(f"so pi is about {piapprox}")