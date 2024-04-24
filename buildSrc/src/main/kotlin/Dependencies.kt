object Dependencies {
    object Api {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Coroutine{
        const val kotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    }

    object Glide{
        const val glideGlide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
    }
    object UI {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.compat}"
        const val material = "com.google.android.material:material:${Versions.materialComponents}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayoutVersion}"
    }
    object KotlinEx{
        const val coreCore = "androidx.core:core-ktx:${Versions.core}"
    }
    object Test {
        const val junitTest = "junit:junit:${Versions.junitJunit}"
        const val extJunitTest = "androidx.test.ext:junit:${Versions.testExtJunit}"
        const val espressoTest = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
    }
    object Navigation {
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }
}