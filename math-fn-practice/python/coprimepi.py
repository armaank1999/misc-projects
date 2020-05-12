import helpers
import math
import numpy as np

phi = helpers.get_phi()
twophisum = -1
endvalue = 1000000
endsmooth = 100
for i in range(1,endvalue+1):
    twophisum += 2*phi(i)
# After the long calculation, smooth over next values to reduce variance.
sixoverpisq = twophisum/(endvalue*endvalue)
print("6/pi^2 is about " + str(sixoverpisq))
piapprox = math.sqrt(6/sixoverpisq)
print("so pi is about " + str(piapprox))
print("Now doing smoothing.")
phioverrange = np.empty(endsmooth)
phioverrange[0] = twophisum
for i in range(1, endsmooth):
    phioverrange[i] = phioverrange[i-1] + 2*phi(endvalue+i)
for i in range(endsmooth):
    phioverrange[i] = phioverrange[i]/((endvalue+i)*(endvalue+i))
sixoverpisq = np.mean(phioverrange)
print("6/pi^2 is about " + str(sixoverpisq))
piapprox = math.sqrt(6/sixoverpisq)
print("so pi is about " + str(piapprox))
with open("primelist.csv", "w") as output:
    output.write(','.join(map(str, phi(0))))
