package com.example.finalproject;

import android.provider.BaseColumns;

public final class QuizDbLayout {

    private QuizDbLayout(){}

    public static class QuestionsColumn implements BaseColumns {
        public static final String Table_Name = "quiz_questions";
        //This column will contain the actual questions
        public static final String QUESTION_COLUMN = "quest";
        public static final String CHOICE1_COLUMN = "choice1";
        public static final String CHOICE2_COLUMN = "choice2";
        public static final String CHOICE3_COLUMN = "choice3";
        public static final String ANSWER_NUMBER_COLUMN = "answerNumber";
    }
}
