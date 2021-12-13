package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.finalproject.QuizDbLayout.QuestionsColumn;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + USER_TABLE;

    private SQLiteDatabase database;


    public DataBaseHelper(@Nullable Context context) {
        super(context, "code_success.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.database = db;

        String createTableStatement = "CREATE TABLE "+USER_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD
                +" TEXT,"+COLUMN_FIRST_NAME+" TEXT,"+COLUMN_LAST_NAME+" TEXT)";

        database.execSQL(createTableStatement);

        final String CREATE_QUESTION_TABLE = "CREATE TABLE " + QuestionsColumn.Table_Name + " ( " +
                QuestionsColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsColumn.QUESTION_COLUMN + " TEXT, " +
                QuestionsColumn.CHOICE1_COLUMN + " TEXT, " +
                QuestionsColumn.CHOICE2_COLUMN + " TEXT, " +
                QuestionsColumn.CHOICE3_COLUMN + " TEXT, " +
                QuestionsColumn.ANSWER_NUMBER_COLUMN + " INTEGER" +
                ")";

        database.execSQL(CREATE_QUESTION_TABLE);
        fillQuestTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        database.execSQL(SQL_DELETE_ENTRIES);
        onCreate(database);
        database.execSQL("DROP TABLE IF EXISTS " + QuestionsColumn.Table_Name);
        onCreate(database);
    }

    private void fillQuestTable(){
        Question question1 = new Question("This is question 1","A","B","C",1);
        additionalQuestion(question1);
        Question question2 = new Question("This is question 2","A","B","C",2);
        additionalQuestion(question2);
        Question question3 = new Question("This is question 3","A","B","C",3);
        additionalQuestion(question3);
        Question question4 = new Question("This is question 4","A","B","C",1);
        additionalQuestion(question4);
        Question question5 = new Question("This is question 5","A","B","C",2);
        additionalQuestion(question5);
    }

    private void additionalQuestion(Question quest){

        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsColumn.QUESTION_COLUMN, quest.getQuest());
        contentValues.put(QuestionsColumn.CHOICE1_COLUMN, quest.getChoice1());
        contentValues.put(QuestionsColumn.CHOICE2_COLUMN, quest.getChoice2());
        contentValues.put(QuestionsColumn.CHOICE3_COLUMN, quest.getChoice3());
        contentValues.put(QuestionsColumn.ANSWER_NUMBER_COLUMN, quest.getAnswerNumber());

        //Now to insert these values into the database
        database.insert(QuestionsColumn.Table_Name,null,contentValues);

    }


    //Now write a method to retrieve data from the database
    @SuppressLint("Range")
    public List<Question> getQuestions(){
        List<Question> questionList = new ArrayList<>();
        database = getReadableDatabase();
        //place cursor to query our database
        Cursor cursor = database.rawQuery("SELECT * FROM " + QuestionsColumn.Table_Name, null);
        if(cursor.moveToFirst()){
            do {
                Question quest = new Question();
                quest.setQuest(cursor.getString(cursor.getColumnIndex(QuestionsColumn.QUESTION_COLUMN)));
                quest.setChoice1(cursor.getString(cursor.getColumnIndex(QuestionsColumn.CHOICE1_COLUMN)));
                quest.setChoice2(cursor.getString(cursor.getColumnIndex(QuestionsColumn.CHOICE2_COLUMN)));
                quest.setChoice3(cursor.getString(cursor.getColumnIndex(QuestionsColumn.CHOICE3_COLUMN)));
                quest.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionsColumn.ANSWER_NUMBER_COLUMN)));
                questionList.add(quest);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }

    

    public boolean registerUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME,userModel.getFirstName());
        cv.put(COLUMN_LAST_NAME,userModel.getLastName());
        cv.put(COLUMN_EMAIL,userModel.getEmail());
        cv.put(COLUMN_PASSWORD,userModel.getPassword());

        long insert = db.insert(USER_TABLE,null,cv);

        if(insert == -1)
            return false;
        else
            return true;

    }

    public Cursor getUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+USER_TABLE+" WHERE ("+COLUMN_EMAIL+"=\""+email+"\" AND "+COLUMN_PASSWORD+"= \""+password+"\")",null);
        return cursor;
    }

    public Cursor getUserById(int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT FROM "+USER_TABLE+" WHERE ID="+userId,null);
        return cursor;
    }
}
