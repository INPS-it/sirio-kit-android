# sirio-kit-android

sirio-kit-android is a library containing the android implementation of the Sirio design system used for INPS mobile apps UI.

## Requirements

- Android 5+

## Usage
1. Add dependency to your project:
```kotlin
repositories {
   maven {
        url = "https://maven.pkg.github.com/INPS-it/sirio-kit-android"
        credentials {
           username = GITHUB_USER
           password = GITHUB_TOKEN
        }
   }
}
dependencies {
    implementation 'it.inps.sirio:library:7.0.2'
}
```
2. Add following repository in your gradle file (es. settings.gradle)
```
maven { url 'https://jitpack.io' }
```
3. Use SirioTheme in the app
```kotlin
SirioTheme {

}
```
4. Use components as in the demo app

## License

sirio-kit-android is released under the BSD 3-Clause License. [See LICENSE](https://github.com/INPS-it/sirio-kit-android/blob/main/LICENSE) for details.
