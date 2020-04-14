package com.example.labtaskall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sigin extends AppCompatActivity {
    private EditText emailET,passwordET;
    private Button signInUserBtn;

    private ProgressBar objectProgressBar;
    private FirebaseAuth objectFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        connectXMLToJava();
        objectFirebaseAuth= FirebaseAuth.getInstance();

    }
    private void connectXMLToJava()
    {
        try
        {
            emailET=findViewById(R.id.userEmailET);
            passwordET=findViewById(R.id.userPasswordET);

            signInUserBtn=findViewById(R.id.signInUser);
            objectProgressBar=findViewById(R.id.signInUserProgressBar);

            signInUserBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signInUser();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "connectXMLToJava:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
     

}
