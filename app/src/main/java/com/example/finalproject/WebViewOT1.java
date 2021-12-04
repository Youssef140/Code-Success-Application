package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewOT1 extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_ot1);

        webView = findViewById(R.id.webView);

//        Intent intent = getIntent();
//        if (intent != null) {
//            webView.getSettings().setJavaScriptEnabled(true);
////            webView.loadUrl(intent.getStringExtra("url"));
//            webView.loadUrl(intent.getStringExtra("https://developer.android.com/guide/webapps/webview?authuser=1#java"));
//        }

        webView.getSettings().setJavaScriptEnabled(true);
//            webView.loadUrl(intent.getStringExtra("url"));
        webView.loadUrl("https://www.tutorialspoint.com/android/android_webview_layout.htm");



    }


    @Override
    public void onBackPressed(){
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }



}