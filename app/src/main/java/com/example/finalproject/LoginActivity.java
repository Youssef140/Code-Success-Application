package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

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
        emailText.setText("");
        passwordText.setText("");
    }

    public void loginClicked(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(LoginActivity.this);
        Cursor cursor = dbHelper.getUser(emailText.getText().toString(),passwordText.getText().toString());
        System.out.println(">>>>>>Cursor: "+cursor);
        if(cursor.isAfterLast()){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Wrong Username or Password please try again");

            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();        }
        else {

//            System.out.println(">>>>>>>"+cursor.getColumnNames().toString());
//            System.out.println(">>>>>>>>>>>>>> "+cursor.getColumnIndex("ID"));

            cursor.moveToNext();
//            Cursor foundUser = dbHelper.getUserById(cursor.getInt(0));

//            foundUser.moveToNext();
//            UserModel user = new UserModel(foundUser.getInt(0),foundUser.getString(1),foundUser.getString(2),foundUser.getString(3),foundUser.getString(4));
//            System.out.println(">>>>>>>>foundUser: "+foundUser);
            Toast.makeText(LoginActivity.this,"Welcome "+cursor.getString(1),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    public void testClicked(View view){
        Intent intent = new Intent(LoginActivity.this,  MainActivity.class);
        startActivity(intent);
    }

}