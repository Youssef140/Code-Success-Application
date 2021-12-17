package com.example.finalproject;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class QuizzActivity1 extends AppCompatActivity {

    //Some features and concepts were inspired by a youtube channel that teaches android development


    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPref";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private TextView textViewHighScore;
    private int highScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz1);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        loadHighScore();

        Button launchBtn = findViewById(R.id.button_launch_quiz);

        //Set listener for the action after we click the button
        launchBtn.setOnClickListener(v -> launchQuiz());

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

    private void launchQuiz(){
        //Here we want to open our second activity after we click the Launch Quiz button
        //This is done by calling Intent to perform the transitions between activities
        Intent intent = new Intent(QuizzActivity1.this,QuizzActivity2.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ)
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizzActivity2.EXTRA_SCORE,0);
                if (score>highScore){
                    updateHighScore(score);
                }
            }
    }

    //Load updated score from shared preferences to display it

    private void loadHighScore(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = sp.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("High Score: " + highScore);
    }

    private void updateHighScore(int newestHighScore){
        highScore = newestHighScore;
        textViewHighScore.setText("High Score: " + highScore);

        //Save this value in shared preferences

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt(KEY_HIGHSCORE, highScore);
        ed.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizzActivity1.this);
        builder.setTitle("Do you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(QuizzActivity1.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
        return true;
    }
}
