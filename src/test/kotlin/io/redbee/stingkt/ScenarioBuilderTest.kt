@file:Suppress("LocalVariableName")

package io.redbee.stingkt

import org.junit.jupiter.api.Test

internal class ScenarioBuilderTest {

    @Test
    internal fun `create a scenario`() {
        val `multiply by 2` = object : Scope() {
            var numberA: Int = -1
            var numberB: Int = -2
        }
        Scenario(`multiply by 2`) {
            Given {
                numberA = 2
                numberB = 4
            }
            When { numberA * numberB }
            Then { actual equals 8 }
        }
    }

    @Test
    internal fun `throws exception`() {
        Scenario {
            When { error("Invalid") }
            Throws<IllegalStateException> { it.message equals "Invalid" }
        }
    }
}