package com.example.victor.testb2w.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.example.victor.testb2w.R
import android.webkit.WebViewClient


class ProductDetail : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val bundle = intent.extras
        var productUrl = ""

        if(bundle != null)
            productUrl = bundle.getString("productUrl")

        val htmlWebView = findViewById<WebView>(R.id.webview)
        htmlWebView.webViewClient = CustomWebViewClient()
        val webSetting = htmlWebView.settings
        webSetting.javaScriptEnabled = true
        webSetting.displayZoomControls = true
        htmlWebView.loadUrl(productUrl)
    }

    private inner class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}
