package com.example.bookmine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class aboutus extends AppCompatActivity {
    private String z,link,click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        TextView notification1 = findViewById(R.id.textView15);



        FirebaseFirestore notification = FirebaseFirestore.getInstance();

        notification.collection("notification").orderBy("new")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                z =document.getData().get("new").toString();
                                link=document.getData().get("link").toString();
                                click=document.getData().get("click").toString();

                                notification1.setText(z+" "+click);
                                notification1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse(link));
                                        startActivity(intent);

                                    }
                                });
                            }
                        } else {
                            String z ="No new notifications";
                            TextView notification = findViewById(R.id.textView15);
                            notification.setText(z);

                        }
                    }
                });




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