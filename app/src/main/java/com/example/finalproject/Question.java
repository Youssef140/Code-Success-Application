package com.example.finalproject;

public class Question {

    //This class will contain different information such as the questions themselves
    //This class will also contain the different answer choices
    //In addition this class will contain the correct one
    //This class will serve as a bridge between the application and the database

    private String quest;
    private String choice1;
    private String choice2;
    private String choice3;
    private int answerNumber;

    //Constructor were we will pass all the 5 values above when we create an object of this class
    //So when we create a question object we will pass the question itself, the choices, and the answer number

    public Question(){

    }

    public Question(String quest, String choice1, String choice2, String choice3, int answerNumber) {
        this.quest = quest;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.answerNumber = answerNumber;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
