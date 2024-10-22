package com.example.keycloaklogin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webview)
        webView.settings.javaScriptEnabled = true
        webView.clearCache(true) // Clear any existing cache

        // Disable caching by setting cache mode to LOAD_NO_CACHE
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        // Load the Keycloak login URL
        webView.loadUrl("http://192.168.252.124:8081/login") // Use the emulator address

        // WebViewClient to handle redirection and URL loading
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.startsWith("myapp://callback")) {
                    // The URL contains the JWT token, extract it
                    handleRedirect(Uri.parse(url))
                    return true
                }
                return false
            }
        }
    }

    private fun handleRedirect(uri: Uri) {
        // Extract the JWT token from the URL
        val idToken = uri.getQueryParameter("id_token")
        val accessToken = uri.getQueryParameter("access_token")
        val refreshToken = uri.getQueryParameter("refresh_token")

        if (idToken != null) {
            // Token is successfully extracted, you can now handle it
            val intent = Intent(this, NextActivity::class.java)
            intent.putExtra("id_token", idToken)
            startActivity(intent)
            finish()
        } else {
            // Handle error or missing token here
        }
    }
}
