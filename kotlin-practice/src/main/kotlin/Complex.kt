import kotlin.math.atan
import kotlin.math.sqrt
import kotlin.math.PI

data class Complex(var real: Double, var imag: Double) {
    constructor(real: Number, imag: Number) : this(real.toDouble(), imag.toDouble())
    constructor(real: Number) : this(real.toDouble(), 0)

    operator fun unaryPlus() = Complex(real, imag)
    operator fun unaryMinus() = Complex(-real, -imag)
    fun conjugate() = Complex(real, -imag)
    fun norm() = sqrt(real*real + imag*imag)
    fun toReitheta() = Reitheta(norm(), if (real == 0.0) (if (imag > 0) PI/2 else -PI/2) else atan(imag/real))

    operator fun plus(other: Complex) = Complex(real + other.real, imag + other.imag)
    operator fun plus(num: Number) = Complex(real + num.toDouble(), imag)
    operator fun plusAssign(other: Complex) {
        real += other.real
        imag += other.imag
    }
    operator fun plusAssign(num: Number) {
        real += num.toDouble()
    }

    operator fun minus(other: Complex) = Complex(real - other.real, imag - other.imag)
    operator fun minus(num: Number) = Complex(real - num.toDouble(), imag)
    operator fun minusAssign(other: Complex) {
        real -= other.real
        imag -= other.imag
    }
    operator fun minusAssign(num: Number) {
        real -= num.toDouble()
    }

    operator fun times(other: Complex) = Complex(real * other.real - imag * other.imag, real * other.imag + imag * other.real)
    operator fun times(num: Number) = Complex(num.toDouble() * real, num.toDouble() * imag)
    operator fun timesAssign(other: Complex) {
        real = real * other.real - imag * other.imag
        imag = real * other.imag + imag * other.real
    }
    operator fun timesAssign(num: Number) {
        real *= num.toDouble()
        imag *= num.toDouble()
    }

    operator fun div(other: Complex): Complex {
        // x/(a+bi) = x(a-bi)/(a+bi)(a-bi) = x(a-bi)/(a^2+b^2)
        val denom = other.real * other.real + other.imag * other.imag
        return Complex((real * other.real + imag * other.imag)/denom, (imag * other.real - real * other.imag)/denom)
    }
    operator fun div(num: Number) = Complex(real / num.toDouble(), imag / num.toDouble())
    operator fun divAssign(other: Complex) {
        val denom = other.real * other.real + other.imag * other.imag
        val newReal = (real * other.real + imag * other.imag)/denom
        imag = (imag * other.real - real * other.imag)/denom
        real = newReal
    }
    operator fun divAssign(num: Number) {
        real /= num.toDouble()
        imag /= num.toDouble()
    }
}

val I = Complex(0, 1)
val C_ONE = Complex(1)

operator fun Number.plus(c: Complex) = Complex(this.toDouble() + c.real, c.imag)
operator fun Number.minus(c: Complex) = Complex(this.toDouble() - c.real, -c.imag)
operator fun Number.times(c: Complex) = Complex(this.toDouble() * c.real, this.toDouble() * c.imag)
operator fun Number.div(c: Complex) = C_ONE/c
fun Number.toComplex() = Complex(this)