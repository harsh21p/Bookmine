package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        String yashlinkdinlink="https://www.linkedin.com/in/yashmahaja/";
        String yashtwitter = "https://twitter.com/yashmahajan_";
        String harshadlinkdin="https://www.linkedin.com/in/harshad-pachore-4437271a1/";
        String harshadtwitter="https://twitter.com/Harsh21_p";

        ImageView linkdinlink =findViewById(R.id.imageView14);
        ImageView linkdinlink1 =findViewById(R.id.imageView20);

        ImageView linkdinlinkt =findViewById(R.id.imageView18);
        ImageView linkdinlinkt1 =findViewById(R.id.imageView19);


        linkdinlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(yashlinkdinlink));
                startActivity(intent);
            }
        });

        linkdinlink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(harshadlinkdin));
                startActivity(intent);
            }
        });

        linkdinlinkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(yashtwitter));
                startActivity(intent);
            }
        });

        linkdinlinkt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(harshadtwitter));
                startActivity(intent);
            }
        });
    }
}