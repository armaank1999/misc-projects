public class FFT {
    // Polynomial arrays start at the constant term. Degree = array.length - 1
    public static void main(String[] args) {

    }

    public static Complex[] fftMul(Complex[] a, Complex[] b) {
        int newLength = Integer.highestOneBit(a.length + b.length - 2) << 1;
        Complex[] aConvert = fftIze(a, newLength);
        Complex[] bConvert = fftIze(b, newLength);
        Complex[] result = new Complex[newLength];
        for (int i = 0; i < newLength; i++) result[i] = aConvert[i].multiply(bConvert[i]);
        return unfftIze(result, newLength);
    }

    public static Complex[] fftIze(Complex[] coeff, int newLength) {
        Complex firstFactor = new Complex(Math.PI/(newLength >> 1));
    }

    public static Complex[] unfftIze(Complex[] coeff, int newLength) {
        Complex firstFactor = new Complex(Math.PI/(-newLength >> 1));
    }
}
