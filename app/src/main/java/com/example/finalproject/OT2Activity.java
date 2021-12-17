package com.example.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class OT2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView1;
    String[] months;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ot2);


        listView1 = findViewById(R.id.listView1);
        months = new DateFormatSymbols().getMonths();
        List<String> categoriesList = new ArrayList<>();

        categoriesList.add("Languages");
        categoriesList.add("Frameworks");
        categoriesList.add("Cloud Computing");
        categoriesList.add("Machine Learning and AI");


        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categoriesList);
        listView1.setAdapter(monthAdapter);
        listView1.setOnItemClickListener(this);

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



    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent languagesIntent = new Intent(OT2Activity.this,LanguagesActivity.class);
                startActivity(languagesIntent);
                break;
            case 1:
                Intent frameworksIntent = new Intent(OT2Activity.this,FrameworkActivity.class);
                startActivity(frameworksIntent);
                break;
            case 2:
                Intent CCIntent = new Intent(OT2Activity.this,LanguagesActivity.class);
                startActivity(CCIntent);
                break;
            default:
                break;
        }
    }


}


