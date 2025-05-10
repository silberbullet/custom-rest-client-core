dependencies {
    api(project(":student:student-application"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}