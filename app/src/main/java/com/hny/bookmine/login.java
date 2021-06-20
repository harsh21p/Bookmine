package com.hny.bookmine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class login extends AppCompatActivity {
Button callsignup,login_button;
ImageView image;
TextInputLayout username, password;
TextView sloganText,logoText;
ProgressBar  progressBar;
FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Hooks
        callsignup = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        login_button = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressBar);

        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,Signup.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginme(v);
            }
        });

    }

    private void loginme(View view){

        login_button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        if(!validatePassword() | !validateUsername()){
            return;
        }
        String username1=username.getEditText().getText().toString();
        String password1 = password.getEditText().getText().toString();
        mAuth.signInWithEmailAndPassword(username1, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            login_button.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(login.this,MainActivity.class);


                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                            login_button.setVisibility(View.VISIBLE);

                        }
                    }
                });

    }
    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        if(val.isEmpty()){
            username.setError("Field cannot be empty");

            login_button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            login_button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


}