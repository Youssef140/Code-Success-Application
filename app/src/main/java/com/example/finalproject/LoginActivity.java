package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
    }

    public void loginClicked(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(LoginActivity.this);
        Cursor cursor = dbHelper.getUser(emailText.getText().toString(),passwordText.getText().toString());

        if(cursor == null){
            Toast.makeText(LoginActivity.this,"Wrong email or password",Toast.LENGTH_LONG).show();
        }
        else {

//            System.out.println(">>>>>>>"+cursor.getColumnNames().toString());
//            System.out.println(">>>>>>>>>>>>>> "+cursor.getColumnIndex("ID"));

            cursor.moveToNext();
//            Cursor foundUser = dbHelper.getUserById(cursor.getInt(0));

//            foundUser.moveToNext();
//            UserModel user = new UserModel(foundUser.getInt(0),foundUser.getString(1),foundUser.getString(2),foundUser.getString(3),foundUser.getString(4));
//            System.out.println(">>>>>>>>foundUser: "+foundUser);
            Toast.makeText(LoginActivity.this,"Welcome "+cursor.getString(1),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, Demo1Activity.class);
            startActivity(intent);
        }
    }


    public void testClicked(View view){
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}