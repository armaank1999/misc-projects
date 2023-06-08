import java.math.BigInteger

fun modPower(base: Long, exp: Long, mod: Long): Long {
    var b = base
    var e = exp
    var returnee = 1L
    while (e > 0) {
        if (e and 1L == 1L) returnee = (b * returnee) % mod
        e = e shr 1
        b = (b * b) % mod
    }
    return returnee
}

@JvmName("modPowerBigInteger")
fun modPower(base: BigInteger, exp: BigInteger, mod: BigInteger): BigInteger {
    var b = base
    var e = exp
    var returnee = BigInteger.ONE
    while (e > BigInteger.ZERO) {
        if (e and BigInteger.ONE == BigInteger.ONE) returnee = (b * returnee) % mod
        e = e shr 1
        b = (b * b) % mod
    }
    return returnee
}

fun main(args: Array<String>) {
    println(modPower(BigInteger.valueOf(5), BigInteger.valueOf(11), BigInteger.valueOf(11)))
}