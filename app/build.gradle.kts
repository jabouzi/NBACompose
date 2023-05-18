plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions.add("NBATeamViewer")
    productFlavors {
        create("dev") {
            buildConfigField( "String", "TYPE", "\"dev\"")
            buildConfigField( "String", "BASE_URL",  "\"http://skanderjabouzi.com/nbateamviewer/\"")
        }
        create("mock") {
            buildConfigField("String", "TYPE", "\"mock\"")
            buildConfigField("String", "BASE_URL",  "\"http:/mock.api/\"")
        }
    }

    variantFilter {
        if (buildType.name == "release" && flavors[0].name == "development") {
            ignore = true
        }
        if (buildType.name == "debug" && flavors[0].name == "production") {
            ignore = true
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.0")
    implementation ("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation ("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
    implementation ("androidx.compose.material3:material3:1.1.0-beta02")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("io.coil-kt:coil:2.3.0")
    implementation ("com.google.dagger:hilt-android:2.44.2")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.8.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("androidx.room:room-ktx:2.5.1")
    kapt ("androidx.room:room-compiler:2.5.1")
    implementation ("io.coil-kt:coil-compose:2.3.0")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    lintChecks("com.slack.lint.compose:compose-lint-checks:1.1.1")
    lintChecks("com.slack.lint:slack-lint-checks:0.2.3")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation ("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_version"]}")
}