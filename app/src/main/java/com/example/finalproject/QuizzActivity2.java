package com.example.finalproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizzActivity2 extends AppCompatActivity {
    public static final String EXTRA_SCORE = "exScore";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView questionCount;
    private TextView countDown;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button submitButton;

    private ColorStateList colorStateList;
    private int questCounter;
    private int questCountTotal;
    private Question currentQuest;

    private int score;
    private boolean isAnswered;

    private long backPressedTime;

    private List<Question> questionList;


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

        colorStateList = radioButton1.getTextColors();//save the default color in colorStateList

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        questionList = dataBaseHelper.getQuestions();
        questCountTotal = questionList.size();
        //shuffle question list to get questions in a random order
        Collections.shuffle(questionList);

        //Once activity start show first question
        displayNextQuestion();
        //To confirm answer chosen by the user
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAnswered){
                    //CHECK FIRST IF RADIO BUTTON IS SELECTED IF NOT DISPLAY TOAST MESSAGE
                    if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()){
                        checkSelectedAnswer();
                    }else{
                        //if now radio button is selected show toast message
                        Toast.makeText(QuizzActivity2.this, "No selected answer, Please select one of the previous choices", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //if question was already answered show next question
                    displayNextQuestion();
                }
            }
        });

    }
    private void displayNextQuestion(){
        radioButton1.setTextColor(colorStateList);
        radioButton2.setTextColor(colorStateList);
        radioButton3.setTextColor(colorStateList);
        //Once we show new question we want all radio buttons to be unselected
        radioGroup.clearCheck();
        //The following conditional statement will deal with if we have an additional question to display
        //then do so otherwise return to starting screen
        if(questCounter<questCountTotal){
            currentQuest = questionList.get(questCounter);
            textViewQuestion.setText(currentQuest.getQuest());
            radioButton1.setText(currentQuest.getChoice1());
            radioButton2.setText(currentQuest.getChoice2());
            radioButton3.setText(currentQuest.getChoice3());
            questCounter++;
            questionCount.setText("Question: " + questCounter + "/" + questCountTotal);
            isAnswered = false;
            submitButton.setText("Confirm Choice");
        }else{
            quizFinished();
        }
    }

    private void checkSelectedAnswer(){
        //set it to true at first since our question was answered
        isAnswered = true;
        //return id of which radio button was checked and save it in the radioButtonSelected variable
        RadioButton radioButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        //turn it to a number since we saved the selected answer as a number in the database
        int answeredNumber = radioGroup.indexOfChild(radioButtonSelected) + 1; //+1 since we start our answer Number at 1 and not at index 0
        //conditional statement to check if selected answer is the same as stored in the DB we want to increase score
        if(answeredNumber == currentQuest.getAnswerNumber()){
            score++;
            textViewScore.setText("Score: " + score);
        }

        showAnswer();
    }

    private void showAnswer(){
        radioButton1.setTextColor(Color.RED);
        radioButton2.setTextColor(Color.RED);
        radioButton3.setTextColor(Color.RED);

        switch (currentQuest.getAnswerNumber()){
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
        if(questCounter<questCountTotal){
            submitButton.setText("Next Question");
        }else{
            submitButton.setText("End the Quiz");
        }

    }

    private void quizFinished(){
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
        if(backPressedTime + 1500 > System.currentTimeMillis()){
            quizFinished();
        }else{
            //more than 1.5 seconds
            Toast.makeText(this, "Press back button again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}