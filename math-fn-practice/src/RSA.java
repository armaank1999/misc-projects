import java.math.BigInteger;

public class RSA {
    public static final BigInteger exp = new BigInteger(new byte[] {1,0,1});
    public static final BigInteger one = new BigInteger(new byte[] {1});
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
        pp = p1.subtract(one);
        qq = p2.subtract(one);
        phi = pp.multiply(qq).divide(pp.gcd(qq));
        pk = exp;
    }

    public static void main(String[] args) {
        System.out.println(exp);
    }
}
