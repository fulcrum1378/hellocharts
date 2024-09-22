plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "ir.mahdiparastesh.hellocharts"
    compileSdk = project.properties["SDK_VERSION"].toString().toInt()
    buildToolsVersion = System.getenv("ANDROID_BUILD_TOOLS_VERSION")

    defaultConfig { minSdk = project.properties["MIN_SDK_VERSION"].toString().toInt() }
    sourceSets.getByName("main") { java.setSrcDirs(listOf("java")) }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("androidx.core:core:1.13.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.20") {
        because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
    }
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.20") {
        because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
    }
}

afterEvaluate { // You only need to set the access token and execute "gradle publish"...
    publishing {
        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/fulcrum6378/HelloCharts")
                credentials {
                    username = "fulcrum6378"
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "ir.mahdiparastesh"
                artifactId = "hellocharts"
                version = project.properties["VERSION_NAME"].toString()
            }
        }
    }
}
