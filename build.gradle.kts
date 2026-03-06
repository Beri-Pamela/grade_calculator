plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "com.gradecalculator"
version = "1.0.0"

application {
    mainClass.set("com.gradecalculator.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // CSV parsing
    implementation("org.apache.commons:commons-csv:1.12.0")

    // Excel read / write (.xlsx)
    implementation("org.apache.poi:poi:5.3.0")
    implementation("org.apache.poi:poi-ooxml:5.3.0")
    implementation("org.apache.xmlbeans:xmlbeans:5.2.1")
    implementation("org.apache.commons:commons-compress:1.27.1")
    implementation("commons-io:commons-io:2.17.0")

    // Embedded web server (GUI mode)
    implementation("io.javalin:javalin:6.4.0")
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("io.mockk:mockk:1.13.13")
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}