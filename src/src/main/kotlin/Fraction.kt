class Fraction(numerator: Int, denominator: Int, private val sign: Int = 1) : Comparable<Fraction> {

    private val numerator: Int
    private val denominator: Int

    // Initialize and normalize the fraction
    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        val gcd = gcd(Math.abs(numerator), Math.abs(denominator))
        val normalizedSign = if (numerator < 0 && denominator < 0 || numerator >= 0 && denominator < 0) -sign else sign
        this.numerator = Math.abs(numerator) / gcd * normalizedSign
        this.denominator = Math.abs(denominator) / gcd
    }

    // Return string representation
    override fun toString(): String {
        return if (numerator == 0) "0" else "$numerator/$denominator"
    }

    // Operator overloads for arithmetic operations
    operator fun plus(other: Fraction): Fraction {
        val commonDenominator = this.denominator * other.denominator
        val newNumerator = this.numerator * other.denominator + other.numerator * this.denominator
        return Fraction(newNumerator, commonDenominator)
    }

    operator fun minus(other: Fraction): Fraction {
        val commonDenominator = this.denominator * other.denominator
        val newNumerator = this.numerator * other.denominator - other.numerator * this.denominator
        return Fraction(newNumerator, commonDenominator)
    }

    operator fun times(other: Fraction): Fraction {
        return Fraction(this.numerator * other.numerator, this.denominator * other.denominator)
    }

    operator fun div(other: Fraction): Fraction {
        return Fraction(this.numerator * other.denominator, this.denominator * other.numerator)
    }

    // Unary minus operator to negate a fraction
    operator fun unaryMinus(): Fraction {
        return Fraction(-this.numerator, this.denominator)
    }

    // Custom negate function
    fun negate(): Fraction {
        return -this
    }

    // Compare fractions
    override fun compareTo(other: Fraction): Int {
        return (this.numerator * other.denominator).compareTo(other.numerator * this.denominator)
    }

    // Utility function to find greatest common divisor
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    // Override equals to compare fractions based on values
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return this.compareTo(other) == 0
    }

    // Override hashCode to be consistent with equals
    override fun hashCode(): Int {
        return numerator.hashCode() * 31 + denominator.hashCode()
    }

    // Helper functions for additional operations if needed
    fun add(other: Fraction): Fraction {
        return this + other
    }

    fun mult(other: Fraction): Fraction {
        return this * other
    }

    fun subtr(other: Fraction): Fraction {
        return this - other
    }

    fun divide(other: Fraction): Fraction {
        return this / other
    }



}



fun main() {
    val a = Fraction(1,2,-1)
    println(a)
    println(a.add(Fraction(1,3)))
    println(a.mult(Fraction(5,2, -1)))
    println(a.div(Fraction(2,1)))
    println(-Fraction(1,6) + Fraction(1,2))
    println(Fraction(2,3) * Fraction(3,2))
    println(Fraction(1,2) > Fraction(2,3))
}