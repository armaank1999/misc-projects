public class Functions {
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    private static final double ln2 = 0.693147180559945309417232121458;

    public static void main(String[] args) {
        System.out.println(expTaylor(lnApprox(2.71828)));
    }

    // Fast exponentiation
    public static int repeatedSquaring(int x, int y) {
        int returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee *= x;
            }
            x*=x;
            y/=2;
        }
        return returnee;
    }

    public static double repeatedSquaring(double x, int y) {
        double returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee *= x;
            }
            x*=x;
            y/=2;
        }
        return returnee;
    }

    public static int repeatedModSquaring(int x, int y, int m) {
        int returnee = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                returnee = (returnee * x)%m;
            }
            x= (x*x)%m;
            y/=2;
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
        return (interpolated>>52) + (-0.344845*mantissa + 2.024658) * mantissa - 1024.674873;
    }

    // Exponentiation Approximations
    public static double expTaylor(double x) { // Pretty bad runtime wise, especially for large x
        double returnee = 0, term = 1;
        for (int i = 1; term > 0.00001; i++) {
            returnee += term;
            term *= (x/i);
        }
        return returnee;
    }

    public static double expApprox(double x) {
        return powApprox(x/ln2);
    }

    public static double powApprox(double x) {
        return 0;
    }
}
