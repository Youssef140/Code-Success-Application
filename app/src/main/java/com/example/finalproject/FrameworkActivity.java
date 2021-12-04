package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.finalproject.LanguagesActivities.JavaActivity;
import com.example.finalproject.LanguagesActivities.PythonActivity;

import java.util.ArrayList;
import java.util.List;

public class FrameworkActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView frameworksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framework);

        frameworksListView = findViewById(R.id.frameworksListView);

        List<String> categoriesList = new ArrayList<>();

        categoriesList.add("Django");
        categoriesList.add("Laravel");
        categoriesList.add("Vue");
        categoriesList.add("Angular");
        categoriesList.add("React");
        categoriesList.add("Moqui");
        categoriesList.add("Spring");
        categoriesList.add("Flutter");
        categoriesList.add("Flask");
        categoriesList.add("ASP.NET");
        categoriesList.add("Ruby");



        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categoriesList);
        frameworksListView.setAdapter(categoriesAdapter);
        frameworksListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent pythonIntent = new Intent(FrameworkActivity.this, PythonActivity.class);
                startActivity(pythonIntent);
                break;
            case 1:
                Intent frameworksIntent = new Intent(FrameworkActivity.this, JavaActivity.class);
                startActivity(frameworksIntent);
                break;
            case 2:
                Intent CCIntent = new Intent(FrameworkActivity.this,LanguagesActivity.class);
                startActivity(CCIntent);
                break;
            default:
                break;
        }
    }
}