plugins {
    id(Plugins.androidLibrary)
    id(Plugins.jetbrainsKotlin)
    id("com.google.gms.google-services")
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)

}

android {
    namespace = "com.gukunov.features"
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
    buildFeatures{
        viewBinding = true
    }


}


dependencies {
    implementation(platform("com.google.firebase:firebase-bom:31.5.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.5.0")

    implementation("com.github.Dimezis:BlurView:version-2.0.3")

    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":entity"))

    implementation(Dependencies.Hilt.hilt)
    implementation(Dependencies.Hilt.hiltKapt)
    kapt(Dependencies.Hilt.hiltKapt)


    implementation(Dependencies.Glide.glideGlide)
    implementation(Dependencies.Glide.glideCompiler)


    implementation(Dependencies.KotlinEx.coreCore)
    implementation(Dependencies.UI.appCompat)
    implementation(Dependencies.UI.material)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    testImplementation(Dependencies.Test.junitTest)
    androidTestImplementation(Dependencies.Test.extJunitTest)
    androidTestImplementation(Dependencies.Test.espressoTest)
}