package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Demo1Activity extends AppCompatActivity {

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        nextButton = findViewById(R.id.nextButton);

    }


    public void nextClicked(View view){
        Intent intent = new Intent(Demo1Activity.this, Demo2Activity.class);
        startActivity(intent);
    }



}