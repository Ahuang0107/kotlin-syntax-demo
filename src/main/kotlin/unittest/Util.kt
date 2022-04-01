package unittest

class Util {
    fun sum(a: Double?, b: Double?): Double {
        return (a ?: 0.0) + (b ?: 0.0)
    }
}