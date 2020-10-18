# sting.kt 
A collection of Kotlin APIs for making JUint more verbose and documentation friedly.

[ ![Download](https://api.bintray.com/packages/redbee/kotlin/sting-kt/images/download.svg) ](https://bintray.com/redbee/kotlin/sting-kt/_latestVersion)

## Install
```kotlin
repositories {
    maven {
        url  "https://dl.bintray.com/redbee/kotlin" 
    }
}
```

```kotlin
dependencies {
    implementation("io.redbee:sting-kt:0.2.0")
}
```

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
