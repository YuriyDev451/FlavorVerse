plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
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
}

dependencies {
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

}