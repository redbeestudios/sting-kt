@file:Suppress("LocalVariableName")

package io.redbee.stingkt

import org.junit.jupiter.api.TestFactory

internal class ScenarioBuilderTest {

    class MultiplyByTowScope : Scope() {
        var number = 0
        var double = 0
    }

    @TestFactory
    internal fun `create a scenario`() =
        Scenario<MultiplyByTowScope> {

            Given {

                case("2 * 2 is 4") {
                    number = 2
                    double = 4
                }

                case {
                    number = 4
                    double = 8
                }
            }

            When { number * 2 }

            Then {
                assert { actual equals double }
            }

        }


//            Scenario(`multiply by 2`)
//            {
//                Given {
//                    numberA = 2
//                    numberB = 4
//                }
//                When { numberA * numberB }
//                Then { actual equals 8 }
//            }
}

//    @Test
//    internal fun `throws exception`() {
//        Scenario {
//            When { error("Invalid") }
//            Throws<IllegalStateException> { it.message equals "Invalid" }
//        }
//    }
//}
