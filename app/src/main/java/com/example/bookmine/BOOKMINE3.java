package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BOOKMINE3 extends AppCompatActivity {
    TextView secondPageSearchContent3;
    String title,coverlink,author,category,noofpages,year,amazonredirecturl,authorlink,fivestarrating,fourstarrating,booklinks,onestarrating,ratingcount,reviewrating,worldcatredirectlink;
    public static final String EXTRA_TEXT_LAST = "com.example.bookmine.extra.last";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_o_k_m_i_n_e3);
        getSupportActionBar().hide();

        //My code


        Intent intent1 = getIntent();
        title = intent1.getStringExtra(MainActivity2.EXTRA_TEXT1);
        coverlink = intent1.getStringExtra(MainActivity2.EXTRA_TEXT2);
        author = intent1.getStringExtra(MainActivity2.EXTRA_TEXT3);
        category = intent1.getStringExtra(MainActivity2.EXTRA_TEXT4);
        noofpages = intent1.getStringExtra(MainActivity2.EXTRA_TEXT5);
        year = intent1.getStringExtra(MainActivity2.EXTRA_TEXT6);
        amazonredirecturl = intent1.getStringExtra(MainActivity2.EXTRA_TEXT7);
        authorlink= intent1.getStringExtra(MainActivity2.EXTRA_TEXT8);
        fivestarrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT9);
        fourstarrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT10);
        booklinks = intent1.getStringExtra(MainActivity2.EXTRA_TEXT11);
        onestarrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT12);
        ratingcount = intent1.getStringExtra(MainActivity2.EXTRA_TEXT13);
        reviewrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT14);
        worldcatredirectlink = intent1.getStringExtra(MainActivity2.EXTRA_TEXT15);



    }

}