import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class RootOfUnity(val turn: Double) {
    // One turn equals one full rotation, which is 2 pi radians.
    fun toReitheta() = Reitheta(1, turn*2*PI)
    fun toComplex() = Complex(cos(turn*2*PI), sin(turn*2*PI))
    fun toComplex(r: Number) = Complex(r.toDouble()*cos(turn*2*PI), r.toDouble()*sin(turn*2*PI))
    fun conjugate() = RootOfUnity(-turn)
}