import java.math.BigInteger;
import java.util.Random;

// Uses Big Integer for much more size than long, so using many native methods
// Make sure you understand how each thing here would be done with regular longs.
// (GCD and basic ops are easy, modexp already implemented - CRT, monty mod exp harder)
public class RSA {
    public static final BigInteger exp = new BigInteger(new byte[] {1,0,1});
    private static final Random rand = new Random();
    public BigInteger pk;
    private BigInteger sk;
    public BigInteger n;
    private BigInteger phi;
    private BigInteger p;
    private BigInteger pp;
    private BigInteger q;
    private BigInteger qq;

    public RSA(BigInteger p1, BigInteger p2) {
        p = p1;
        q = p2;
        n = p.multiply(q);
        pp = p1.subtract(BigInteger.ONE);
        qq = p2.subtract(BigInteger.ONE);
        phi = pp.multiply(qq).divide(pp.gcd(qq));
        pk = exp;
        sk = pk.modPow(phi.subtract(BigInteger.ONE), n);
    }

    public BigInteger encrypt(BigInteger input) { return input.modPow(pk, n); }

    public BigInteger decrypt(BigInteger cipher) { return cipher.modPow(sk, n); }

    public static void main(String[] args) {
        BigInteger message = BigInteger.valueOf(1234567);
        RSA tester = new RSA(BigInteger.probablePrime(20, rand), BigInteger.probablePrime(20, rand));
        System.out.println(tester.decrypt(tester.encrypt(message)));
        System.out.println(tester.encrypt(tester.decrypt(message)));
    }
}
