import java.util.*

class Lotto {
    companion object {
        val lottoRange = 1..40
        const val n = 7
    }

    fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
        if (n > range.count()) return null
        return range.toList().shuffled().take(n)
    }

    fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        return list1.toSet().intersect(list2.toSet()).size
    }

    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        return guess.size == count && numDistinct(guess) == count && guess.all { it in range }
    }

    fun checkGuess(guess: List<Int>, secret: List<Int> = pickNDistinct(lottoRange, n)!!): Int {
        return if (isLegalLottoGuess(guess)) {
            numCommon(guess, secret)
        } else {
            0
        }
    }
}
fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
    while (true) {
        println("Give $n numbers from $low to $high, separated by commas:")
        val input = readLine() ?: return emptyList()
        val numbers = input.split(',').mapNotNull { it.trim().toIntOrNull() }
        if (numbers.size == n && numbers.distinct().size == n && numbers.all { it in low..high }) {
            return numbers
        }
        println("Invalid input. Please try again.")
    }
}

fun playLotto() {
    val lotto = Lotto()
    var playAgain: String
    do {
        val secretNumbers = lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!!
        val guess = readNDistinct(1, 40, 7)
        val correctCount = lotto.checkGuess(guess, secretNumbers)

        println("lotto numbers: $secretNumbers, you got $correctCount correct")

        println("More? (Y/N): ")
        playAgain = readLine()?.trim()?.uppercase(Locale.getDefault()) ?: "N"
    } while (playAgain == "Y")
}
fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    val allPossibleNumbers = Lotto.lottoRange.toList().combinations(Lotto.n)
    val secretNumbers = lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!!
    var steps = 0

    for (guess in allPossibleNumbers) {
        steps++
        val result = lotto.checkGuess(guess, secretNumbers)
        if (result == Lotto.n) {
            return Pair(steps, guess)
        }
    }

    return Pair(steps, emptyList())
}

fun <T> List<T>.combinations(n: Int): List<List<T>> {
    if (n == 0) return listOf(emptyList())
    return if (size < n) emptyList() else {
        (0 until size).flatMap { i ->
            (this.subList(i + 1, size)).combinations(n - 1).map { listOf(this[i]) + it }
        }
    }
}

fun main() {
    playLotto()

}












