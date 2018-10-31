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
	Rational(int num = 0, int den = 1);
	Rational(const Rational&);
	int numr() const {return numerator_;};
	int denom() const {return denominator_;};
	void display() const;
	void simplify();
	double decimal() const {return numerator_/denominator_;};
private:
	int numerator_;
	int denominator_;
};

class Complex
{
public:
	Complex(int num_r, int den_r, int num_i, int den_i);
	Complex(const Rational& real = Rational(), const Rational& imaginary = Rational());
	const Rational real() const{return real_;};
	const Rational imag() const{return imaginary_;};
	void display() const;
	void simplify();
private:
	Rational real_;
	Rational imaginary_;
};

class Person
{
public:
	Person(int age = 35, int height = 66);
	virtual ~Person(){cout << "Person dying." << endl;};
	virtual void mytype(){cout << "I am a person." << endl;};
private:
	int age;
	int height;
};

class Student:public Person
{
public:
	Student(Person, double GPA);
	Student(int age = 13, int height = 60, double GPA = 4.0);
	~Student(){cout << "Student graduating." << endl;};
	void mytype(){cout << "I am a student." << endl;};
private:
	double GPA_;
};

// Returns the sum of two Rationals
const Rational operator +(const Rational&, const Rational&);

// Returns the difference of two Rationals
const Rational operator -(const Rational&, const Rational&);

// Returns the product of two Rationals
const Rational operator *(const Rational&, const Rational&);

// Returns the quotient of two Rationals
const Rational operator /(const Rational&, const Rational&);

// Returns the sum of two Complexs
const Complex operator +(const Complex&, const Complex&);

// Returns the difference of two Complexs
const Complex operator -(const Complex&, const Complex&);

// Returns the product of two Complexs
const Complex operator *(const Complex&, const Complex&);

// Returns the quotient of two Complexs
const Complex operator /(const Complex&, const Complex&);

// Returns GCD of two integers
int gcd(int, int);

// Returns number of digits in an int (e.g. 10 returns 2, -10 returns 3)
int digits (int);
#endif