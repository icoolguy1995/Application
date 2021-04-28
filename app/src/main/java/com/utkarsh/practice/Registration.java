package com.utkarsh.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private TextView registeruser;
    private EditText editTextFullName , editTextAge , editTextEmailAddress , editTextPassword;



    private FirebaseAuth mAuth;  // current auth state from FireBase:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        mAuth = FirebaseAuth.getInstance();  //  FireBase Auth instance in onCreate() method:

        registeruser = (TextView) findViewById(R.id.registerUser);
        registeruser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.Age);
        editTextEmailAddress = (EditText) findViewById(R.id.eMail);
        editTextPassword = (EditText) findViewById(R.id.passWord);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerUser:
                   userRegister();
                   break;
                   
        }

    }

    private void userRegister() {

        String Email = editTextEmailAddress.getText().toString().trim();
        String paSSword = editTextPassword.getText().toString().trim();
        String fullname = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if (fullname.isEmpty())           //Showing errors on idle input:
           {
               editTextFullName.setError("FullName is required");
               editTextFullName.requestFocus();
               return;
           }
        if (age.isEmpty())               //Showing errors on idle input:
           {
               editTextAge.setError("Age is required");
               editTextAge.requestFocus();
               return;
           }
        if (paSSword.isEmpty())         //Showing errors on idle input:
           {
               editTextPassword.setError("Password is required");
               editTextPassword.requestFocus();
               return;
           }
        if (paSSword.length() < 6)      // Validation For Password Length:
           {
               editTextPassword.setError("Password is too short, above 6");
           }
        if (Email.isEmpty())           //Showing errors on idle input:
          {
              editTextEmailAddress.setError("e-Mail is required");
              editTextEmailAddress.requestFocus();
              return;
          }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())       // Verifying The E-Mail:
          {
              editTextEmailAddress.setError("Provide Valid E-mail");
              editTextEmailAddress.requestFocus();
              return;
          }

        mAuth.createUserWithEmailAndPassword(Email,paSSword)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful())
                      {
                        User user = new User(fullname , age ,Email);

                          FirebaseDatabase.getInstance().getReference("Users")  // Firebase DataBase Object:
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(task1 -> {

                                runOnUiThread(
                                        ()->{
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(Registration.this, "User Has Been Register!", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(Registration.this, "Process Failed!", Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                );



                            });

                      }
                      else{

                        Toast.makeText(Registration.this, "Process Failed!", Toast.LENGTH_SHORT).show();
                    }

                });
    }
}