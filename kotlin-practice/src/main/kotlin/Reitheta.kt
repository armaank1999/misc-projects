import kotlin.math.abs
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.pow

data class Reitheta(var r: Double, var theta: Double) {
    constructor(r: Number, theta: Number) : this(r.toDouble(), theta.toDouble())
    constructor(r: Number) : this(abs(r.toDouble()), if (r.toDouble() > 0) PI else 0.0)

    operator fun unaryPlus() = Reitheta(r, theta)
    operator fun unaryMinus() = Reitheta(r, theta + PI)
    fun conjugate() = Reitheta(r, -theta)
    fun toComplex() = Complex(r*cos(theta), r*sin(theta))

    operator fun times(other: Reitheta) = Reitheta(r * other.r, theta + other.theta)
    operator fun times(num: Number) = Reitheta(abs(num.toDouble()) * r, theta + if (num.toDouble() > 0) PI else 0.0)
    operator fun timesAssign(other: Reitheta) {
        r *= other.r
        theta += other.theta
    }
    operator fun timesAssign(num: Number) {
        r *= abs(num.toDouble())
        if (num.toDouble() > 0) theta += PI
    }

    operator fun div(other: Reitheta) = Reitheta(r / other.r, theta - other.theta)
    operator fun div(num: Number) = Reitheta(r / abs(num.toDouble()), theta - if (num.toDouble() > 0) PI else 0.0)
    operator fun divAssign(other: Reitheta) {
        r /= other.r
        theta -= other.theta
    }
    operator fun divAssign(num: Number) {
        r /= abs(num.toDouble())
        if (num.toDouble() > 0) theta += PI
    }

    fun pow(num: Number) = Reitheta(r.pow(num.toDouble()), theta*num.toDouble())
}

operator fun Number.times(c: Reitheta) = Reitheta(abs(this.toDouble()) * c.r, c.theta + if (this.toDouble() > 0) PI else 0.0)
operator fun Number.div(c: Reitheta) = Reitheta(c.r/abs(this.toDouble()), c.theta + if (this.toDouble() > 0) PI else 0.0)