package com.redbee.pokemonapi.archunit

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.GeneralCodingRules

@AnalyzeClasses(packages = ["com.redbee.springkotlinseed"], importOptions = [DoNotIncludeTests::class])
class CodingRulesTest {

    @ArchTest
    val exceptions_should_respect_naming_convention: ArchRule = ArchRuleDefinition.classes()
            .that().resideInAPackage("..exception..")
            .should().haveSimpleNameEndingWith("Exception")

    @ArchTest
    var no_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS

    @ArchTest
    var no_standard_streams = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS

    @ArchTest
    var no_java_logging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING

    @ArchTest
    var no_jodatime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME

    @ArchTest
    var classes_must_not_be_suffixed_with_impl = ArchRuleDefinition.noClasses()
            .should().haveSimpleNameEndingWith("Impl")
            .because("En serio, y si nos esforzamos un poco más?")
}
