package com.github.otsuka_kohei.receiptline4android

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.caverock.androidsvg.SVG
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Receiptline {
    private lateinit var webView: WebView


    companion object {

        /**
         * Get instance of Receiptline.
         *
         * @param context
         * @return instance of Receiptline.
         */
        suspend fun getInstance(context: Context) =
            suspendCoroutine<Receiptline> { continuation ->
                Log.d("Receiptline ", "start instantiation")
                val receiptImageGenerator = Receiptline()
                receiptImageGenerator.webView = WebView(context)
                receiptImageGenerator.webView.settings.javaScriptEnabled = true
                receiptImageGenerator.webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        Log.d("Receiptline ", "finish instantiation")
                        continuation.resume(receiptImageGenerator)
                    }
                }
                receiptImageGenerator.webView.loadUrl("file:///android_asset/receipt.html")
            }
    }


    /**
     * Get SVG string of receipt from receiptline.
     *
     * @param doc String of receiptline DSL (parameter for receiptline.js).
     * @param display JSON string of display option (parameter for receiptline.js).
     * @return SVG string of receipt image.
     */
    suspend fun transform(doc: String, display: String): String =
        suspendCoroutine { continuation: Continuation<String> ->
            val js = """
                const doc = '${doc}';
                const display = ${display};
                const svg = receiptline.transform(doc, display);
                const svgStr = svg.toString();
                window.btoa(svgStr);
            """.trimIndent()

            webView.evaluateJavascript(js) {
                val decodeScgStr = String(Base64.decode(it, Base64.DEFAULT))
                Log.d("SVG string", decodeScgStr)
                continuation.resume(decodeScgStr)
            }
        }


    private fun setResolution(
        svg: SVG,
        dpi: Float = -1f,
        widthPixel: Float = -1f,
        heightPixel: Float = -1f
    ): SVG {
        var m = 1f

        // Priority to apply: [high] dpi > widthPixel > heightPixel [low]
        if (dpi != -1f) {
            m = dpi / svg.renderDPI
        } else if (widthPixel != -1f) {
            m = widthPixel / svg.documentWidth
        } else if (heightPixel != -1f) {
            m = heightPixel / svg.documentHeight
        }

        svg.renderDPI = svg.renderDPI * m
        svg.documentWidth = svg.documentWidth * m
        svg.documentHeight = svg.documentHeight * m

        return svg
    }


    /**
     * Get bitmap of receipt from receiptline.
     *
     * @param doc String of receiptline DSL (parameter for receiptline.js).
     * @param display JSON string of display option (parameter for receiptline.js).
     * @param dpi dpi of bitmap. If this parameter is not specified, default dpi of AndroidSVG (96 dpi) is used.
     * @param widthPixel width of bitmap. If dpi is specified, this parameter is ignored.
     * @param heightPixel height of bitmap. If either dpi or widthPixel is specified, this parameter is ignored.
     * @return bitmap of receipt image.
     */
    suspend fun transformToBitmap(
        doc: String,
        display: String,
        dpi: Float = -1f,
        widthPixel: Float = -1f,
        heightPixel: Float = -1f
    ): Bitmap {
        val svgStr = transform(doc, display)
        var svg = SVG.getFromString(svgStr)
        svg = setResolution(svg, dpi, widthPixel, heightPixel)
        Log.d(
            "bitmap resolution",
            " dpi:${svg.renderDPI} width:${svg.documentWidth} height:${svg.documentHeight}"
        )
        val bitmap = Bitmap.createBitmap(
            svg.documentWidth.toInt(),
            svg.documentHeight.toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        svg.renderToCanvas(canvas)
        return bitmap
    }
}