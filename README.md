# receiptline4android
[![](https://jitpack.io/v/otsuka-kohei/receiptline4android.svg)](https://jitpack.io/#otsuka-kohei/receiptline4android)
  
Get Android bitmap from receipt image of [receiptline](https://github.com/receiptline/receiptline).

## How to use
### Add dependency
#### Method 1: JitPack (Recommended)
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

#### Method 2: GitHub Packages
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
   implementation 'com.github.otsuka_kohei:receiptline4android:<version>'
}
```

### Examples
#### Input
```kotlin
MainScope().launch {
  val receiptLine = Receiptline.getInstance(this@MainActivity)
  val bitmap = 
    receiptLine.transformToBitmap(
      """{w:*,4,8}\n-\n|ITEM | QTY| AMOUNT|\n-\n_Apple | 1| 1.00\n"Banana | 2| 2.00\n`Cherry | 3| 3.00\n-\n{w:*,16}\n^^TOTAL | ^^^6.00\n{c:201234567890;o:ean,hri}\n\n{c:Receiptline;o:qrcode,4}""",
      dpi = 150f
    )
  val imageView: ImageView = findViewById(R.id.imageView)
  imageView.setImageBitmap(bitmap)
}
```
#### Output
<img src="https://user-images.githubusercontent.com/20738169/166691688-fd230dc6-1ca0-42bb-a23e-f8c70771bceb.png" width="300px">

## Dependencies
- [receiptline](https://github.com/receiptline/receiptline)
    * for generate receipt images
    * [receiptline.js](https://github.com/receiptline/receiptline/blob/a2137c539d2169569c3efe1fd1be9a6dfd4e3fa3/lib/receiptline.js) and [qrcode.js](https://github.com/receiptline/receiptline/blob/a2137c539d2169569c3efe1fd1be9a6dfd4e3fa3/lib/qrcode-generator/qrcode.js) are copied from [receiptline](https://github.com/receiptline/receiptline).
- [AndroidSVG](https://github.com/BigBadaboom/androidsvg)
    * for convert SVG to bitmap
