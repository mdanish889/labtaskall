package com.example.labtaskall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class uploadimage extends AppCompatActivity {
    private EditText ImageNameET;
    private ImageView imageToUploadIV ;

    private Button UploadBtn;
    private Dialog objectWaitDialog;
    private static final int REQUEST_CODE=123;
    private Uri objectUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);
        ImageNameET=findViewById(R.id.ImageNameET);
        imageToUploadIV=findViewById(R.id.ImageToUploadIV);

        UploadBtn=findViewById(R.id.uploadImageBtn);

        objectWaitDialog=new Dialog(this);
        objectWaitDialog.setContentView(R.layout.activity_please_wait);


        objectWaitDialog.setCancelable(false);

    }
    private void selectImageFromGallery()
    {
        try{

            Intent objectIntent=new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,REQUEST_CODE);


        }catch (Exception e)
        {
            Toast.makeText(this, "selectImageFromGallery:"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&requestCode==RESULT_OK && data!=null){
            objectUri=data.getData();
            if(objectUri!=null)
            {
                try {
                    Bitmap objectBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),objectUri);
                    imageToUploadIV.setImageBitmap(objectBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(this, "NO Image is Selected", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(this, "NO Image is Selected", Toast.LENGTH_SHORT).show();
    }

}
