package com.example.camtopdf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

// pdf4me library
import org.apache.commons.io.FileUtils;

import com.pdf4me.client.ConvertClient;
import com.pdf4me.client.Pdf4meClient;
import com.pdf4me.client.MergeClient;

import model.ConvertToPdf;
import model.ConvertToPdfAction;
import model.ConvertToPdfRes;
import model.Document;


public class ms extends AppCompatActivity {
    private Button cameraBtn;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ms);

        Button cameraBtn = (Button) findViewById(R.id.cameraBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("camera", "click camera");
                askCameraPermission();


            }
        });

    }
    public void openactivity_next(){
        Intent intent=new Intent(this, next.class);
        intent.putExtra("imagepath",currentPhotoPath);
        startActivity(intent);
    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
//            dispatchTakePictureIntent();
            openactivity_next();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //open camera
//                dispatchTakePictureIntent();
                openactivity_next();
            } else {
                Toast.makeText(this, "camera permission required to use camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private void openCamera() {
//        Toast.makeText(this, "camera open request", Toast.LENGTH_SHORT).show();
//        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(camera , CAMERA_REQUEST_CODE);
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d("cam_result", " camera save ");
                Log.d("cam_photo_path", " Path: " + currentPhotoPath);
                openactivity_next();

//              Pdf4meClient pdf4meClient = new Pdf4meClient();
//              pdf4meClient = new Pdf4meClient("//");
//                Pdf4meClient pdf4meClient = new Pdf4meClient("https://api-dev.pdf4me.com", "NTFmMWRiMWYtNzIwOC00Nzg2LTk2Y2YtNmY1N2IzYTE1NjI4OldIeUtHRERITnBDTmozSTA9cjB5UmxZOD1RWG5oZVZW");

//                ConvertClient convertClient = new ConvertClient(pdf4meClient)
//               ConvertToPdf convertToPdf = new ConvertToPdf();
//
//                Document document = new Document();
//                try {
//                    document.setDocData(Files.readAllBytes(Paths.get(""+currentPhotoPath)));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                document.setName(currentPhotoPath);
//                convertToPdf.setDocument(document);
//
//                convertToPdf.setConvertToPdfAction(new ConvertToPdfAction());
//
//                ConvertToPdfRes res = convertClient.convertToPdf(convertToPdf);
//
            }


        }
    }


    public File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "temp";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
//            ...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

}


