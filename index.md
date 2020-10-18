---
title: io.redbee.stingkt -
---
//[sting-kt](../index.md)/[io.redbee.stingkt](index.md)



# Package io.redbee.stingkt  


## Types  
  
|  Name|  Summary| 
|---|---|
| [BlockResult](-block-result/index.md)| [jvm]  <br>Content  <br>sealed class [BlockResult](-block-result/index.md)  <br><br><br>
| [CaseBuilder](-case-builder/index.md)| [jvm]  <br>Content  <br>class [CaseBuilder](-case-builder/index.md)<[T](-case-builder/index.md) : [Scope](-scope/index.md)>  <br><br><br>
| [ScenarioBuilder](-scenario-builder/index.md)| [jvm]  <br>Content  <br>class [ScenarioBuilder](-scenario-builder/index.md)<[T](-scenario-builder/index.md) : [Scope](-scope/index.md)>  <br><br><br>
| [Scope](-scope/index.md)| [jvm]  <br>Content  <br>abstract class [Scope](-scope/index.md)(**actual**: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, **exception**: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)  <br><br><br>
| [SimpleScope](-simple-scope/index.md)| [jvm]  <br>Content  <br>class [SimpleScope](-simple-scope/index.md) : [Scope](-scope/index.md)  <br><br><br>
| [TestCase](-test-case/index.md)| [jvm]  <br>Content  <br>class [TestCase](-test-case/index.md)<[T](-test-case/index.md) : [Scope](-scope/index.md)>(**name**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **scope**: [T](-test-case/index.md))  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [AND](-a-n-d.md)| [jvm]  <br>Content  <br>fun <[T](-a-n-d.md)> [AND](-a-n-d.md)(block: () -> [T](-a-n-d.md)): [T](-a-n-d.md)  <br><br><br>
| [assertTrue](assert-true.md)| [jvm]  <br>Content  <br>infix fun <[T](assert-true.md)> [T](assert-true.md).[assertTrue](assert-true.md)(block: ([T](assert-true.md)) -> [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>)  <br><br><br>
| [case](case.md)| [jvm]  <br>Content  <br>inline fun <[T](case.md) : [Scope](-scope/index.md)> [CaseBuilder](-case-builder/index.md)<[T](case.md)>.[case](case.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), block: [T](case.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [equals](equals.md)| [jvm]  <br>Content  <br>infix fun [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?.[equals](equals.md)(expected: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?)  <br><br><br>
| [EXPECT](-e-x-p-e-c-t.md)| [jvm]  <br>Content  <br>fun <[T](-e-x-p-e-c-t.md)> [EXPECT](-e-x-p-e-c-t.md)(block: () -> [T](-e-x-p-e-c-t.md)): [T](-e-x-p-e-c-t.md)  <br><br><br>
| [GIVEN](-g-i-v-e-n.md)| [jvm]  <br>Content  <br>fun <[T](-g-i-v-e-n.md)> [GIVEN](-g-i-v-e-n.md)(block: () -> [T](-g-i-v-e-n.md)): [T](-g-i-v-e-n.md)  <br><br><br>
| [Scenario](-scenario.md)| [jvm]  <br>Content  <br>inline fun <[T](-scenario.md) : [Scope](-scope/index.md)> [Scenario](-scenario.md)(block: [ScenarioBuilder](-scenario-builder/index.md)<[T](-scenario.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<DynamicTest>  <br>inline fun [Scenario](-scenario.md)(case: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), block: [ScenarioBuilder](-scenario-builder/index.md)<[SimpleScope](-simple-scope/index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<DynamicTest>  <br><br><br>
| [THEN](-t-h-e-n.md)| [jvm]  <br>Content  <br>fun <[T](-t-h-e-n.md)> [THEN](-t-h-e-n.md)(block: () -> [T](-t-h-e-n.md)): [T](-t-h-e-n.md)  <br><br><br>
| [WHEN](-w-h-e-n.md)| [jvm]  <br>Content  <br>fun <[T](-w-h-e-n.md)> [WHEN](-w-h-e-n.md)(block: () -> [T](-w-h-e-n.md)): [T](-w-h-e-n.md)  <br><br><br>

