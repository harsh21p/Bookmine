package com.hny.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class login extends AppCompatActivity {
Button callsignup,login_button;
ImageView image;
TextInputLayout username, password;
TextView sloganText,logoText;

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
        login_button = findViewById(R.id.login_btn);

        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,Signup.class);
                startActivity(intent);

            }
        });
    }
}