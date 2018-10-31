public class Functions {
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    private static final double ln2 = 0.693147180559945309417232121458;
    private static final double pi = 3.1415926535897932384626433832795;
    private static final double tau = 6.283185307179586476925286766559;

    public static void main(String[] args) {
        System.out.println(repeatedSquaring(cos(pi/4),2));
    }

    // Fast exponentiation to integer powers
    public static long repeatedSquaring(long x, long y) {
        long returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee *= x;
            }
            x *= x;
            y /= 2;
        }
        return returnee;
    }

    public static double repeatedSquaring(double x, long y) {
        double returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee *= x;
            }
            x *= x;
            y /= 2;
        }
        return returnee;
    }

    public static long repeatedModSquaring(long x, long y, long m) {
        long returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee = (returnee * x) % m;
            }
            x = (x * x) % m;
            y /= 2;
        }
        return returnee;
    }

    // Log Approximations
    public static double lnApprox(double x) {
        return ln2 * logApprox(x);
    }

    public static double logApprox(double x) {
        long interpolated = java.lang.Double.doubleToLongBits(x);
        double mantissa = java.lang.Double.longBitsToDouble((interpolated & 0x000fffffffffffffL) | 0x3ff0000000000000L);
        return (interpolated >> 52) + (-0.344845 * mantissa + 2.024658) * mantissa - 1024.674873;
    }

    // Exponentiation Approximations
    public static double expTaylor(double x) { // Pretty bad runtime wise, especially for large x
        double returnee = 0, term = 1;
        for (int i = 1; term > 0.00001; i++) {
            returnee += term;
            term *= (x / i);
        }
        return returnee;
    }

    public static double expApprox(double x) {
        return twoPowApprox(x/ln2);
    }

    public static double twoPowApprox(double x) {
        if (x < 0) return 1/ twoPowApprox(-x);
        int rounded = (int) x;
        double mantissa = x - rounded;
        return (1 << rounded) * ((((((0.000218632165342569 * mantissa + 0.00123848207294941) * mantissa + 0.00968589633703232) * mantissa +
            0.0554790813475847) * mantissa + 0.240231039235368) * mantissa + 0.693146848920151) * mantissa + 1.00000000529826);
    }

    public static double powApprox(double x, double y) {
        return twoPowApprox(y * logApprox(x));
    }

    public static double nthRoot(double x, int n) {
        return 0;
    }

    public static double inversenthRoot(double x, int n) {
        return 0;
    }

    public static double sin(double x) {
        double returnee = x, factor = x;
        int i = 2;
        x = x * x;
        while (factor > .00000000001) {
            factor *= x/(i++*i++);
            returnee -= factor;
            factor *= x/(i++*i++);
            returnee += factor;
        }
        return returnee;
    }

    public static double cos(double x) {
        double returnee = 1, factor = 1;
        int i = 1;
        x = x * x;
        while (factor > .00000000001) {
            factor *= x/(i++*i++);
            returnee -= factor;
            factor *= x/(i++*i++);
            returnee += factor;
        }
        return returnee;
    }
}
