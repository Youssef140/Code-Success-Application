package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OT1Activity extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ot1);

        nextButton = findViewById(R.id.nextButton);
    }

    public void nextClicked(View view){
        Intent intent = new Intent(OT1Activity.this,OT2Activity.class);
        startActivity(intent);
    }
}