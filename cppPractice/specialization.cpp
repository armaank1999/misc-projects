#include <iostream> // for endl etc.
#include <cmath> // for math
#include <string> // for strings
#include <cstdlib> //for rand
#include <iomanip> // for setw etc
#include "rational.h"

using namespace std;

const Rational operator +(const Rational& x, const Rational& y) {
	Rational result((x.numr()*y.denom())+(y.numr()*x.denom()),
					 x.denom()*y.denom());
	result.simplify();
	return result;
}

const Rational operator -(const Rational& x, const Rational& y) {
	Rational result((x.numr()*y.denom())-(y.numr()*x.denom()),
					 x.denom()*y.denom());
	result.simplify();
	return result;
}

const Rational operator *(const Rational& x, const Rational& y) {
	Rational result(x.numr()*y.numr(),x.denom()*y.denom());
	result.simplify();
	return result;
}

const Rational operator /(const Rational& x, const Rational& y) {
	Rational result(x.numr()*y.denom(),x.denom()*y.numr());
	result.simplify();
	return result;
}

template <typename T> const T Arm_Complex operator +(const Arm_Complex<T>& x, const Arm_Complex<T>& y) {
	Arm_Complex result(x.real()+y.real(),x.imag()+y.imag());
	return result;
}

template <> const Rational Arm_Complex operator +(const Arm_Complex<Rational>& x, const Arm_Complex<Rational>& y) {
	Arm_Complex result(x.real()+y.real(),x.imag()+y.imag());
	result.simplify();
	return result;
}

template <typename T> const T Arm_Complex operator -(const Arm_Complex<T>& x, const Arm_Complex<T>& y) {
	Arm_Complex result(x.real()-y.real(), x.imag()-y.imag());
	return result;
} 

template <> const Rational Arm_Complex <Rational> operator -(const Arm_Complex<Rational>& x, const Arm_Complex<Rational>& y) {
	Arm_Complex result(x.real()-y.real(),x.imag()-y.imag());
	result.simplify();
	return result;
}

template <typename T> const T Arm_Complex operator *(const Arm_Complex<T>& x, const Arm_Complex<T>& y) {
	Arm_Complex result((x.real()*y.real())-(x.imag()*y.imag()),
				   (x.real()*y.imag())+(x.imag()*y.real()));
	return result;
}

template <> const Rational Arm_Complex operator *(const Arm_Complex<Rational>& x, const Arm_Complex<Rational>& y) {
	Arm_Complex result((x.real()*y.real())-(x.imag()*y.imag()),
				   (x.real()*y.imag())+(x.imag()*y.real()));
	result.simplify();
	return result;
}

template <typename T> const T Arm_Complex operator /(const Arm_Complex<T>& x, const Arm_Complex<T>& y) {
	Arm_Complex result(((x.real()*y.real() + x.imag()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())),
				   ((x.imag()*y.real() - x.real()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())));
    return result;
}

template <> const Rational Arm_Complex operator /(const Arm_Complex<Rational>& x, const Arm_Complex<Rational>& y) {
	Arm_Complex result(((x.real()*y.real() + x.imag()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())),
				   ((x.imag()*y.real() - x.real()*y.imag())/(y.real()*y.real()+y.imag()*y.imag())));
	result.simplify();
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

int digits (int n) {
	if (n == 0) {
		return 1;
	}
	int d = 0;
	if (n < 0) {
		// d++;
		n = -n;
	}
	while (n >= 1) {
		d++;
		n = n/10;
	}
	return d;
}

Rational::Rational(int numr, int denom)
:numerator_(numr),
 denominator_(denom) {
}

Rational::Rational(const Rational& rat)
:numerator_(rat.numr()),
 denominator_(rat.denom()) {
}

Rational::Rational()
:numerator_(0),
 denominator_(1){
}
int Rational::numr() const {
	return numerator_;
}

int Rational::denom() const {
	return denominator_;
}

void Rational::simplify() {
	int a = gcd(numerator_,denominator_);
	if (denominator_ < 0) {
		a*=-1;
	}
	numerator_/=a;
	denominator_/=a;
}

void Rational::display() const {
	int len1 = digits(numerator_);
	int len2 = digits(denominator_);
	if (len2 > len1) {
		len1 = len2;
	}
	cout << numerator_ << endl;
	for (int i = 0; i < len1; i++) {
		cout << "_";
	}
	cout << endl;
	cout << denominator_ << endl;
}

template <> Rational Arm_Complex<Rational>::Arm_Complex(int numr_r, int denom_r, int numr_i, int denom_i)
:real_(numr_r, denom_r),
 imaginary_(numr_i, denom_i){
}

template <typename T> T Arm_Complex<T>::Arm_Complex(const T& real, const T& imaginary) 
:real_(real),
 imaginary_(imaginary) {
}

template <> Rational Arm_Complex<Rational>::Arm_Complex(const Rational& real, const Rational& imaginary) 
:real_(real),
 imaginary_(imaginary) {
}

template <typename T> T Arm_Complex<T>::Arm_Complex(){
}

template <> Rational Arm_Complex<Rational>::Arm_Complex(){
}

template <typename T> const T Arm_Complex<T>::real() const {
	return real_;
}

template <> const Rational Arm_Complex<Rational>::real() const {
	return real_;
}

template <typename T> const T Arm_Complex<T>::imag() const {
	return imaginary_;
}

template <> const Rational Arm_Complex<Rational>::imag() const {
	return imaginary_;
}

template <> Rational Arm_Complex<Rational>::simplify() {
	real_.simplify();
	imaginary_.simplify();
}

template <> Rational Arm_Complex<Rational>::display() const {
	int len1 = digits(real_.numr());
	int len2 = digits(real_.denom());
	if (len2 > len1) {
		len1 = len2;
	}
	cout << setw(len1) << real_.numr() << "   ";
	int len3 = digits(imaginary_.numr());
	int len4 = digits(imaginary_.denom());
	if (len4 > len3) {
		len3 = len4;
	}
	cout << setw(len3);
	if (imaginary_.numr() < 0) {
		cout << -(imaginary_.numr()) << endl;
	}
	else {
		cout << imaginary_.numr() << endl;
	}
	for (int i = 0; i < len1; i++) {
		cout << "_";
	}
	if (imaginary_.numr() < 0) {
		cout << " - ";
	}
	else {
		cout << " + ";
	}
	for (int i = 0; i < len3; i++) {
		cout << "_";
	}
	cout << " i" << endl;
	cout << setw(len1) << real_.denom() << "   " << setw(len3) << imaginary_.denom() << endl;
}

template <typename T> void Arm_Complex::display() const {
	if (imaginary_()>= 0) {
		cout << real_ << " + " << imaginary_;
	}
	else {
		cout << real_ << " - " << abs(imaginary_);
	}
}

int main () {
	Complex <Rational> girl1(3,1,4,1);
	Complex <double> girl2(3.5,-2.0);
	girl1.display();
	girl2.display();
}