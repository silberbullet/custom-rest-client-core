rootProject.name = "custom-rest-client-core"

val services = "${rootProject.projectDir}/example-services"

apply(from = "core/core.settings.gradle.kts")
apply(from = "main-runner/main.settings.gradle.kts")
apply(from = "$services/school/school.settings.gradle.kts")
apply(from = "$services/student/student.settings.gradle.kts")