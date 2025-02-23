import me.champeau.jmh.JmhBytecodeGeneratorTask

plugins {
    java
    kotlin("jvm") version "2.2.255-SNAPSHOT"
    id("me.champeau.jmh") version "0.7.2"
}

group = "org.jetbrains.kotlinx"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    jmh("commons-io:commons-io:2.14.0")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(23)
        vendor = JvmVendorSpec.ORACLE // Valhalla build
    }
    compilerOptions {
        freeCompilerArgs = listOf("-Xvalue-classes", "-Xvalhalla-value-classes", "-Xjvm-enable-preview")
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("--release", "23", "--enable-preview"))
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("--enable-preview")
}

tasks.withType<JmhBytecodeGeneratorTask> {
    jvmArgs = listOf("--enable-preview")
}

jmh {
    humanOutputFile = project.file("${project.rootDir}/reports/jmh/human.txt")
    resultsFile = project.file("${project.rootDir}/reports/jmh/results.csv")
    jvmArgsPrepend = listOf("--enable-preview")
    benchmarkMode = listOf("avgt")
    resultFormat = "CSV"
    warmupIterations = 3
}
