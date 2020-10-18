@file:Suppress("FunctionName")

package io.redbee.stingkt

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DynamicTest.dynamicTest

abstract class Scope(
    var actual: Any? = null,
    var exception: Throwable? = null
)

class SimpleScope : Scope()

inline fun Scenario(case: String, block: ScenarioBuilder<SimpleScope>.() -> Unit) =
    Scenario<SimpleScope> {
        Given { case(case) }
        block.invoke(this)
    }

inline fun <T : Scope> Scenario(block: ScenarioBuilder<T>.() -> Unit) =
    ScenarioBuilder<T>()
        .apply(block)
        .build()

class ScenarioBuilder<T : Scope> {

    val cases: MutableList<TestCase<T>> = mutableListOf()
    private var whenBlock: (T.() -> Any)? = null
    private var thenBlock: (T.() -> Unit)? = null
    var throwsBlock: ((T) -> Unit)? = null

    inline fun Given(block: CaseBuilder<T>.() -> Unit): ScenarioBuilder<T> {
        cases.addAll(CaseBuilder<T>().apply(block).cases)
        return this
    }

    fun When(block: T.() -> Any) {
        whenBlock = block
    }

    fun Then(block: T.() -> Unit) {
        thenBlock = block
    }

    inline fun <reified E : Throwable> Throws(crossinline block: T.(E) -> Unit) {
        throwsBlock = { case ->
            block(case, assertThrows(E::class.java) {
                case.exception?.let { throw it }
            })
        }
    }

    fun build() = cases.map { case ->
        dynamicTest(case.name) {

            kotlin.runCatching { case.scope.run(whenBlock!!) }
                .onSuccess { case.scope.actual = it }
                .onFailure { case.scope.exception = it }

            thenBlock?.let { case.scope.run(it) }
            throwsBlock?.let { case.scope.run(it) }
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

inline fun <reified E : Throwable> Scope.thrown(block: Scope.(E) -> Unit = {}) {
    block(this, assertThrows(E::class.java) { this.exception?.let { throw it } })
}
