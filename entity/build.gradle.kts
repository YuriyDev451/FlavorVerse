plugins {
    id(Plugins.androidLibrary)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.parcelize)
}

android {
    namespace = "com.gukunov.entity"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation("com.google.code.gson:gson:2.8.8")


    implementation(Dependencies.KotlinEx.coreCore)
    implementation(Dependencies.UI.appCompat)
    implementation(Dependencies.UI.material)
    testImplementation(Dependencies.Test.junitTest)
    androidTestImplementation(Dependencies.Test.extJunitTest)
    androidTestImplementation(Dependencies.Test.espressoTest)
}