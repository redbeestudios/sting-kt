
# sting.kt 
A collection of Kotlin APIs for making JUint more verbose and documentation friedly.

## Examples

### Multiple Cases
```kotlin
@TestFactory
fun `create a scenario`() = Scenario<AValueIsAddedTwice> {
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

    When { calculator.addPositives(number, number) }

    Then { actual equals double }
}
``` 

### Exceptions
```kotlin
@TestFactory
fun `validate target throws`() = Scenario<AValueIsAddedTwice> {
    Given { 
        case { number = -1; double = -2 } 
    }

    When { calculator.addPositives(number, number) }

    Throws<IllegalArgumentException> { it.message equals "-1 should be positive" }
}
``` 
