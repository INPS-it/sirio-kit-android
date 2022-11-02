<!--
SPDX-FileCopyrightText: 2021 Istituto Nazionale Previdenza Sociale

SPDX-License-Identifier: BSD-3-Clause
-->

# SirioKitAndroid

SirioKitAndroid is a library containing the android implementation of Sirio design system to be used for INPS mobile apps UI

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
    implementation 'it.inps.sirio:library:x.y.z'
}
```
2. Use SirioTheme in the app
```kotlin
SirioTheme {

}
```
2. Use components as in the demo app

## License

SirioKitAndroid is released under the BSD 3-Clause License. [See LICENSE](https://github.com/INPS-it/sirio-kit-android/blob/main/LICENSE) for details.
