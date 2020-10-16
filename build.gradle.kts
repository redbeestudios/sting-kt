plugins {
    `maven-publish`
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.dokka") version "1.4.10"
    id("com.jfrog.bintray") version "1.8.5"
}

group = "io.redbee"
version = "0.1.3"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.test {
    useJUnitPlatform()
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

bintray {
    user = if (project.hasProperty("bintrayUser"))
        project.findProperty("bintrayUser").toString() else System.getenv("BINTRAY_USER")
    key = if (project.hasProperty("bintrayKey"))
        project.findProperty("bintrayKey").toString() else System.getenv("BINTRAY_KEY")
    publish = true

    setPublications("sting")

    pkg.apply {
        repo = "kotlin"
        name = rootProject.name
        description = "A collection of Kotlin extension functions for making JUint more verbose."
        userOrg = "redbee"
        websiteUrl = "https://github.com/redbeestudios/sting-kt"
        issueTrackerUrl = "https://github.com/redbeestudios/sting-kt/issues"
        vcsUrl = "https://github.com/redbeestudios/sting-kt"
        githubRepo = "https://github.com/redbeestudios/sting-kt"
        version.vcsTag = "v${project.version}"
        setLicenses("Apache-2.0")
        setLabels("kotlin", "junit", "test", "bdd")
        publicDownloadNumbers = true
    }
}

publishing {
    publications {
        create<MavenPublication>("sting") {
            from(components["java"])
            artifact(dokkaJar)
            artifact(sourcesJar)
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}
