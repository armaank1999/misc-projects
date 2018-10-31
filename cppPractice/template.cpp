#include <iostream> // for endl etc.
#include <cmath> // for math
#include <string> // for strings
#include <cstdlib> //for rand
#include <iomanip> // for setw etc
#include "template.h"

using namespace std;

template <typename T> const Complex<T> operator +(const Complex<T>& x, const Complex<T>& y) {
	Complex<T> result(x.real()+y.real(),x.imag()+y.imag());
	return result;
}

template <typename T> const Complex<T> operator -(const Complex<T>& x, const Complex<T>& y) {
	Complex<T> result(x.real()-y.real(), x.imag()-y.imag());
	return result;
}

template <typename T> const Complex<T> operator *(const Complex<T>& x, const Complex<T>& y) {
	Complex<T> result((x.real()*y.real())-(x.imag()*y.imag()),
				   (x.real()*y.imag())+(x.imag()*y.real()));
	return result;
}

template <typename T> const Complex<T> operator /(const Complex<T>& x, const Complex<T>& y) {
	Complex<T> result(((x.real()*y.real() + x.imag()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())),
				   ((x.imag()*y.real() - x.real()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())));
    return result;
}

int gcd(int a, int b) {
	int div;
	a = abs(a);
	b = abs(b);
	for (int i=(a>b?a:b); i>0; i--) {
		if ((a%i == 0) and (b%i == 0)) {
			div = i;
			i = 0;
		}
	}
	return div;
}

template <typename T> Complex<T>::Complex(const T& real = 0, const T& imaginary = 0) 
:real_(real),
 imaginary_(imaginary) {
}

template <typename T> const T Complex<T>::real() const {
	return real_;
}

template <typename T> const T Complex<T>::imag() const {
	return imaginary_;
}

template <typename T> void Complex<T>::display() const {
	if (imag()>= 0) {
		cout << real() << " + " << imag() << "i ";
	}
	else {
		cout << real() << " - " << abs(imag()) << "i ";
	}
}

int main () {
	Complex <double> girl1(5,7);
	girl1.display();
	Complex <double> girl2(3.5,-2.0);
	girl2.display();
	Complex <double> girl3 = girl1*girl2;
	girl3.display();
}