public class Complex {
    double a;
    double b;

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Complex multiply(Complex that) {
        return new Complex(this.a * that.a - this.b * that.b, this.a * that.b + that.a * this.b);
    }

    public Complex add(Complex that) {
        return new Complex(this.a + that.a, this.b + that.b);
    }

    // Magnitude 1
    public Complex(double angle) {
        this.a = Functions.cos(angle);
        this.b = Functions.sin(angle);
    }
}
