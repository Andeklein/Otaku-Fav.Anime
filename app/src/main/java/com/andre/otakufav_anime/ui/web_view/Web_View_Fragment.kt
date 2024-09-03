package com.andre.otakufav_anime.ui.web_view
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.andre.otakufav_anime.R

class YouTubeWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_web__view_)

        val webView = findViewById<WebView>(R.id.webView)

        // WebView Einstellungen
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        // Verhindert das Öffnen von YouTube im Browser
        webView.webViewClient = WebViewClient()

        // Lade die YouTube-URL
        webView.loadUrl("https://www.youtube.com")
    }
}