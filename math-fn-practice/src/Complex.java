public class Complex {
    double a;
    double b;

    public Complex(double real, double imag) {
        a = real;
        b = imag;
    }

    public Complex multiply(Complex that) {
        return new Complex(this.a * that.a - this.b * that.b, this.a * that.b + that.a * this.b);
    }

    public Complex add(Complex that) {
        return new Complex(this.a + that.a, this.b + that.b);
    }

    public void square() {
        double newA = a*a - b*b;
        b = 2*a*b;
        a = newA;
    }

    // Magnitude 1
    public Complex(double angle) {
        a = Functions.cos(angle);
        b = Functions.sin(angle);
    }
}
