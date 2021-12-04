package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Demo2Activity extends AppCompatActivity {

    Button nextBtn, processButton, uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        nextBtn = findViewById(R.id.nextBtn);
        processButton = findViewById(R.id.processButton);
        uploadButton = findViewById(R.id.uploadButton);

    }
/*
    private void setupCamera()
    {
        snapButton = (View) findViewById(R.id.cameraActivity_CameraButton);
        snapButton.setOnClickListener((View.OnClickListener) this);
        snapButton.setOnClickListener(this);
        galleryButton = (ImageButton) findViewById(R.id.cameraActivity_GalleryButton);
        galleryButton.setOnClickListener(this);
        myprogressBar = (ProgressBar) findViewById(R.id.myPB);
        myprogressBar.setVisibility(View.INVISIBLE);

        myImageView = (ImageView) findViewById(R.id.cameraActivity_ImageView);


    }

    private void grabImageFromGallery()
    {
        Intent imageGetter = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(imageGetter, RESULT_LOAD_IMAGE);
    }


 */

    public void nextClicked(View view){

    }

    public void uploadClicked(View view){

    }

    public void processClicked(View view){

    }


}