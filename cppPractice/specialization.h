#include <iostream> // for endl etc.
#include <cmath> // for math
#include <string> // for strings
#include <cstdlib> //for rand
#include <iomanip> // for setw etc

using namespace std;

#ifndef Rational_h
#define Rational_h

// A number with a numerator and a denominator
class Rational
{
public:
	Rational(int num, int den);
	Rational(const Rational&);
	Rational();
	int numr() const;
	int denom() const;
	void display() const;
	void simplify();
private:
	int numerator_;
	int denominator_;
};

template <T> class Arm_Complex
{
public:
	Arm_Complex(const T& real, const T& imaginary);
	Arm_Complex();
	const T real() const;
	const T imag() const;
	void display() const;
private:
	T real_;
	T imaginary_;
};

template <> class Arm_Complex <Rational>
{
public:
	Arm_Complex(int num_r, int den_r, int num_i, int den_i);
	Arm_Complex(const Rational& real, const Rational& imaginary);
	Arm_Complex();
	const Rational real() const;
	const Rational imag() const;
	void display() const;
	void simplify();
private:
	Rational real_;
	Rational imaginary_;
};

// Returns the sum of two Rationals
const Rational operator +(const Rational&, const Rational&);

// Returns the difference of two Rationals
const Rational operator -(const Rational&, const Rational&);

// Returns the product of two Rationals
const Rational operator *(const Rational&, const Rational&);

// Returns the quotient of two Rationals
const Rational operator /(const Rational&, const Rational&);

// Returns the sum of two Arm_Complexs
const Arm_Complex operator +(const Arm_Complex&, const Arm_Complex&);

// Returns the difference of two Arm_Complexs
const Arm_Complex operator -(const Arm_Complex&, const Arm_Complex&);

// Returns the product of two Arm_Complexs
const Arm_Complex operator *(const Arm_Complex&, const Arm_Complex&);

// Returns the quotient of two Arm_Complexs
const Arm_Complex operator /(const Arm_Complex&, const Arm_Complex&);

// Returns GCD of two integers
int gcd(int, int);

// Returns number of digits in an int (e.g. 10 returns 2, -10 returns 3)
int digits (int);
#endif