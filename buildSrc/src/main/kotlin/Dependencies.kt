object Dependencies {
    // App Ui Dependencies
    val uiDependencies by lazy {
        listOf(
            "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}",
            "androidx.appcompat:appcompat:${DependencyVersions.APP_COMPAT}",
            "com.google.android.material:material:${DependencyVersions.MATERIAL}",
            "androidx.constraintlayout:constraintlayout:${DependencyVersions.CONSTRAINT_LAYOUT}",
            "com.airbnb.android:lottie:${DependencyVersions.LOTTIE}",
        )
    }
    val coreKtxDependency by lazy { "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}" }

    // Test Dependencies
    val junitDependency by lazy { "junit:junit:${DependencyVersions.JUNIT}" }
    val junitKtx by lazy { "androidx.test.ext:junit-ktx:${DependencyVersions.ANDROID_JUNIT}" }

    val androidTestDependencies by lazy {
        listOf(
            "androidx.test.espresso:espresso-core:${DependencyVersions.ESPRESSO}",
            "androidx.test.ext:junit:${DependencyVersions.ANDROID_JUNIT}",
            "androidx.compose.ui:ui-test-junit4",
            composePlatformBom
        )
    }
    val debugImplementation by lazy {
        listOf(
            "androidx.compose.ui:ui-tooling",
            "androidx.compose.ui:ui-test-manifest"
        )
    }

    // navigation
    val navigationDependencies by lazy {
        listOf(
            "androidx.navigation:navigation-fragment-ktx:${DependencyVersions.ANDROID_NAVIGATION_FRAGMENT}",
            "androidx.navigation:navigation-ui-ktx:${DependencyVersions.ANDROID_NAVIGATION_UI}"
        )
    }

    // Networking Dependencies
    val retrofitDependencies by lazy {
        listOf(
            "com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT}",
            "com.squareup.retrofit2:converter-gson:${DependencyVersions.RETROFIT}",
            "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OKHTTP_INTERCEPTOR}",
            "com.google.code.gson:gson:${DependencyVersions.GSON}"
        )
    }

    val kotlinSerilization by lazy {
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependencyVersions.KOTLIN_SERILIZATOIN}"
    }

    val glideDependency by lazy {
        "com.github.bumptech.glide:glide:${DependencyVersions.GLIDE}"
    }

    val lottieDependency by lazy {
        "com.airbnb.android:lottie-compose:${DependencyVersions.LOTTIE_COMPOSE}"
    }

    // Coroutines Dependencies
    val coroutinesDependency by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.COROUTINES}"
    }

    // Live Data
    val liveDataDependencies by lazy {
        listOf(
            "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.LIVE_DATA_LIFE_CYCLE}",
            "androidx.activity:activity-ktx:${DependencyVersions.LIVE_DATA_ACTIVITY}",
            "androidx.fragment:fragment-ktx:${DependencyVersions.LIVE_DATA_FRAGMENT}",
            "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.RUN_TIME_LIFECYCLE_COMPOSE}"
        )
    }

    // Hilt
    val hiltDependencies by lazy {
        listOf(
            "com.google.dagger:hilt-android-gradle-plugin:${DependencyVersions.HILT}",
            "com.google.dagger:hilt-android:${DependencyVersions.HILT}"
        )
    }
    val hiltCompiler by lazy {
        "com.google.dagger:hilt-android-compiler:${DependencyVersions.HILT}"
    }
    val daggerAndroid by lazy {
        "com.google.dagger:dagger-android:${DependencyVersions.DAGGER_ANDROID}"
    }

    val swipeDependency by lazy {
        "it.xabaras.android:recyclerview-swipedecorator:${DependencyVersions.SWIPE}"
    }
    val coilDependency by lazy {
        listOf(
            "io.coil-kt:coil-svg:${DependencyVersions.COIL}"
        )
    }
    val dateStoreDependency by lazy {
        "androidx.datastore:datastore-preferences:${DependencyVersions.DATA_STORE}"
    }

    val composeDependency by lazy {
        listOf(
            "androidx.compose.material3:material3:${DependencyVersions.MATERIAL3_COMPOSE}",
            "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}",
            "androidx.compose.ui:ui",
            "androidx.compose.ui:ui-graphics",
            "androidx.compose.ui:ui-tooling-preview",
            "androidx.compose.foundation:foundation:${DependencyVersions.FOUNDATION_COMPOSE}",
            "androidx.compose.ui:ui-util:${DependencyVersions.UI_UTIL_COMPOSE}",
            "androidx.constraintlayout:constraintlayout-compose:${DependencyVersions.CONSTRAINT_LAYOUT_COMPOSE}",
            "com.google.accompanist:accompanist-systemuicontroller:${DependencyVersions.SYSTEM_UI_CONTROLLER}",
            "io.coil-kt:coil-compose:${DependencyVersions.COIL_COMPOSE}",
            "androidx.appcompat:appcompat:1.3.0-beta01",
            "androidx.hilt:hilt-navigation-compose:${DependencyVersions.HILT_COMPOSE}",
            "com.airbnb.android:lottie-compose:${DependencyVersions.LOTTIE_COMPOSE}",
            "androidx.compose.material:material:${DependencyVersions.MATERIAL_COMPOSE}",
            "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.RUN_TIME_LIFECYCLE_COMPOSE}"
        )
    }

    val composeUiTest by lazy { "androidx.compose.ui:ui-test-manifest" }
    val composeUiDependency by lazy {
        "androidx.compose.ui:ui-tooling:${DependencyVersions.MATERIAL_COMPOSE}"
    }
    val composePlatformBom by lazy {
        "androidx.compose:compose-bom:${DependencyVersions.PLATFORM_BOM_COMPOSE}"
    }
    val composePlatformBomAndroidTest by lazy {
        "androidx.compose:compose-bom:${DependencyVersions.PLATFORM_BOM_COMPOSE}"
    }

    val facebookUtilsDependency by lazy{
        "com.github.parse-community:ParseFacebookUtils-Android:${DependencyVersions.FacebookUtils}"
    }

    val facebookDependency by lazy { "com.facebook.android:facebook-login:latest.release" }

    val ktorDependency by lazy {
        listOf(
            "io.ktor:ktor-client-core:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-client-android:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-client-okhttp:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-client-content-negotiation:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-client-serialization:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-serialization-gson:${DependencyVersions.KTOR_VERSION}",
            "io.ktor:ktor-client-logging-jvm:${DependencyVersions.KTOR_LOGGING}",
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependencyVersions.KTOR_SERIALIZATION}",
            "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OKHTTP_INTERCEPTOR}",
        )
    }

    val splashDependency by lazy {
        "androidx.core:core-splashscreen:${DependencyVersions.SPLASH_SCREEN}"
    }

    val firebaseDependency by lazy {
        listOf(
            "com.google.firebase:firebase-crashlytics:${DependencyVersions.FIREBASE_CRASH}",
            "com.google.firebase:firebase-crashlytics-ktx:${DependencyVersions.FIREBASE_CRASH}",
            "com.google.firebase:firebase-analytics:${DependencyVersions.FIREBASE_ANALYTICS}",
            "com.google.firebase:firebase-auth",
            "com.google.android.gms:play-services-auth:${DependencyVersions.FIREBASE_AUTH}",
            "com.google.firebase:firebase-firestore"
        )
    }

    val firebaseBom by lazy {
        "com.google.firebase:firebase-bom:${DependencyVersions.FIREBASE_BOM}"
    }
    // Map
    val mapDependencies by lazy {
        listOf(
            "com.google.android.gms:play-services-maps:${DependencyVersions.MAP}",
            "com.google.android.gms:play-services-location:${DependencyVersions.GMS_LOCATION}",
            "com.google.maps.android:maps-compose:${DependencyVersions.MAPS_COMPOSE}",
        )
    }

    // Paging
    val pagingRuntime by lazy {
        "androidx.paging:paging-runtime:${DependencyVersions.PagingRuntimeVersion}"
    }
    val pagingCompose by lazy {
        "androidx.paging:paging-compose:${DependencyVersions.PagingComposeVersion}"
    }
    val pagingCommon by lazy {
        "androidx.paging:paging-common:${DependencyVersions.PagingRuntimeVersion}"
    }

    object Classpath {

        val navigationClasspath by lazy {
            "androidx.navigation:navigation-safe-args-gradle-plugin:${DependencyVersions.ClasspathVersions.NAVIGATION_CLASSPATH}"
        }
        val toolsBuildClasspath by lazy {
            "com.android.tools.build:gradle:${DependencyVersions.ClasspathVersions.TOOLS_BUILD_CLASSPATH}"
        }
        val googleServiceClasspath by lazy {
            "com.google.gms:google-services:${DependencyVersions.ClasspathVersions.GOOGLE_SERVICES_CLASSPATH}"
        }
        val googleFirebaseCrashClasspath by lazy {
            "com.google.firebase:firebase-crashlytics-gradle:${DependencyVersions.ClasspathVersions.FIREBASE_CRASH_CLASSPATH}"
        }
        val kotlinSerializationClasspath by lazy {
            "org.jetbrains.kotlin:kotlin-serialization:${DependencyVersions.ClasspathVersions.KOTLIN_SERIALZATION_CLASSPATH}"
        }
        val googleLibrariesMapsPlatformClasspath by lazy{
            "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${DependencyVersions.ClasspathVersions.MAPS_PLATFORM_CLASSPATH}"
        }
    }

}