<p align="center">
  <img alt="Nimble logo" src="https://assets.nimblehq.co/logo/light/logo-light-text-320.png" />
</p>

---

# Android Common Extensions

This repository provides a collection of common Android extensions.

[![](https://jitpack.io/v/nimblehq/android-common-ktx.svg)](https://jitpack.io/#nimblehq/android-common-ktx)

## Usage

1. Add JitPack to your project build.gradle
    - Groovy

    ```groovy
    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
       }
    }
    ```

    - Kotlin DSL

    ```kotlin
    repositories {
    	...
    	maven(url = "https://jitpack.io")
    }
    ```

2. Add the dependency in the application build.gradle
    - Groovy

    ```groovy
    dependencies {
    	implementation 'com.github.nimblehq:android-common-ktx:<version>'
    }
    ```

    - Kotlin DSL

    ```kotlin
    implementation("com.github.nimblehq:android-common-ktx:<version>")
    ```

## Contributing

All contributions are welcome by opening an issue for discussion about what you would like to update. You can visit our [Wiki](https://github.com/nimblehq/android-common-ktx/wiki#how-to-contribute) for guidelines to contribute your ideas to our project.

## License

This project is Copyright (c) 2014 and onwards. It is free software,
and may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## About

![Nimble](https://assets.nimblehq.co/logo/dark/logo-dark-text-160.png)

This project is maintained and funded by Nimble.

We love open source and do our part in sharing our work with the community!
See [our other projects][community] or [hire our team][hire] to help build your product.

[community]: https://github.com/nimblehq
[hire]: https://nimblehq.co/
