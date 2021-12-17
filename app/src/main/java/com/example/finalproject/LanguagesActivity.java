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

import com.example.finalproject.LanguagesActivities.JavaActivity;
import com.example.finalproject.LanguagesActivities.PythonActivity;

import java.util.ArrayList;
import java.util.List;

public class LanguagesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView languagesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        languagesListView = findViewById(R.id.languagesListView);
        List<String> categoriesList = new ArrayList<>();

        categoriesList.add("Python");
        categoriesList.add("Java");
        categoriesList.add("C");
        categoriesList.add("C#");
        categoriesList.add("C++");
        categoriesList.add("JavaScript");
        categoriesList.add("HTML/CSS");
        categoriesList.add("Dart");
        categoriesList.add("Kotlin");
        categoriesList.add("Swift");
        categoriesList.add("Ruby");
        categoriesList.add("GO");


        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categoriesList);
        languagesListView.setAdapter(categoriesAdapter);
        languagesListView.setOnItemClickListener(this);

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
                Intent pythonIntent = new Intent(LanguagesActivity.this, PythonActivity.class);
                startActivity(pythonIntent);
                break;
            case 1:
                Intent frameworksIntent = new Intent(LanguagesActivity.this, JavaActivity.class);
                startActivity(frameworksIntent);
                break;
            case 2:
                Intent CCIntent = new Intent(LanguagesActivity.this,LanguagesActivity.class);
                startActivity(CCIntent);
                break;
            default:
                break;
        }
    }
}