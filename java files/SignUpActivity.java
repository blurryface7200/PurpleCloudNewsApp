package com.example.purplecloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {


    //private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confPasswordEditText;
    private EditText emailEditText;
    private Button signupButton;
    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    private String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //usernameEditText = findViewById(R.id.editText_username);
        passwordEditText = findViewById(R.id.editText_password);
        confPasswordEditText = findViewById(R.id.editText_confirm_password);
        emailEditText = findViewById(R.id.editText_email);
        signupButton = findViewById(R.id.button_signup);
        progressBar= new ProgressBar(this);
        progressBar.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpAuth();
            }
        });



    }

    private void SignUpAuth() {
        //String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confPassword = confPasswordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if(!email.matches(regex)){
            emailEditText.requestFocus();
            emailEditText.setError("Enter a valid email!");
        }else if(password.length()<6||password.isEmpty()) {
            passwordEditText.requestFocus();
            passwordEditText.setError("Password length should be greater than 5");
        }else if (!password.equals(confPassword)){
            confPasswordEditText.requestFocus();
            confPasswordEditText.setError("Password does not match");
        }else {

            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });




        }



    }


}