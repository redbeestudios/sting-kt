@file:Suppress("LocalVariableName")

package io.redbee.stingkt

import io.redbee.stingkt.target.Calculator
import org.junit.jupiter.api.TestFactory

internal class ScenarioBuilderTest {

    private val calculator = Calculator()

    class AValueIsAddedTwice : Scope() {
        var number = 0
        var double = 0
    }

    @TestFactory
    fun `create a scenario`() =
        Scenario<AValueIsAddedTwice> {
            Given {
                case("2 + 2 is 4") {
                    number = 2
                    double = 4
                }
                case {
                    number = 4
                    double = 8
                }
            }
            When {
                calculator.addPositives(number, number)
            }
            Then { actual equals double }
        }

    @TestFactory
    fun `validate unexpected throw breaks the test`() =
        Scenario("an error is not expected") {
            When {
                Scenario("actual equals 123") {
                    When { error("not_expected_throw") }
                    Then { actual equals 123 }
                }[0].executable.execute()
            }
            Throws<IllegalStateException> { it.message == "not_expected_throw" }
        }

    @TestFactory
    fun `validate target throws`() =
        Scenario<AValueIsAddedTwice> {
            Given { case { number = -1; double = -2 } }
            When { calculator.addPositives(number, number) }
            Throws<IllegalArgumentException> {
                it.message equals "$number should be positive"
            }
        }

    @TestFactory
    fun `validate custom throws`() =
        Scenario("trows") {
            When { error("Invalid") }
            Then { thrown<IllegalStateException> { it.message equals "Invalid" } }
        }

}