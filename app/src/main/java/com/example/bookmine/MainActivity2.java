package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void openActivity3(View view)
    {
        Toast.makeText(MainActivity2.this,"Opening Third Page...",Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, BOOKMINE3.class);
        startActivity(intent1);
    }


}