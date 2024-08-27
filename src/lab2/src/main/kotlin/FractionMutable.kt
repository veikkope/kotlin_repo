package org.example

class FractionMutable(numerator: Int, denominator: Int, sign: Int = 1) {

    private var numerator: Int = numerator
    private var denominator: Int = denominator
    private var sign: Int = sign

    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        normalize()
    }

    // Simplifies the fraction and ensures the denominator is positive
    private fun normalize() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
        if (denominator < 0) {
            denominator *= -1
            sign *= -1
        }
    }

    // Negate the fraction by flipping the sign
    fun negate() {
        sign *= -1
    }

    // Add another fraction to this fraction
    fun add(other: FractionMutable) {
        numerator = sign * numerator * other.denominator + other.sign * other.numerator * denominator
        denominator *= other.denominator
        sign = if (numerator < 0) -1 else 1
        numerator = kotlin.math.abs(numerator)
        normalize()
    }

    // Multiply this fraction by another fraction
    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        sign *= other.sign
        normalize()
    }

    // Divide this fraction by another fraction
    fun div(other: FractionMutable) {
        numerator *= other.denominator
        denominator *= other.numerator
        sign *= other.sign
        normalize()
    }

    // Get the integer part of the fraction
    fun intPart(): Int {
        return sign * (numerator / denominator)
    }

    // Convert the fraction to a string representation
    override fun toString(): String {
        return if (sign == -1) "-$numerator/$denominator" else "$numerator/$denominator"
    }

    // Helper function to find the greatest common divisor
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) kotlin.math.abs(a) else gcd(b, a % b)
    }
}

