import org.springframework.boot.gradle.tasks.bundling.BootJar

version = "0.0.1-SNAPSHOT"

dependencies {
    // core

    // service
    implementation(project(":school"))
    implementation(project(":student"))
    // webmvc
    implementation("org.springframework.boot:spring-boot-starter-web")
    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // db
    implementation("com.h2database:h2")
    // test
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar>{
    enabled = true
}

tasks.withType<Jar>{
    enabled = false
}