plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.musicapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.musicapp"
        minSdk = 24
      targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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
    buildFeatures {
        viewBinding =  true
    }
}

dependencies {


    implementation ("androidx.multidex:multidex:2.0.1")
    //navigation
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    implementation ("androidx.navigation:navigation-runtime:2.7.7")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation ("com.google.firebase:firebase-database")
    // MaterialDialog
    implementation ("com.afollestad.material-dialogs:core:0.9.6.0")
    //Glide ImageLoader
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    // Circle Imageview
    implementation ("de.hdodenhof:circleimageview:3.0.0")
    // Indicator
    implementation ("me.relex:circleindicator:2.1.6")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}