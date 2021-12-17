package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.finalproject.databinding.ActivityMainBinding;
import com.example.finalproject.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

//        getSupportFragmentManager().beginTransaction()
//                .remove(R.id.test1,fragment1)

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        // Define ActionBar object
//        ActionBar actionBar;
//        actionBar = getActionBar();
//
//        // Define ColorDrawable object and parse color
//        // using parseColor method
//        // with color hash code as its parameter
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("#7A9E9F"));
//
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }



}