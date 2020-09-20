
# sting.kt 
A collection of Kotlin extension functions for making JUint more verbose.

## Example
```kotlin
@TestFactory  
internal fun `create a scenario`() =  
    Scenario<MultiplyByTowScope>("given two numbers, when mutiplying by 2, then get the double of original number") {
  
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
``` 
