dependencies {
    api(project(":student:student-api"))
    // ❌ 해결방안 1
    // api(project(":school:school-application"))
    // 🛎️ 해결방안 2
    api(project(":school:school-api"))
}