package io.redbee.stingkt.target

class Calculator(
    private val math: Math
) {

    fun addPositives(vararg n: Int) =
        n.find { it < 0 }?.let { require(it >= 0) { "$it should be positive" } }
            ?: n.sumBy { it }

    fun multiplyByRandom(n: Int) = n * math.randomNumber()

}


class Math {

    fun randomNumber() = 1

}