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

val school = getDirectories("example-services", "school")

include(
    ":school",
    ":school:school-api",
    ":school:school-domain",
    ":school:school-application",
    ":school:school-rdb-adapter",
    ":school:school-webmvc-adapter",
)

project(":school").projectDir = school("school")
project(":school:school-api").projectDir = school("api")
project(":school:school-domain").projectDir = school("domain")
project(":school:school-application").projectDir = school("application")
project(":school:school-rdb-adapter").projectDir = school("rdb")
project(":school:school-webmvc-adapter").projectDir = school("web-mvc")
