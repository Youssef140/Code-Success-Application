package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Scanner;

public class RegisterActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText fullNameText;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        fullNameText = findViewById(R.id.fullNameText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        registerBtn = findViewById(R.id.registerBtn);

    }

    public void onRegisterClicked(View view) {

        String fullName = fullNameText.getText().toString();
        System.out.println(">>>>>>fullName: "+fullName);
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        UserModel user;

//        if(fullName==null){
//            Toast.makeText(this,"Name Required",Toast.LENGTH_SHORT).show();
//        }
//        else if(email==null){
//            Toast.makeText(this,"Email Required",Toast.LENGTH_SHORT).show();
//        }
//        else if(password==null){
//            Toast.makeText(this,"Passwordq Required",Toast.LENGTH_SHORT).show();
        if(email.isEmpty() || password.isEmpty() || password.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Missing Information");
            builder.setMessage("Please fill all required information.");
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }
        else{
            String[] name = fullName.split("\\s");
            String firstName = "";
            String lastName = "";
            if(name.length==1){
                firstName = name[0];
            }
            else {
                firstName = name[0];
                for(int i =1;i<name.length;i++){
                    lastName += name[i]+" ";
                }
            }
            user = new UserModel(-1,firstName,lastName,email,password);

            DataBaseHelper dbHelper = new DataBaseHelper(RegisterActivity.this);
            boolean success = dbHelper.registerUser(user);

            if(success){
                Toast.makeText(this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
            else
                Toast.makeText(this,"Unable to Register User",Toast.LENGTH_SHORT).show();

        }


    }

    public void onRegisterLoginClick(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}