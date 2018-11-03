import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

// Uses Big Integer for much more size than long, so using many native methods
// Make sure you understand how each thing here would be done with regular longs.
// (GCD and basic ops are easy, modexp already implemented, karatsuba mul understood - CRT, monty mod exp harder)
public class RSA {
    public static final BigInteger exp = new BigInteger(new byte[] {1,0,1});
    private static final Random rand = new Random();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm");
    public String name;
    public BigInteger pk;
    private BigInteger sk;
    public BigInteger n;
    private BigInteger phi;
    private BigInteger p;
    private BigInteger pp;
    private BigInteger q;
    private BigInteger qq;

    public RSA(String name, BigInteger p1, BigInteger p2) {
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

    private BigInteger decrypt(BigInteger cipher) { return cipher.modPow(sk, n); }

    private BigInteger giveSignature() {
        return decrypt(messagify(name + " on " + formatter.format(new Date())));
    }

    public static void main(String[] args) {
//        BigInteger message = BigInteger.valueOf(1234567);
//        RSA tester = new RSA("sif", BigInteger.probablePrime(60, rand), BigInteger.probablePrime(60, rand));
//        System.out.println(tester.decrypt(tester.encrypt(message)));
//        System.out.println(tester.encrypt(tester.decrypt(message)));
        System.out.println(messagify("testing how long this is"));
    }

    public static BigInteger messagify(String message) { return new BigInteger(message.getBytes()); }

    public static String demessagify(BigInteger result) { return new String(result.toByteArray()); }
}
