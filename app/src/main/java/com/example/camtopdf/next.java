package com.example.camtopdf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

public class next extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ImageView image = (ImageView) findViewById(R.id.imageView);
                Intent intent = getIntent();
//        String imagepath = intent.getStringExtra("imagepath");
//        Log.d("imagepath", "onCreate: "+imagepath);
//        Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
//        image.setImageBitmap(bitmap);
//        Uri newpath = Uri.parse(imagepath);
        startcrop();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                ImageView image = (ImageView) findViewById(R.id.imageView);
                image.setImageURI(result.getUri());
                Toast.makeText(this, "Image Update Successfully !!!"
                        ,Toast.LENGTH_SHORT).show();
            }
            Log.d("check", "request if");
        }
    }

    private void startcrop() {
        CropImage.activity()
                .start(this);
        Log.d("check", "inside function");


    }
}