package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mycode
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);

       // button.setOnClickListener(new View.OnClickListener() {
          //  @Override
        //    public void onClick(View v) {
         //       Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
               // String s = editText.getText().toString();
               // int a = Integer.parseInt(s);


          //  }
       // });



    }
    public void openActivity2(View view)
    {
        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}