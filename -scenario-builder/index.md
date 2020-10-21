---
title: ScenarioBuilder -
---
//[sting-kt](../../index.md)/[io.redbee.stingkt](../index.md)/[ScenarioBuilder](index.md)



# ScenarioBuilder  
 [jvm] class [ScenarioBuilder](index.md)<[T](index.md) : [Scope](../-scope/index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [build](build.md)| [jvm]  <br>Content  <br>fun [build](build.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>>  <br><br><br>
| [Cases](-cases.md)| [jvm]  <br>Content  <br>fun [Cases](-cases.md)(block: [CaseBuilder](../-case-builder/index.md)<[T](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br><br><br>
| [equals](../-case-builder/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../-case-builder/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [Given](-given.md)| [jvm]  <br>Content  <br>fun [Given](-given.md)(block: [T](index.md).() -> [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br>fun [Given](-given.md)(cases: [CaseBuilder](../-case-builder/index.md)<[T](index.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), block: [T](index.md).() -> [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br><br><br>
| [hashCode](../-case-builder/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../-case-builder/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [Then](-then.md)| [jvm]  <br>Content  <br>fun [Then](-then.md)(block: [T](index.md).() -> [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br><br><br>
| [thrown](thrown.md)| [jvm]  <br>Content  <br>inline fun <[E](thrown.md) : [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)> [Scope](../-scope/index.md).[thrown](thrown.md)(block: [Scope](../-scope/index.md).([E](thrown.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [BlockResult](../-block-result/index.md)  <br><br><br>
| [Throws](-throws.md)| [jvm]  <br>Content  <br>inline fun <[E](-throws.md) : [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)> [Throws](-throws.md)(crossinline block: [T](index.md).([E](-throws.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br><br><br>
| [toString](../-case-builder/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../-case-builder/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [When](-when.md)| [jvm]  <br>Content  <br>fun [When](-when.md)(block: [T](index.md).() -> [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [ScenarioBuilder](index.md)<[T](index.md)>  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [blocks](index.md#io.redbee.stingkt/ScenarioBuilder/blocks/#/PointingToDeclaration/)|  [jvm] val [blocks](index.md#io.redbee.stingkt/ScenarioBuilder/blocks/#/PointingToDeclaration/): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[T](index.md).() -> [BlockResult](../-block-result/index.md)>   <br>
| [shouldHandle](index.md#io.redbee.stingkt/ScenarioBuilder/shouldHandle/#/PointingToDeclaration/)|  [jvm] var [shouldHandle](index.md#io.redbee.stingkt/ScenarioBuilder/shouldHandle/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>

