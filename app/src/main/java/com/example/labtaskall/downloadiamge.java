package com.example.labtaskall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

public class downloadiamge extends AppCompatActivity {


    private EditText imageNameET;
    private ImageView downloadedIV;

    private Button downloadBtn;
    private FirebaseFirestore objectFirebaseFirestore;

    private Dialog objectWaitDialog;
    private LocalDate Glide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadiamge);


        imageNameET=findViewById(R.id.imageNameET);
        downloadedIV=findViewById(R.id.downladedImageIV);
        downloadBtn=findViewById(R.id.downloadImageBtn);

        objectWaitDialog=new Dialog(this);

        objectWaitDialog.setContentView(R.layout.activity_please_wait);
        objectWaitDialog.setCancelable(false);

        objectFirebaseFirestore=FirebaseFirestore.getInstance();
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void downloadImage()
    {
        if(!imageNameET.getText().toString().isEmpty())
        {
            objectWaitDialog.show();
            objectFirebaseFirestore.collection("Links").document(imageNameET.getText().toString())
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists())
                    {
                        objectWaitDialog.dismiss();
                        String url=documentSnapshot.getString("url");

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    objectWaitDialog.dismiss();
                    Toast.makeText(downloadiamge.this, "Fail to download data:"+
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            objectWaitDialog.dismiss();
            Toast.makeText(this, "Please enter the iamge name to download", Toast.LENGTH_SHORT).show();
        }
    }

}
