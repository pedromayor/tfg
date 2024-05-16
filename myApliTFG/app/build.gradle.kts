plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.myaplitfg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myaplitfg"
        minSdk = 31
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
    buildFeatures {
        viewBinding = true
    }
    //    packagingOptions{
//        exclude ("META-INF/NOTICE.md")
//        exclude ("META-INF/LICENSE.md")
//    }

//    }

}

dependencies {



    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    implementation("androidx.lifecycle:lifecycle-extensions:2.7.0")
//    //noinspection LifecycleAnnotationProcessorWithJava8
//    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.7.0")
//
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
//
//    implementation ("com.google.dagger:dagger-android:2.38.1")
//    annotationProcessor ("com.google.dagger:dagger-android-processor:2.38.1")

    // Picasso
    implementation ("com.squareup.picasso:picasso:2.71828")

    // Stetho
    implementation ("com.facebook.stetho:stetho:1.5.1")
    implementation ("com.facebook.stetho:stetho-okhttp3:1.5.1")
//
    //Libreia Spinner
 //   implementation ("com.github.ganfra:material-spinner:2.0.0")
//
//    //Java Mail
//    implementation ("com.sun.mail:android-mail:1.6.5")
//    implementation ("com.sun.mail:android-activation:1.6.5")
//
//    //Google Maps
//    implementation ("com.google.android.gms:play-services-maps:17.0.0")
//    implementation ("com.google.android.gms:play-services-places:16.0.0")

    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.google.android.gms:play-services-location:21.2.0")


    //Sweet Alert
    implementation ("com.github.f0ris.sweetalert:library:1.6.2")

    //ImagenView Circle
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //Carrusel
    implementation ("com.github.smarteist:autoimageslider:1.4.0")

    implementation ("com.github.bumptech.glide:glide:4.11.0")

//    implementation ("com.github.barteksc:android-pdf-viewer:2.8.2")

}