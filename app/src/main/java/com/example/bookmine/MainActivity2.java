package com.example.bookmine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity2 extends AppCompatActivity {
    TextView secondPageSearchContent;
    String searchContent,Filterbartext1,Filterbartext,searchContent1;
    private RecyclerView mResultList1;
    private DatabaseReference mUserDatabase;
    public static final String EXTRA_TEXT1 = "Extra.search.for.third.page.title";
    public static final String EXTRA_TEXT2 = "Extra.search.for.third.page.author";
    public static final String EXTRA_TEXT3 = "Extra.search.for.third.page.category";
    public static final String EXTRA_TEXT4 = "Extra.search.for.third.page.category1";
    public static final String EXTRA_TEXT5 = "Extra.search.for.third.page.category2";
    public static final String EXTRA_TEXT6 = "Extra.search.for.third.page.category3";
    public static final String EXTRA_TEXT7 = "Extra.search.for.third.page.category4";
    public static final String EXTRA_TEXT8 = "Extra.search.for.third.page.category5";
    public static final String EXTRA_TEXT9 = "Extra.search.for.third.page.category6";
    public static final String EXTRA_TEXT10 = "Extra.search.for.third.page.category7";
    public static final String EXTRA_TEXT11= "Extra.search.for.third.page.category8";
    public static final String EXTRA_TEXT12= "Extra.search.for.third.page.category9";
    public static final String EXTRA_TEXT13= "Extra.search.for.third.page.category10";
    //public static final String EXTRA_TEXT14= "Extra.search.for.third.page.category11";
    //public static final String EXTRA_TEXT15= "Extra.search.for.third.page.category12";
    public static final String EXTRA_TEXT16= "Extra.search.for.third.page.category13";
    public static final String EXTRA_TEXT17= "Extra.search.for.third.page.category14";

    myadapter1 adapter1;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        //my code
        Intent intent = getIntent();
        searchContent1 = intent.getStringExtra(MainActivity.EXTRA_NAME);
        Filterbartext1 = intent.getStringExtra(MainActivity.EXTRA_NAME_F);

        //Code of recyclerview
        String[] words1 = searchContent1.toString().split(" ");
        StringBuilder sb1 = new StringBuilder();
        if (words1[0].length() > 0) {
            sb1.append(Character.toUpperCase(words1[0].charAt(0)) + words1[0].subSequence(1, words1[0].length()).toString().toLowerCase());
            for (int i = 1; i < words1.length; i++) {
                sb1.append(" ");
                sb1.append(Character.toUpperCase(words1[i].charAt(0)) + words1[i].subSequence(1, words1[i].length()).toString().toLowerCase());
            }
        }
        searchContent= sb1.toString();

        String[] words = Filterbartext1.toString().split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        Filterbartext = sb.toString();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().getRoot();

        mResultList1=findViewById(R.id.recycler_view_secondpage);
        mResultList1.setHasFixedSize(true);
        mResultList1.setLayoutManager(new LinearLayoutManager(this));
        //call
        if(searchContent.contains(".")) {
            firebaseUserSearch(searchContent1, Filterbartext);
        }else {

            firebaseUserSearch(searchContent, Filterbartext);
        }

    }

    private void firebaseUserSearch(String s,String y) {

        Query firebaseSearchQueary;
        if (y.equals("All")) {
            if (s.equals("All")) {
                firebaseSearchQueary = mUserDatabase;
            } else {
                firebaseSearchQueary = mUserDatabase.orderByChild("author").startAt(s).endAt(s);
            }

        } else {
            if (s.equals("All")) {
                firebaseSearchQueary = mUserDatabase.orderByChild("genre_and_votes").startAt(y).endAt(y);
                ;
            } else {
                firebaseSearchQueary = mUserDatabase.orderByChild("authcat").startAt(s + y).endAt(s + y);
            }
        }


        //code here


        FirebaseRecyclerOptions<Book1> options1 =new FirebaseRecyclerOptions.Builder<Book1>()
                .setQuery(firebaseSearchQueary,Book1.class)
                .build();
        adapter1=new myadapter1(options1);
        adapter1.startListening();
        mResultList1.setAdapter(adapter1);

    }


    public static void openActivity3(View view, Context con, String bookname, String coverlink, String authorname, String category, String noofpages, String year, String amazon_redirect_url, String author_link, String five_star_rating, String four_star_rating, String booklinks, String onr_star_rating, String rating_count, String three_star_rating, String toe_star_rating)
    {
        Toast.makeText(con,"Opening "+bookname,Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(con, BOOKMINE3.class);
        intent1.putExtra(EXTRA_TEXT1,bookname);
        intent1.putExtra(EXTRA_TEXT2,coverlink);
        intent1.putExtra(EXTRA_TEXT3,authorname);
        intent1.putExtra(EXTRA_TEXT4,category);
        intent1.putExtra(EXTRA_TEXT5,noofpages);
        intent1.putExtra(EXTRA_TEXT6,year);
        intent1.putExtra(EXTRA_TEXT7,amazon_redirect_url);
        intent1.putExtra(EXTRA_TEXT8,author_link);
        intent1.putExtra(EXTRA_TEXT9,five_star_rating);
        intent1.putExtra(EXTRA_TEXT10,four_star_rating);
        intent1.putExtra(EXTRA_TEXT11,booklinks);
        intent1.putExtra(EXTRA_TEXT12,onr_star_rating);
        intent1.putExtra(EXTRA_TEXT13,rating_count);
        // intent1.putExtra(EXTRA_TEXT14,review_count);
        // intent1.putExtra(EXTRA_TEXT15,worldcat_redirect_link);
        intent1.putExtra(EXTRA_TEXT16,three_star_rating);
        intent1.putExtra(EXTRA_TEXT17,toe_star_rating);
        con.startActivity(intent1);
    }

}

