@file:Suppress("FunctionName")

package io.redbee.stingkt

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DynamicTest.dynamicTest

abstract class Scope(
    var actual: Any? = null,
    var expected: Any? = null,
    var exception: Throwable? = null
)

class SimpleScope : Scope()

inline fun Scenario(case: String, block: ScenarioBuilder<SimpleScope>.() -> Unit) = Suppose(case, block)
inline fun Suppose(case: String, block: ScenarioBuilder<SimpleScope>.() -> Unit) =
    Suppose<SimpleScope> {
        Cases { case(case) }
        block.invoke(this)
    }

inline fun <T : Scope> Scenario(block: ScenarioBuilder<T>.() -> Unit) = Suppose(block)
inline fun <T : Scope> Suppose(block: ScenarioBuilder<T>.() -> Unit) =
    ScenarioBuilder<T>()
        .apply(block)
        .build()
        .map { (name, test) -> dynamicTest(name) { test.invoke() } }

sealed class BlockResult {
    abstract val out: Any?

    data class Returned(override val out: Any?) : BlockResult()
    data class Threw(override val out: Throwable) : BlockResult()
    data class Handled(override val out: Throwable) : BlockResult()
}

class ScenarioBuilder<T : Scope> {

    var shouldHandle = false
    private val cases: MutableList<TestCase<T>> = mutableListOf()
    val blocks: MutableList<(T.() -> BlockResult)> = mutableListOf()

    fun Given(block: T.() -> Any): ScenarioBuilder<T> {
        blocks.add { evaluateBlock(block) }
        return this
    }

    fun Given(cases: CaseBuilder<T>.() -> Unit, block: T.() -> Any = {}): ScenarioBuilder<T> {
        this.cases.addAll(CaseBuilder<T>().apply(cases).cases)
        blocks.add { evaluateBlock(block) }
        return this
    }

    fun When(block: T.() -> Any): ScenarioBuilder<T> {
        blocks.add { evaluateBlock(block) }
        return this
    }

    fun Then(block: T.() -> Any): ScenarioBuilder<T> {
        blocks.add { evaluateBlock(block) }
        return this
    }

    inline fun <reified E : Throwable> Throws(crossinline block: T.(E) -> Unit): ScenarioBuilder<T> {
        this.shouldHandle = true
        val a = { case: T -> case.thrown<E> { block(case, it) } }
        blocks.add(a)
        return this
    }

    inline fun <reified E : Throwable> Scope.thrown(block: Scope.(E) -> Unit = {}): BlockResult {
        this@ScenarioBuilder.shouldHandle = true
        block(this, assertThrows(E::class.java) { this.exception?.let { throw it } })
        return this.exception?.let { BlockResult.Handled(it) } ?: BlockResult.Returned(this)
    }

    private fun T.evaluateBlock(block: T.() -> Any): BlockResult {
        return kotlin.runCatching { block(this) }
            .onSuccess { this.actual = it }
            .onFailure { this.exception = it }
            .fold({ BlockResult.Returned(it) }, { BlockResult.Threw(it) })
    }

    fun Cases(block: CaseBuilder<T>.() -> Unit): ScenarioBuilder<T> {
        cases.addAll(CaseBuilder<T>().apply(block).cases)
        return this
    }

    fun build() = cases.map { case ->
        case.name to {
            blocks
                .map { it(case.scope) }
                .forEach { r ->
                    if (r is BlockResult.Threw && !shouldHandle)
                        throw r.out
                }
        }
    }
}

class TestCase<T : Scope>(
    val name: String,
    val scope: T
)

class CaseBuilder<T : Scope> {

    val cases: MutableList<TestCase<T>> = mutableListOf()

}

inline fun <reified T : Scope> CaseBuilder<T>.case(name: String = "", block: T.() -> Unit = {}) {
    val caseName = name.takeUnless { it.isEmpty() } ?: "Case ${cases.size + 1}"
    val caseScope = T::class.java.getDeclaredConstructor().newInstance()
    val case = TestCase<T>(caseName, caseScope)
    block(caseScope)
    cases.add(case)
}
