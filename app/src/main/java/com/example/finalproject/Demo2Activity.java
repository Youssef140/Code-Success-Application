package com.example.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.finalproject.ImageRecognition.FetchPath;
import com.example.finalproject.ImageRecognition.ImageDetector;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Demo2Activity extends AppCompatActivity {

    Button nextBtn, processButton, uploadButton;
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static String imagePath;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        imagePath = "";
        imageView = (ImageView) findViewById(R.id.imageView);
        nextBtn = findViewById(R.id.nextBtn);
        processButton = findViewById(R.id.processButton);
        uploadButton = findViewById(R.id.uploadButton);
        verifyStoragePermissions(this);

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

    public void uploadClicked(View view) throws IOException, JSONException {
        switch(view.getId()){
            case R.id.uploadButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.processButton:
                verifyStoragePermissions(this);
                System.out.println(">>>>>ProcessButoon Clicked");
                Toast.makeText(Demo2Activity.this,"Error Processing image",Toast.LENGTH_LONG);
                if(!imagePath.isEmpty()) {
//                    Calling Image detector API
                    System.out.println(">>>>>>Image URI is not empty");
                    ImageDetector imageDetector = new ImageDetector(imagePath,"https://www.nyckel.com/v1/functions/xv2z9bq01urlzwwu/invoke","POST");
                    imageDetector.doInBackground();

                    Gson gson = new Gson();
                    if(!imageDetector.getResponse().isEmpty()) {
                        JSONObject response = new JSONObject(imageDetector.getResponse());
  //                    Going to Result page
                        String label = response.getString("labelName");
                        if(label.equalsIgnoreCase("not car")) {
                            Intent intent = new Intent(Demo2Activity.this, Demo3Activity.class);
                            intent.putExtra("confidence", response.getString("confidence"));
                            startActivity(intent);
                        }
                        else if(label.equalsIgnoreCase("car")){
                            Intent intent = new Intent(Demo2Activity.this, Demo2Activity2.class);
                            intent.putExtra("confidence", response.getString("confidence"));
                            startActivity(intent);
                        }
                    }


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


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}