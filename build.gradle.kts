plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android)       apply false
    alias(libs.plugins.kotlin.compose)       apply false  // keep this
    // ← delete the explicit line below
    // id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
}
