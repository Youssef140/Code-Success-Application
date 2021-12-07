package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.finalproject.ImageRecognition.FetchPath;
import com.example.finalproject.ImageRecognition.ImageDetector;

import java.io.IOException;

public class Demo2Activity extends AppCompatActivity {

    Button nextBtn, processButton, uploadButton;
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        imagePath = "";
        imageView = (ImageView) findViewById(R.id.imageView);
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

    public void uploadClicked(View view) throws IOException {
        switch(view.getId()){
            case R.id.uploadButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.processButton:
                System.out.println(">>>>>ProcessButoon Clicked");
                if(!imagePath.isEmpty()) {
                    System.out.println(">>>>>>Image URI is not empty");
                    ImageDetector imageDetector = new ImageDetector();
                    imageDetector.send(imagePath,"https://www.nyckel.com/v1/functions/xv2z9bq01urlzwwu/invoke","POST");
                }
            default:
                break;
        }
    }

    public void processClicked(View view){

    }

    public void imageClicked(View view){
//        switch(view.getId()){
//            case R.id.imageView:
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
//                break;
//
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            imagePath = FetchPath.getPath(this,selectedImage);
            System.out.println(">>>>>>>ImagePath: "+imagePath);
        }
    }


}