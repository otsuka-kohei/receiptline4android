package com.github.otsuka_kohei.receiptline4android.sample

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.otsuka_kohei.receiptline4android.Receiptline
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}