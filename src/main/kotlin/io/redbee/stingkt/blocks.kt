@file:Suppress("FunctionName")

package io.redbee.stingkt

fun <T> GIVEN(block: () -> T) = block()
fun <T> WHEN(block: () -> T) = block()
fun <T> THEN(block: () -> T) = block()
fun <T> EXPECT(block: () -> T): T = block()
fun <T> AND(block: () -> T): T = block()