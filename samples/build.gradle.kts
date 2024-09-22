plugins {
    id("com.android.application")
}

android {
    namespace = "ir.mahdiparastesh.hellocharts.samples"
    compileSdk = project.properties["SDK_VERSION"].toString().toInt()
    buildToolsVersion = System.getenv("ANDROID_BUILD_TOOLS_VERSION")

    defaultConfig {
        applicationId = "ir.mahdiparastesh.hellocharts"
        minSdk = project.properties["MIN_SDK_VERSION"].toString().toInt()
        targetSdk = project.properties["SDK_VERSION"].toString().toInt()
        versionCode = 1
        versionName = project.properties["VERSION_NAME"].toString()
    }

    sourceSets.getByName("main") {
        manifest.srcFile("AndroidManifest.xml")
        java.setSrcDirs(listOf("java"))
        res.setSrcDirs(listOf("res"))
        assets.setSrcDirs(listOf("assets"))
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":library"))
    implementation("androidx.appcompat:appcompat:1.7.0")
}
