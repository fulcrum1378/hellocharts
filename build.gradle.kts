plugins {
    id("com.android.application") version ("8.5.2") apply (false)
    id("com.android.library") version ("8.5.2") apply (false)
}

tasks.register("clean", Delete::class) {
    delete("$rootDir/library/build", "$rootDir/samples/build")
}
