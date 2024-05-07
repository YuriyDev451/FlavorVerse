buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    id("com.android.application") version "8.2.2" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
//    id("com.android.library") version "8.2.2" apply false

    id(Plugins.androidApplication) version Versions.androidApplication apply false
    id(Plugins.jetbrainsKotlin) version Versions.jetbrainsKotlin apply false
    id(Classpath.hilt) version Versions.hilt apply false
    id("com.google.gms.google-services") version "4.4.1" apply false


}