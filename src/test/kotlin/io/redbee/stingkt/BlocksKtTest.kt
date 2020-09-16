package io.redbee.stingkt

import org.junit.jupiter.api.Test

internal class BlocksKtTest {
    @Test
    internal fun defaultGivenWhenThen() {
        var result = -1
        GIVEN { result = 2 }
        WHEN { result *= 2 }
        THEN { result equals 4 }
    }

    @Test
    internal fun andsAreAThing() {
        var result = -1
        GIVEN { result = 2 }
        AND { result += 4 }

        WHEN { result *= 2 }

        THEN { result equals 12 }
        AND { result equals 6 + 6 }
    }

    @Test
    internal fun expectTheUnexpected() {
        EXPECT { 2 equals 2 }
    }
}