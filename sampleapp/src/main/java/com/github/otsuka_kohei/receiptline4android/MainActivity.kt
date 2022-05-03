package com.github.otsuka_kohei.receiptline4android

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainScope().launch {
            val receiptLine = Receiptline.getInstance(this@MainActivity)
            val bitmap = receiptLine.transformToBitmap(
                """{code:2012345678903;option:ean,hri}""",
                """{cpl:42,encoding:'multilingual'}"""
            )
            
            val imageView: ImageView = findViewById(R.id.imageView)
            imageView.setImageBitmap(bitmap)
        }
    }
}