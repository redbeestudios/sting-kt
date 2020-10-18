---
title: BlockResult -
---
//[sting-kt](../../index.md)/[io.redbee.stingkt](../index.md)/[BlockResult](index.md)



# BlockResult  
 [jvm] sealed class [BlockResult](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [Handled](-handled/index.md)| [jvm]  <br>Content  <br>data class [Handled](-handled/index.md)(**out**: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [BlockResult](index.md)  <br><br><br>
| [Returned](-returned/index.md)| [jvm]  <br>Content  <br>data class [Returned](-returned/index.md)(**out**: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) : [BlockResult](index.md)  <br><br><br>
| [Threw](-threw/index.md)| [jvm]  <br>Content  <br>data class [Threw](-threw/index.md)(**out**: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) : [BlockResult](index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../-case-builder/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../-case-builder/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../-case-builder/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../-case-builder/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../-case-builder/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../-case-builder/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [out](index.md#io.redbee.stingkt/BlockResult/out/#/PointingToDeclaration/)|  [jvm] abstract val [out](index.md#io.redbee.stingkt/BlockResult/out/#/PointingToDeclaration/): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?   <br>


## Inheritors  
  
|  Name| 
|---|
| [BlockResult](-returned/index.md)
| [BlockResult](-threw/index.md)
| [BlockResult](-handled/index.md)

