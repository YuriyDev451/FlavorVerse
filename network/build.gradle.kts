plugins {
    id(Plugins.androidLibrary)
    id(Plugins.jetbrainsKotlin)

    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    namespace = "com.gukunov.network"
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


    implementation(Dependencies.KotlinEx.coreCore)
    implementation(Dependencies.UI.appCompat)
    implementation(Dependencies.UI.material)
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    testImplementation(Dependencies.Test.junitTest)
    androidTestImplementation(Dependencies.Test.extJunitTest)
    androidTestImplementation(Dependencies.Test.espressoTest)


    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hiltKapt)

    implementation(Dependencies.Api.retrofit)
    implementation(Dependencies.Api.retrofitGson)
    implementation(Dependencies.Api.okhttp)
    implementation(Dependencies.Api.okhttpLogging)
    implementation(Dependencies.Api.gson)


}