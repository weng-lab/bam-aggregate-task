import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    kotlin("jvm") version "1.3.21"
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("application")
}

group = "com.genomealmanac"
version = "0.7.0"
val artifactID = "bam-aggregate-task"

repositories {
    mavenCentral()
    jcenter()
}

val biojavaVersion = "5.2.1"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("com.github.samtools", "htsjdk","2.19.0")
    compile("com.github.ajalt", "clikt", "1.6.0")
    compile("io.github.microutils","kotlin-logging","1.6.10")
    compile("ch.qos.logback", "logback-classic","1.2.3")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.4.0")
    testCompile("org.assertj", "assertj-core", "3.11.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClassName = "AppKt"
}
val shadowJar: ShadowJar by tasks
shadowJar.apply {
    baseName = artifactID
    classifier = ""
    destinationDir = file("build")
}
