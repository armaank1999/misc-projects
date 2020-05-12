import math

def memoize(f):
    memo = {}
    def helper(x):
        if x not in memo:  
            answer = f(x)          
            memo[x] = answer
            return answer
        return memo[x]
    return helper

# Takes input specifically over the natural numbers in order.
def memoize_naturals(f):
    memo = []
    def helper(x):
        if x == 0:
            return f(x)
        if x > len(memo):   
            answer = f(x)    
            memo.append(answer)
            return answer
        return memo[int(x-1)]
    return helper

def get_is_prime():
    primes = []
    def is_prime(x):
        if x == 0:
            return primes
        if x == 1:
            return (1, 1)
        sqrtx = math.sqrt(x)
        for prime in primes:
            if prime > sqrtx:
                break
            div = x/prime
            if div == int(div):
                return (prime, div)
        primes.append(x)
        return (x, 1)
    return is_prime

def get_phi():
    is_prime = get_is_prime()
    def phi(x):
        if x == 0:
            return is_prime(0)
        if x == 1:
            return 1
        div_pair = is_prime(x)
        divided = div_pair[1]/div_pair[0]
        if divided == int(divided):
            return div_pair[0] * phi(div_pair[1])
        return (div_pair[0] - 1) * phi(div_pair[1])
    phi = memoize_naturals(phi)
    return phi

