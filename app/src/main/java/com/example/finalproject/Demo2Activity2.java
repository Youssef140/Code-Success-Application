package com.example.finalproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Demo2Activity2 extends AppCompatActivity {

    TextView percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo22);

        percentage = findViewById(R.id.percentage2);

        String confidence = getIntent().getStringExtra("confidence");

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#7A9E9F"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);


        System.out.println(">>>>>>>Confidence: "+confidence);
        double conf = Double.parseDouble(confidence);
        percentage.setText(((int) (conf*100))+"%");

    }
}