package com.utkarsh.practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    String email;
    EditText forgotPasswordField;
    Button resetPasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        forgotPasswordField = findViewById(R.id.resetFiled);
        resetPasswordButton = findViewById(R.id.reset);
        firebaseAuth = FirebaseAuth.getInstance();
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMeResetLink();
            }
        });
    }



    private void getMeResetLink(){
        email = forgotPasswordField.getText().toString().trim();

        if(!email.isEmpty()){

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                //Enter Correct email
                forgotPasswordField.setError("Enter valid e-Mail!");
                forgotPasswordField.requestFocus();
                return;
            }

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {

                if(task.isSuccessful()){
                    //Toast
                    Toast.makeText(ForgotPassword.this, "Check your e-Mail!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                }else{
                    //ErrorMessage
                    Toast.makeText(ForgotPassword.this, "Process Failed, Try again!", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            forgotPasswordField.setError("Enter e-Mail!");
            forgotPasswordField.requestFocus();
        }
    }

    /*@Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }*/
}
