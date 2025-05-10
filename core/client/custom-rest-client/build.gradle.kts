dependencies {
    api(project(":client-api"))
    // Spring Release on 2025.4.17
    api("org.springframework:spring-web:6.2.6")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}