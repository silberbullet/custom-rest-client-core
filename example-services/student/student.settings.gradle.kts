fun getDirectories(vararg names: String): (String) -> File {
    var dir = rootDir
    for (name in names) {
        dir = dir.resolve(name)
    }
    return { targetName ->
        val directory = dir.walkTopDown().maxDepth(3)
            .filter(File::isDirectory)
            .associateBy { it.name }
        directory[targetName] ?: throw Error("그런 폴더가 없습니다: $targetName")
    }
}

val student = getDirectories("example-services", "student")

include(
    ":student",
    ":student:student-api",
    ":student:student-domain",
    ":student:student-application",
    ":student:student-rdb-adapter",
    ":student:student-webmvc-adapter",
)

project(":student").projectDir = student("student")
project(":student:student-api").projectDir = student("api")
project(":student:student-domain").projectDir = student("domain")
project(":student:student-application").projectDir = student("application")
project(":student:student-rdb-adapter").projectDir = student("rdb")
project(":student:student-webmvc-adapter").projectDir = student("web-mvc")
