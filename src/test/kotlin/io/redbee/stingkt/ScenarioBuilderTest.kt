@file:Suppress("LocalVariableName")

package io.redbee.stingkt

import io.mockk.every
import io.mockk.mockk
import io.redbee.stingkt.target.Calculator
import io.redbee.stingkt.target.Math
import org.junit.jupiter.api.TestFactory

internal class ScenarioBuilderTest {


    private val math: Math = mockk()

    private val calculator = Calculator(
        math
    )

    class AValueIsAddedTwice : Scope() {
        var number = 0
        var double = 0
    }

    class AValueIsMultipliedByARandomNumber : Scope() {
        var number = 0
        var random = 0
    }

    @TestFactory
    fun `create a scenario`() =
        Suppose<AValueIsAddedTwice> {
            Cases {
                case("2 + 2 is 4") { number = 2; double = 4 }
                case("3 + 3 is 6") { number = 3; double = 6 }
                case("4 + 4 is 8") { number = 4; double = 8 }
                case("5 + 5 is 10") { number = 5; double = 10 }
            }
            When {
                calculator.addPositives(number, number)
            }
            Then { actual equals double }
        }

    @TestFactory
    fun `create a scenario with mocking`() =
        Suppose<AValueIsMultipliedByARandomNumber> {
            Cases {
                case("2 + 2 is 4") { number = 2; random = 10; expected = 20 }
                case("2 + 2 is 4") { number = 2; random = 10; expected = 20 }
            }

            Given { every { math.randomNumber() } returns random }

            When { calculator.multiplyByRandom(number) }

            Then { actual equals expected }
        }

    @TestFactory
    fun `validate unexpected throw breaks the test`() =
        Suppose("an error is not expected") {
            When {
                Suppose("actual equals 123") {
                    When { error("not_expected_throw") }
                    Then { actual equals 123 }
                }[0].executable.execute()
            }
            Throws<IllegalStateException> { it.message == "not_expected_throw" }
        }

    @TestFactory
    fun `validate target throws`() =
        Suppose<AValueIsAddedTwice> {
            Cases { case { number = -1; double = -2 } }
            When { calculator.addPositives(number, number) }
            Throws<IllegalArgumentException> {
                it.message equals "$number should be positive"
            }
        }

    @TestFactory
    fun `validate custom throws`() =
        Suppose("trows") {
            When { error("Invalid") }
            Then { thrown<IllegalStateException> { it.message equals "Invalid" } }
        }

}