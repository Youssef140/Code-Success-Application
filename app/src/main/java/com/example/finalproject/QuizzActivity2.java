package com.example.finalproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizzActivity2 extends AppCompatActivity {
   public static final String EXTRA_SCORE = "exScore";
    public static final long COUNTDOWN_TIMER = 20000;

    private static final String SCORE = "score";
    private static final String QUESTION_COUNT = "questionCount";
    private static final String TIME_LEFT = "timeLeft";
    private static final String ANSWERED = "answered";
    private static final String QUESTION_LIST = "questionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView questionCount;
    private TextView countDown;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button submitButton;
    private ColorStateList colorStateListRadioButton;
    private ColorStateList colorStateListCountDown;
    private CountDownTimer cdt;
    private long TimeLeft;
    private int questCounter;
    private int questCountTotal;
    private Question currentQuest;
    private int score;
    private boolean isAnswered;
    private long backPressedTime;
    private ArrayList<Question> questionList;
    private MediaPlayer mp;
    private MediaPlayer mp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz2);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        questionCount = findViewById(R.id.text_view_questionCount);
        countDown = findViewById(R.id.text_view_timer);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radio_button_choice1);
        radioButton2 = findViewById(R.id.radio_button_choice2);
        radioButton3 = findViewById(R.id.radio_button_choice3);
        submitButton = findViewById(R.id.button_submit);
        mp = MediaPlayer.create(this,R.raw.sample);
        mp1 = MediaPlayer.create(this,R.raw.sample1);

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

        colorStateListRadioButton = radioButton1.getTextColors();//save the default color in colorStateListRadioButtonRadioButton
        colorStateListCountDown = countDown.getTextColors();

        if(savedInstanceState == null){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        questionList = (ArrayList<Question>) dataBaseHelper.getQuestions();
        questCountTotal = questionList.size();
        //shuffle question list to get questions in a random order
        Collections.shuffle(questionList);

        //Once activity start show first question
        displayNextQuestion();
        }else{
            questionList = savedInstanceState.getParcelableArrayList(QUESTION_LIST);
            if(questionList == null){ //if null after getting it from the savedInstanceState so finish activity because we don't have a question list
                finish();
            }
            questCountTotal = questionList.size();
            questCounter = savedInstanceState.getInt(QUESTION_COUNT);
            currentQuest = questionList.get(questCounter - 1);//question counter is one question ahead so add -1 to avoid an exception at the last question
            score = savedInstanceState.getInt(SCORE);
            TimeLeft = savedInstanceState.getLong(TIME_LEFT);
            isAnswered = savedInstanceState.getBoolean(ANSWERED);

            if(!isAnswered){
                startCountDown();
            }else{
                updateCountDownDisplay();
                showAnswer();
            }

        }
        //To confirm answer chosen by the user
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswered) {
                    //CHECK FIRST IF RADIO BUTTON IS SELECTED IF NOT DISPLAY TOAST MESSAGE
                    if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()) {
                        checkSelectedAnswer();
                    } else {
                        //if no radio button is selected show toast message
                        Toast.makeText(QuizzActivity2.this, "No selected answer, Please select one of the previous choices", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //if question was already answered show next question
                    displayNextQuestion();
                }
            }
        });

    }

    private void displayNextQuestion() {
        radioButton1.setTextColor(colorStateListRadioButton);
        radioButton2.setTextColor(colorStateListRadioButton);
        radioButton3.setTextColor(colorStateListRadioButton);
        //Once we show new question we want all radio buttons to be unselected
        radioGroup.clearCheck();
        //The following conditional statement will deal with if we have an additional question to display
        //then do so otherwise return to starting screen
        if (questCounter < questCountTotal) {
            currentQuest = questionList.get(questCounter);
            textViewQuestion.setText(currentQuest.getQuest());
            radioButton1.setText(currentQuest.getChoice1());
            radioButton2.setText(currentQuest.getChoice2());
            radioButton3.setText(currentQuest.getChoice3());
            questCounter++;
            questionCount.setText("Question: " + questCounter + "/" + questCountTotal);
            isAnswered = false;
            submitButton.setText("Confirm Choice");

            //Once we go to a new question directly start the count down
            TimeLeft = COUNTDOWN_TIMER;
            startCountDown();

        } else {
            quizFinished();
        }
    }

    private void startCountDown(){
        cdt = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished;
                updateCountDownDisplay();
            }

            @Override
            public void onFinish() {
                //usually the timer skips the last onTick so our timer will display 1 second even if it finished
                //Here we are going to solve this issue
                TimeLeft = 0;
                updateCountDownDisplay();
                checkSelectedAnswer();//if we clicked an option to keep it locked as an answer
            }
        }.start();
    }

    private void updateCountDownDisplay(){
        int minutes = (int)(TimeLeft / 1000) / 60;
        int seconds = (int)(TimeLeft / 1000) % 60;

        String timeBeautified = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDown.setText(timeBeautified);
        //change text color if time is <10 seconds
        if(TimeLeft < 10000){
            countDown.setTextColor(Color.RED);
        }else{
            //if above 10<time<20
            countDown.setTextColor(colorStateListCountDown);
        }
    }

    private void checkSelectedAnswer() {
        //set it to true at first since our question was answered
        isAnswered = true;
        //if we picked an answer we want to stop our timer
        cdt.cancel();
        //return id of which radio button was checked and save it in the radioButtonSelected variable
        RadioButton radioButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        //turn it to a number since we saved the selected answer as a number in the database
        int answeredNumber = radioGroup.indexOfChild(radioButtonSelected) + 1; //+1 since we start our answer Number at 1 and not at index 0
        //conditional statement to check if selected answer is the same as stored in the DB we want to increase score
        if (answeredNumber == currentQuest.getAnswerNumber()) {
            mp.start();
            score++;
            textViewScore.setText("Score: " + score);
        }else{
            mp1.start();
        }

        showAnswer();
    }

    private void showAnswer() {
        radioButton1.setTextColor(Color.RED);
        radioButton2.setTextColor(Color.RED);
        radioButton3.setTextColor(Color.RED);

        switch (currentQuest.getAnswerNumber()) {
            case 1:
                radioButton1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is the correct Answer");
                break;

            case 2:
                radioButton2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is the correct Answer");
                break;

            case 3:
                radioButton3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is the correct Answer");
                break;
        }

        //check if this is the last question or if we have any questions left
        if (questCounter < questCountTotal) {
            submitButton.setText("Next Question");
        } else {
            submitButton.setText("End the Quiz");
        }

    }

    private void quizFinished() {
        Intent resultIn = new Intent();
        resultIn.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIn);
        finish();
    }

    @Override
    public void onBackPressed() {
        /*if first time we click the back button this if clause will not be caught because back time is 0 and System.currentTimeMillis
        is much bigger so we save currentTimeMillis is save in backPressed so the next time we press the back button we compare backPressed
        +1500 to currentTime
         */
        if (backPressedTime + 1500 > System.currentTimeMillis()) {
            quizFinished();
        } else {
            //more than 1.5 seconds
            Toast.makeText(this, "Press back button again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //when our activity is finished we have to cancel our timer or it will stay running in the background
        if(cdt != null){
            cdt.cancel();
        }
    }

    //To save values when rotation of the screen occurs
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE,score);
        outState.putInt(QUESTION_COUNT,questCounter);
        outState.putLong(TIME_LEFT,TimeLeft);
        outState.putBoolean(ANSWERED,isAnswered);
        outState.putParcelableArrayList(QUESTION_LIST,questionList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.mainmenu,menu);
        return true;
    }

}
