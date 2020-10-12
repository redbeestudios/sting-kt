package io.redbee.stingkt.target

class Calculator {

    fun addPositives(vararg n: Int) =
        n.find { it < 0 }?.let { require(it >= 0) { "$it should be positive" } }
            ?: n.sumBy { it }

}