val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)


include(
    ":client-api",
    ":custom-rest-client",
)

project(":client-api").projectDir = core["client-api"]!!
project(":custom-rest-client").projectDir = core["custom-rest-client"]!!