package com.hny.bookmine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Signup extends AppCompatActivity {
    //Variables
Button regBtn, already;
TextInputLayout regName, regUsername,  regEmail, regPassword, regNumber;
FirebaseDatabase rootNode;
DatabaseReference reference;
ProgressBar progressBar1;
FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Hooks

        already = findViewById(R.id.already);
        regBtn = findViewById(R.id.regBtn);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regName = findViewById(R.id.name);
        regPassword = findViewById(R.id.password);
        regNumber = findViewById(R.id.number);
        progressBar1 = findViewById(R.id.progressBar1);


        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,login.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
            if(val.isEmpty()){
                regName.setError("Field cannot be empty");
                return false;
            }else{
                regName.setError(null);
                regName.setErrorEnabled(false);
                return true;
            }

        }

    private Boolean validateUsername(){
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        String val = regUsername.getEditText().getText().toString();
            if(val.isEmpty()){
                regUsername.setError("Field cannot be empty");
                return false;
            }
            else if(val.length()>=15){
                regUsername.setError("Username too long");
                return false;
            }else if(!val.matches(noWhiteSpace)){
                regUsername.setError("White Spaces are not allowed");
                return false;
            }
            else{
                regUsername.setError(null);
                regUsername.setErrorEnabled(false);
                return true;
            }

    }

    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if(val.isEmpty()){
                regEmail.setError("Field cannot be empty");
                return false;
            }else if(!val.matches(emailPattern)){
                regEmail.setError("Invalid email address");
                return false;
            }
            else{
                regEmail.setError(null);
                regEmail.setErrorEnabled(false);
                return true;

        }

    }

    private Boolean validatePhoneNo(){
        String val = regNumber.getEditText().getText().toString();
        if(val.isEmpty()){
            regNumber.setError("Field cannot be empty");
            return false;
        }else{
            regNumber.setError(null);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    //Save data in firebase on button click
    public void regUser(View view){
        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }
        regBtn.setVisibility(View.INVISIBLE);
        progressBar1.setVisibility(View.VISIBLE);
        // Get all the values
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phnnumber = regNumber.getEditText().getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("users");
                            UserHelperClass helperClass = new UserHelperClass(username, name, password, email, phnnumber);
                            reference.child(phnnumber).setValue(helperClass);
                            Toast.makeText(Signup.this,"Registeration Successful",Toast.LENGTH_SHORT).show();
                            regBtn.setVisibility(View.VISIBLE);
                            progressBar1.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Signup.this,login.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            regBtn.setVisibility(View.VISIBLE);
                            progressBar1.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}