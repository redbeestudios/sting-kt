package io.redbee.stingkt

import org.junit.jupiter.api.Assertions

infix fun Any?.equals(expected: Any?) {
    Assertions.assertEquals(expected, this)
}

infix fun <T> T.assertTrue(block: (T) -> Pair<Boolean, String>) {
    with(block(this)) {
        Assertions.assertTrue(first, second)
    }
}