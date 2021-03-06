public class Functions {
    //<editor-fold desc="Precomputed constants">
    private static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L,
        87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    private static final double ln2 = 0.693147180559945309417232121458;
    //</editor-fold>

    public static void main(String[] args) {
        System.out.println(repeatedSquaring(cos(Math.PI/4),2));
        System.out.println(shiftDouble(1048576, -20));
    }

    // Exponentiation to integer powers. 61C inspired shifts and loop unrolling
    public static long repeatedSquaring(long x, long y) {
        long returnee = 1;
        while (y > 0) {
            if ((y & 1) == 1) returnee *= x;
            x *= x;
            y = y >> 1;
            if ((y & 1) == 1) returnee *= x;
            x *= x;
            y = y >> 1;
        }
        return returnee;
    }

    public static double repeatedSquaring(double x, long y) {
        double returnee = 1;
        while (y > 0) {
            if ((y & 1) == 1) returnee *= x;
            x *= x;
            y = y >> 1;
            if ((y & 1) == 1) returnee *= x;
            x *= x;
            y = y >> 1;
        }
        return returnee;
    }

    public static long repeatedModSquaring(long x, long y, long m) {
        long returnee = 1;
        while (y > 0) {
            if ((y & 1) == 1) returnee = (returnee * x) % m;
            x = (x * x) % m;
            y = y >> 1;
            if ((y & 1) == 1) returnee = (returnee * x) % m;
            x = (x * x) % m;
            y = y >> 1;
        }
        return returnee;
    }

    // This function is slower than regular mul in Java but illustrates a fun concept used below
    // Works for negative shifts as left shifts too
    public static double shiftDouble(double x, long y) {
        return Double.longBitsToDouble(Double.doubleToLongBits(x) + (y << 52));
    }

    // Root - inspired by fast inv sqrt
    public static double nthRoot(double x, int n) {
        return 0;
    }

    public static double inverseRoot(double x, int n) {
        return 0;
    }

    // Log Approximations
    public static double lnApprox(double x) { return ln2 * logApprox(x); }

    public static double logApprox(double x) {
        long interpolated = Double.doubleToLongBits(x);
        double mantissa = Double.longBitsToDouble((interpolated & 0x000fffffffffffffL) | 0x3ff0000000000000L);
        return (interpolated >> 52) + (-0.344845 * mantissa + 2.024658) * mantissa - 1024.674873;
    }

    // Exponentiation Approximations
    public static double expTaylor(double x) { // Pretty bad runtime wise
        double returnee = 1, term = 1;
        for (int i = 1; term > 0.00001; i++) {
            term *= (x / i);
            returnee += term;
        }
        return returnee;
    }

    public static double expApprox(double x) { return twoPowApprox(x/ln2); }

    public static double twoPowApprox(double x) {
        if (x < 0) return 1/ twoPowApprox(-x);
        int rounded = (int) x;
        double mantissa = x - rounded;
        return (1 << rounded) * ((((((0.000218632165342569 * mantissa + 0.00123848207294941) * mantissa + 0.00968589633703232) * mantissa +
            0.0554790813475847) * mantissa + 0.240231039235368) * mantissa + 0.693146848920151) * mantissa + 1.00000000529826);
    }

    public static double powApprox(double x, double y) { return twoPowApprox(y * logApprox(x)); }

    // Trig by Taylor series
    public static double sin(double x) {
        double returnee = x, factor = x;
        int i = 2;
        x = x * x;
        while (factor > .0000000000000001) {
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
        while (factor > .0000000000000001) {
            factor *= x/(i++*i++);
            returnee -= factor;
            factor *= x/(i++*i++);
            returnee += factor;
        }
        return returnee;
    }

    public static double sec(double x) { return 1/cos(x); }

    public static double csc(double x) { return 1/sin(x); }

    public static double cot(double x) { return cos(x)/sin(x); }

    public static double tan(double x) { return sin(x)/cos(x); }
}
