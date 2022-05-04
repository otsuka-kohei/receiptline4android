# receiptline4android
[![](https://jitpack.io/v/otsuka-kohei/receiptline4android.svg)](https://jitpack.io/#otsuka-kohei/receiptline4android)
  
Get Android bitmap from receipt image of [receiptline](https://github.com/receiptline/receiptline).

## How to use
### JitPack
Add this Maven repository to your root `build.gradle`.
```gradle
allprojects {
   repositories {
      maven { url 'https://jitpack.io' }
   }
}
```
Add this dependency to your module `build.gradle`.
```gradle
dependencies {
   implementation 'com.github.otsuka-kohei:receiptline4android:<version>'
}
```

### GitHub Packages
Add this Maven repository to your root `build.gradle`.
```gradle
allprojects {
   repositories {
      maven {
            name = "GitHubPackages-receiptline4android"
            url = uri("https://maven.pkg.github.com/otsuka-kohei/receiptline4android")
            credentials {
                username = "<Your GitHub user name>"
                password = "<Your GitHub personal access token>"
            }
        }
   }
}
```
Add this dependency to your module `build.gradle`.
```gradle
dependencies {
   implementation 'com.github.otsuka-kohei:receiptline4android:<version>'
}
```

## Dependencies
- [receiptline](https://github.com/receiptline/receiptline)
    * for generate receipt images
    * [receiptline.js](https://github.com/receiptline/receiptline/blob/a2137c539d2169569c3efe1fd1be9a6dfd4e3fa3/lib/receiptline.js) and [qrcode.js](https://github.com/receiptline/receiptline/blob/a2137c539d2169569c3efe1fd1be9a6dfd4e3fa3/lib/qrcode-generator/qrcode.js) are copied from [receiptline](https://github.com/receiptline/receiptline).
- [AndroidSVG](https://github.com/BigBadaboom/androidsvg)
    * for convert SVG to bitmap
