plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT_LIBRARY)
    id(Plugins.SERIALIZATION)
    id(Plugins.GOOGLE_SERVICES)
}

android {
    namespace = "org.haidy.servify"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "org.haidy.servify"
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = ConfigData.JAVA_VERSIONS_CODE
        targetCompatibility = ConfigData.JAVA_VERSIONS_CODE
    }
    kotlinOptions {
        jvmTarget = ConfigData.JAVA_VERSIONS_CODE.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}

dependencies {
    Dependencies.firebaseDependency.forEach { implementation(it) }
    implementation(platform(Dependencies.firebaseBom))
    implementation(Dependencies.glideDependency)
    Dependencies.retrofitDependencies.forEach { implementation(it) }
    Dependencies.uiDependencies.forEach { implementation(it) }
    Dependencies.composeDependency.forEach { implementation(it) }
    Dependencies.coilDependency.forEach{ implementation(it) }
    testImplementation(Dependencies.junitDependency)
    Dependencies.androidTestDependencies.forEach { androidTestImplementation(it) }
    implementation(Dependencies.composePlatformBom)
    androidTestImplementation(platform(Dependencies.composePlatformBomAndroidTest))
    Dependencies.navigationDependencies.forEach { implementation(it) }
    Dependencies.hiltDependencies.forEach { implementation(it) }
    kapt(Dependencies.hiltCompiler)
    Dependencies.liveDataDependencies.forEach { implementation(it) }
    implementation(Dependencies.facebookDependency)
    implementation (Dependencies.daggerAndroid)
    implementation(Dependencies.coroutinesDependency)
    implementation(Dependencies.dateStoreDependency)
    implementation(Dependencies.kotlinSerilization)
    implementation(Dependencies.lottieDependency)
}