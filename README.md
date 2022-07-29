## Android Studio Project Github User Search

This application build for search github users


## Installation

Download:

    $ git clone https://github.com/joshephez/GithubUser.git

Import Project by Android Studio Menu > File > Import Project...

Run LVLSample by Android Studio Menu > Run > Run Myapp.  
If some issues are happened, try "Sync Project with Gradle Files" or "Rebuild Project" at Android Studio Menu

## Configuration

This application using Kotlin progamming languages with MVVM architectures
and this application have minimum requirement SDK 21  


This application also using internet permission, at AndroidManifest.xml

   <uses-permission android:name="android.permission.INTERNET"/>
   
   
For ui purposes this application added some icons including :

- ic_email.png
- ic_subscription.png
- ic_location.png
- ic_search.png
- ic_dot_space.png
- ic_followers.png

and build some custom shape on drawable including:

- edittextshape.xml
- linearlayoutshape.xml

   
This application uses several dependency libraries including :
 
 - Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.1'
    
  - Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.0'
    implementation "androidx.activity:activity-ktx:1.5.0"
    
  - Dagger & Hilt
    implementation 'com.google.dagger:hilt-android:2.41'
    kapt 'com.google.dagger:hilt-android-compiler:2.41'
    kapt 'androidx.hilt:hilt-compiler:1.0.0'
    
  - Glide
    implementation 'com.github.bumptech.glide:glide:4.13.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
    
  - Circle Images View
    implementation 'de.hdodenhof:circleimageview:3.1.0'


Run YourApp by Android Studio Menu > Run > Run YourApp.
