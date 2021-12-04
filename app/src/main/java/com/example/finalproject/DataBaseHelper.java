package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + USER_TABLE;


    public DataBaseHelper(@Nullable Context context) {
        super(context, "code_success.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+USER_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_EMAIL+" TEXT,"+COLUMN_PASSWORD
                +" TEXT,"+COLUMN_FIRST_NAME+" TEXT,"+COLUMN_LAST_NAME+" TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
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
