package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView secondPageSearchContent;
    String searchContent;
    public static final String EXTRA_TEXT2 = "Extra.search.for.third.page";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //my code
        secondPageSearchContent =findViewById(R.id.searchText);
        Intent intent = getIntent();
        searchContent = intent.getStringExtra(MainActivity.EXTRA_NAME);
        secondPageSearchContent.setText( "You Searching For : " + searchContent);

    }
    public void openActivity3(View view)
    {
        Toast.makeText(MainActivity2.this,"Opening Third Page...",Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, BOOKMINE3.class);
        intent1.putExtra(EXTRA_TEXT2,searchContent);
        startActivity(intent1);
    }


}