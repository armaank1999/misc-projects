#include <iostream> // for endl etc.
#include <cmath> // for math
#include <string> // for strings
#include <cstdlib> //for rand
#include <iomanip> // for setw etc

using namespace std;

#ifndef Rational_h
#define Rational_h

template <typename T> class Complex
{
public:
	Complex(const T& real, const T& imaginary);
	const T real() const;
	const T imag() const;
	void display() const;
private:
	T real_;
	T imaginary_;
};

// Returns the sum of two Complexs
template <typename T> const Complex<T> operator +(const Complex<T>&, const Complex<T>&);

// Returns the difference of two Complexs
template <typename T> const Complex<T> operator -(const Complex<T>&, const Complex<T>&);

// Returns the product of two Complexs
template <typename T> const Complex<T> operator *(const Complex<T>&, const Complex<T>&);

// Returns the quotient of two Complexs
template <typename T> const Complex<T> operator /(const Complex<T>&, const Complex<T>&);

// Returns GCD of two integers
int gcd(int, int);
#endif