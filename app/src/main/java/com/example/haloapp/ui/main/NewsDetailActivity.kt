package com.example.haloapp.ui.main

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.haloapp.R
import kotlinx.android.synthetic.main.news_detail_activity.*

/**
 * Created by Deepak Mandhani on 2020-06-26.
 */

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS_URL = "news_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail_activity)
        val newsUrl = intent.getStringExtra(NEWS_URL)
        val settings = wv_news.settings
        settings.javaScriptEnabled = true
        wv_news.webViewClient = WebViewClient()
        newsUrl?.let {
            wv_news.loadUrl(newsUrl)
        } ?: super.onBackPressed()
    }


}
