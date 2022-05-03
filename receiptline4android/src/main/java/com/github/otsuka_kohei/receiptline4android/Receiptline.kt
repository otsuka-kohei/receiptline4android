package com.github.otsuka_kohei.receiptline4android

import android.content.Context
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Receiptline {
    private lateinit var webView: WebView

    companion object {
        suspend fun getInstance(context: Context) =
            suspendCoroutine<Receiptline> { continuation ->
                val receiptImageGenerator = Receiptline()
                receiptImageGenerator.webView = WebView(context)
                receiptImageGenerator.webView.settings.javaScriptEnabled = true
                receiptImageGenerator.webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        continuation.resume(receiptImageGenerator)
                    }
                }
                receiptImageGenerator.webView.loadUrl("file:///android_asset/receipt.html")
            }
    }

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
                continuation.resume(decodeScgStr)
            }
        }
}