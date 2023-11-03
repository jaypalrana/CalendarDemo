# Project Details ::
# Project Name
CalendarDemo
## Language 
Kotlin
### Description 
The demo calendar view prepared to find list of holidays for selected day
#### Project Structure -
project structure based on MVVM architecture
-- Folder structure
```bash
app
   |   |-- main
   |   |   |-- java
   |   |   |   |-- com
   |   |   |   |   |-- example
   |   |   |   |   |   |-- calendar
   |   |   |   |   |   |   |-- activity
   |   |   |   |   |   |   |-- adapter
   |   |   |   |   |   |   |-- api
   |   |   |   |   |   |   |-- base
   |   |   |   |   |   |   |-- model
   |   |   |   |   |   |   |-- permissions
   |   |   |   |   |   |   |-- receiver
   |   |   |   |   |   |   |-- utils
   |   |   |   |   |   |   |-- viewmodel
   |   |   |-- res
   |   |   |   |-- drawable
   |   |   |   |-- font
   |   |   |   |-- layout
   |   |   |   |-- mipmap-anydpi-v26
   |   |   |   |-- mipmap-hdpi
   |   |   |   |-- mipmap-mdpi
   |   |   |   |-- mipmap-xhdpi
   |   |   |   |-- mipmap-xxhdpi
   |   |   |   |-- mipmap-xxxhdpi
   |   |   |   |-- values
```
***
## Folders Details

The folder structure of this app is explained below:

| Folder Name                                        | Description                                              |
|----------------------------------------------------|----------------------------------------------------------|
| app/src/main/java/com/example/calendar/activity    | This folder contains all Activity files                  |
| app/src/main/java/com/example/calendar/adapters    | This folder contain all RecyclerView Adapter             |
| app/src/main/java/com/example/calendar/models      | This folder contains all model files                     |
| app/src/main/java/com/example/calendar/api         | This folder contains all files which we used in api call |
| app/src/main/java/com/example/calendar/base        | This folder contains base files                          |
| app/src/main/java/com/example/calendar/fragment    | This folder contains all fragment files                  |
| app/src/main/java/com/example/calendar/permissions | This folder contains all permission related  files       |
| app/src/main/java/com/example/calendar/receiver    | This folder contains all broadcast receiver              |
| app/src/main/java/com/example/calendar/utils       | This folder contains constant and common function        |
| app/src/main/java/com/example/calendar/viewModel   | This folder contains all view model files                |
| app/src/res/drawable                               | App icons and Images and Drawable files                  |
| app/src/res/layout                                 | Layout Files                                             |
| app/src/res/mipmap-anydpi-v26                      | Launcher App icon                                        |
| app/src/res/mipmap-hdpi                            | Launcher App icon                                        |
| app/src/res/mipmap-mdpi                            | Launcher App icon                                        |
| app/src/res/mipmap-xhdpi                           | Launcher App icon                                        | 
| app/src/res/mipmap-xxhdpi                          | Launcher App icon                                        |
| app/src/res/mipmap-xxxhdpi                         | Launcher App icon                                        |  
| app/src/res/values/colors.xml                      | All Colors code Added in this file which are used in App |
| app/src/res/values/dimens.xml                      | Add Dimen in this file                                   |
| app/src/res/values/string.xml                      | Add All string in this file which are used in App        |
| app/src/res/values/styles.xml                      | Add All style in this file                               |
| app/src/res/font.xml                               | this contain all font style                              |
 


# Versions name with their code ::
Android Studio version : Android Studio Giraffe | 2022.3.1

## Dependencies (Packages and Library)

| name                                         | version       | Details                                                                                                                                       |
|----------------------------------------------|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| org.jetbrains.kotlin:kotlin-stdlib-jdk7      | 1.8.0         | The Kotlin Standard Library for JDK 7, providing essential utility functions and extensions for Kotlin programming                            |
| androidx.appcompat:appcompat                 | 1.6.1         | library that provides backward-compatible implementations of newer Android features and UI components                                         |
| androidx.constraintlayout:constraintlayout   | 2.1.4         | library that helps to create flexible and responsive user interfaces in Android by using a constraint-based layout                            |
| androidx.recyclerview:recyclerview           | 1.3.0         | AndroidX RecyclerView is a library that provides an improved and more flexible version of the RecyclerView widget.                            |  
| androidx.cardview:cardview                   | 1.0.0         | library in the AndroidX framework that provides a customizable and flexible CardView widget                                                   |
| androidx.core:core-ktx                       | 1.9.0         | Android's AndroidX Core libraries, providing Kotlin-specific extensions and utilities for Android app development.                            |
| com.google.android.material:material         | 1.9.0         | Google's Material Design Components for Android, offering UI components, styles, and resources                                                |
| com.intuit.sdp:sdp-android                   | 1.1.0         | library that provides a simple and consistent way to define and use dimension values                                                          |
| com.intuit.ssp:ssp-android                   | 1.9.0         | library for defining responsive and adaptable text sizes                                                                                      |
| androidx.lifecycle:lifecycle-runtime-ktx     | 2.6.1         | Kotlin extension library for Android's AndroidX Lifecycle components.                                                                         |
| androidx.lifecycle:lifecycle-extensions      | 2.2.0         | library that provides a set of additional extensions and utility classes for Android's AndroidX Lifecycle components,.                        |
| androidx.lifecycle:lifecycle-livedata-ktx    | 2.6.1         | Kotlin extension library for Android's AndroidX LiveData component, offering Kotlin-specific extensions and utilities to work with LiveData   |
| androidx.lifecycle:lifecycle-viewmodel-ktx   | 2.6.1         | Kotlin extension library for Android's AndroidX ViewModel component, providing Kotlin-specific extensions                                     |
| androidx.lifecycle:lifecycle-common-java8    | 2.6.1         | library that extends the AndroidX Lifecycle components to provide Java 8 support,                                                             |
| androidx.lifecycle:lifecycle-compiler        | 2.6.1         | library that provides a compiler for Android's AndroidX Lifecycle components                                                                  |
| com.squareup.retrofit2:retrofit              | 2.9.0         | library that provides the Retrofit HTTP client for Android application                                                                        |
| com.squareup.retrofit2:converter-gson        | 2.9.0         | library that provides a Gson converter for Retrofit 2. It allows you to serialize and deserialize JSON data using Google's Gson library       |
| com.squareup.okhttp3:logging-interceptor     | 5.0.0-alpha.5 | library that provides an OkHttp interceptor for logging HTTP requests and responses. It is often used with OkHttp to log network interactions |
| com.squareup.retrofit2:converter-scalars     | 2.7.0         | library that provides a Retrofit converter for Scalars                                                                                        |
| com.squareup.okhttp3:okhttp                  | 5.0.0-alpha.2 | library that provides OkHttp, a popular and efficient HTTP client for Android applications                                                    |
| com.google.code.gson:gson                    | 2.9.0         | library that provides Gson, a Java library from Google used for serializing and deserializing Java objects to and from JSON  data             |
| androidx.recyclerview:recyclerview           | 1.3.2         | library that provides the AndroidX RecyclerView component. RecyclerView is a powerful and flexible UI component for Android app development   |
| androidx.recyclerview:recyclerview-selection | 1.2.0-alpha01 | library that provides the AndroidX RecyclerView Selection library.                                                                            |




# SDK Version supports:
*Min SDK version required: 23*
*TargetSdk SDK version required: 33*

