plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)

    id("com.google.gms.google-services")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.gukunov.flavorverse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gukunov.flavorverse"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation(project(":features"))
    implementation(project(":common"))


    implementation(Dependencies.UI.material)
    implementation(Dependencies.UI.constraintLayout)
    implementation(Dependencies.UI.appCompat)
    implementation(Dependencies.KotlinEx.coreCore)
    implementation(Dependencies.Navigation.navigationFragment)
    implementation(Dependencies.Navigation.navigationUi)
    testImplementation(Dependencies.Test.junitTest)
    androidTestImplementation(Dependencies.Test.extJunitTest)
    androidTestImplementation(Dependencies.Test.espressoTest)

    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hiltKapt)

    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")

}