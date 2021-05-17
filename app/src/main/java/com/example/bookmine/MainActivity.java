 package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    //FireBase
   // private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        //mycode

      //  button = findViewById(R.id.button);
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

        editText = findViewById(R.id.editTextTextPersonName);
        String searchContent = editText.getText().toString();
        intent.putExtra(EXTRA_NAME,searchContent);
        startActivity(intent);


    }


}