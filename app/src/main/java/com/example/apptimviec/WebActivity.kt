package com.example.apptimviec

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.toUri

class WebActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        if(intent != null){
            Toast.makeText(this, intent.getStringExtra("url"), Toast.LENGTH_LONG).show();
            webViewSetup()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup()
    {
        val web = findViewById<WebView>(R.id.webView)
        web.webViewClient = WebViewClient()
        web.apply {
            val url = intent.getStringExtra("url").toString()
            //val url = "https://esuhai.com/tuyen-dung-viec-lam-esuhai.html?utm_source=jobstreetvn&utm_campaign=jobstreetvn&utm_medium=organic"
            loadUrl(url)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
       // Toast.makeText(this, intent.getStringExtra("url"), Toast.LENGTH_LONG).show();
    }
}