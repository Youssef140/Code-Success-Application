package com.example.finalproject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class QuizzActivity1 extends AppCompatActivity {

    //Some features and concepts were inspired by a youtube channel that teaches android development


    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPref";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private TextView textViewHighScore;
    private int highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        loadHighScore();

        Button launchBtn = findViewById(R.id.button_launch_quiz);

        //Set listener for the action after we click the button
        launchBtn.setOnClickListener(v -> launchQuiz());
    }

    private void launchQuiz(){
        //Here we want to open our second activity after we click the Launch Quiz button
        //This is done by calling Intent to perform the transitions between activities
        Intent intent = new Intent(MainActivity.this,McqActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ)
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(McqActivity.EXTRA_SCORE,0);
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
}
