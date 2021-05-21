package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BOOKMINE3 extends AppCompatActivity {
    TextView secondPageSearchContent3;
    String title,author,category;
    public static final String EXTRA_TEXT_LAST = "com.example.bookmine.extra.last";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_o_k_m_i_n_e3);
        getSupportActionBar().hide();

        //My code


        Intent intent1 = getIntent();
        title = intent1.getStringExtra(MainActivity2.EXTRA_TEXT1);
        author = intent1.getStringExtra(MainActivity2.EXTRA_TEXT2);
        category = intent1.getStringExtra(MainActivity2.EXTRA_TEXT3);

    }

}