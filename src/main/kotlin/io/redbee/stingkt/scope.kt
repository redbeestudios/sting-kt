package io.redbee.stingkt

import org.junit.jupiter.api.DynamicTest.dynamicTest

abstract class Scope(
    var actual: Any? = null,
    var exception: Throwable? = null
)

inline fun <T : Scope> Scenario(block: ScenarioBuilder<T>.() -> Unit) =
    ScenarioBuilder<T>().apply(block).build()

class ScenarioBuilder<T : Scope> {

    val cases: MutableList<TestCase<T>> = mutableListOf()
    var whenBlock: (T.() -> Any)? = null
    val asserts: MutableList<T.() -> Unit> = mutableListOf()

    inline fun Given(block: CaseBuilder<T>.() -> Unit) = cases.addAll(CaseBuilder<T>().apply(block).cases)

    fun  When(block: T.() -> Any) {
        whenBlock = block
    }

    fun Then(block: AssertBuilder<T>.() -> Unit) = asserts.addAll(AssertBuilder<T>().apply(block).asserts)

    fun build() = cases.map { case ->
        dynamicTest(case.name) {

            kotlin.runCatching { case.scope.run(whenBlock!!) }
                .onSuccess { case.scope.actual = it }
                .onFailure { case.scope.exception = it }

            // TODO handle throws here

            asserts.map { assert -> assert(case.scope) }
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

inline fun <reified T : Scope> CaseBuilder<T>.case(name: String = "", block: T.() -> Unit) {
    val caseName = name.takeUnless { it.isEmpty() } ?: "Case ${cases.size + 1}"
    val caseScope = T::class.java.getDeclaredConstructor().newInstance()
    val case = TestCase<T>(caseName, caseScope)
    block(caseScope)
    cases.add(case)
}


class AssertBuilder<T : Scope> {
    val asserts: MutableList<T.() -> Unit> = mutableListOf()

    fun assert(block: T.() -> Unit) {
        asserts.add(block)
    }

    // TODO add throws to handle exceptions
//    inline fun <reified E : Throwable> throws(block: (Throwable) -> Unit = {}) {
//        block(assertThrows(E::class.java) { mScope.exception?.let { throw it } })
//    }

}
