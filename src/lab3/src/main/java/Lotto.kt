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

    fun checkGuess(guess: List<Int>, secret: List<Int>): Int {
        return if (isLegalLottoGuess(guess)) {
            numCommon(guess, secret)
        } else {
            0
        }
    }
}

fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    val correctNumbers = mutableListOf<Int>()
    val candidates = Lotto.lottoRange.toMutableList()
    var steps = 0

    while (correctNumbers.size < Lotto.n) {
        val currentGuess = correctNumbers.toMutableList()

        for (i in 0 until (Lotto.n - correctNumbers.size)) {
            currentGuess.add(candidates[i])
        }

        steps++
        val correctCount = lotto.checkGuess(currentGuess, lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!!)

        if (correctCount > correctNumbers.size) {
            correctNumbers.addAll(currentGuess.filter { it !in correctNumbers })
            candidates.removeAll(currentGuess)
        } else {
            candidates.removeAll(currentGuess.filter { it !in correctNumbers })
        }

        if (correctNumbers.size == Lotto.n) break
    }

    return Pair(steps, correctNumbers.sorted())
}

fun playLotto() {
    val lotto = Lotto()
    var playAgain: String
    do {
        val secretNumbers = lotto.pickNDistinct(Lotto.lottoRange, Lotto.n)!!
        val guess = readNDistinct(1, 40, 7)
        val correctCount = lotto.checkGuess(guess, secretNumbers)

        println("lotto numbers: $secretNumbers, you got $correctCount correct")

        val (steps, computerGuess) = findLotto(lotto)
        println("computer guess in $steps steps is $computerGuess")

        println("More? (Y/N): ")
        playAgain = readLine()?.trim()?.uppercase() ?: "N"
    } while (playAgain == "Y")
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

fun main() {
    playLotto()
}












