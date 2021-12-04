package com.example.finalproject.LanguagesActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.finalproject.R;

public class PythonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

        WebView javaWebView = findViewById(R.id.pythonWebView);
//        setContentView(javaWebView);
//        Intent intent = getIntent();

        javaWebView.getSettings().setLoadsImagesAutomatically(true);
        javaWebView.getSettings().setJavaScriptEnabled(true);
        javaWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        javaWebView.setWebViewClient(new WebViewClient());
        // Load the initial URL
        javaWebView.loadUrl("https://docs.oracle.com/en/java/");

    }
}