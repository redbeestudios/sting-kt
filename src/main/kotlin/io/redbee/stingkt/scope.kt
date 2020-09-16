@file:Suppress("UNCHECKED_CAST")

package io.redbee.stingkt

import org.junit.jupiter.api.Assertions.assertThrows

abstract class Scope(
    var actual: Any? = null,
    var exception: Throwable? = null
)

class ScenarioBuilder<T : Scope>(
    var mScope: T
) {

    fun Given(block: T.() -> Unit) {
        mScope.run(block)
    }

    fun <R> When(block: T.() -> R) {
        kotlin.runCatching { mScope.run(block) }
            .onSuccess { mScope.actual = it }
            .onFailure { mScope.exception = it }
    }

    fun Then(block: T.() -> Unit) {
        mScope.run(block)
    }

    inline fun <reified E : Throwable> Throws(block: (Throwable) -> Unit = {}) {
        block(assertThrows(E::class.java) { mScope.exception?.let { throw it } })
    }

}

class DefaultScope : Scope()

fun <T : Scope> Scenario(ctx: T, block: ScenarioBuilder<T>.() -> Unit): ScenarioBuilder<T> {
    return ScenarioBuilder(ctx).apply { block() }
}

fun Scenario(block: ScenarioBuilder<DefaultScope>.() -> Unit): ScenarioBuilder<DefaultScope> {
    return ScenarioBuilder(DefaultScope()).apply { block() }
}