package com.example.bookmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BOOKMINE3 extends AppCompatActivity {
    String identifer;
    TextView secondPageSearchContent3;
    String twostarrating,threestarrating,title,coverlink,author,category,noofpages,year,amazonredirecturl,authorlink,fivestarrating,fourstarrating,booklinks,onestarrating,ratingcount;
    public static final String EXTRA_TEXT_LAST = "com.example.bookmine.extra.last";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_o_k_m_i_n_e3);
        getSupportActionBar().hide();

        identifer="No";
        //My code
        Intent intent1 = getIntent();
         identifer = intent1.getStringExtra(MainActivity2.EXTRA_TEXT18);
         identifer = intent1.getStringExtra(MainActivity.EXTRA_TEXT18);

        if(identifer.equals("MAIN2")){
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
       // reviewrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT14);
       // worldcatredirectlink = intent1.getStringExtra(MainActivity2.EXTRA_TEXT15);
        twostarrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT17);
        threestarrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT16);
        }else {
            if(identifer.equals("MAIN1")){
                title = intent1.getStringExtra(MainActivity.EXTRA_TEXT1);
                coverlink = intent1.getStringExtra(MainActivity.EXTRA_TEXT2);
                author = intent1.getStringExtra(MainActivity.EXTRA_TEXT3);
                category = intent1.getStringExtra(MainActivity.EXTRA_TEXT4);
                noofpages = intent1.getStringExtra(MainActivity.EXTRA_TEXT5);
                year = intent1.getStringExtra(MainActivity.EXTRA_TEXT6);
                amazonredirecturl = intent1.getStringExtra(MainActivity.EXTRA_TEXT7);
                authorlink= intent1.getStringExtra(MainActivity.EXTRA_TEXT8);
                fivestarrating = intent1.getStringExtra(MainActivity.EXTRA_TEXT9);
                fourstarrating = intent1.getStringExtra(MainActivity.EXTRA_TEXT10);
                booklinks = intent1.getStringExtra(MainActivity.EXTRA_TEXT11);
                onestarrating = intent1.getStringExtra(MainActivity.EXTRA_TEXT12);
                ratingcount = intent1.getStringExtra(MainActivity.EXTRA_TEXT13);
                // reviewrating = intent1.getStringExtra(MainActivity2.EXTRA_TEXT14);
                // worldcatredirectlink = intent1.getStringExtra(MainActivity2.EXTRA_TEXT15);
                twostarrating = intent1.getStringExtra(MainActivity.EXTRA_TEXT17);
                threestarrating = intent1.getStringExtra(MainActivity.EXTRA_TEXT16);
            }
        }

        setDetailspage3(title,coverlink,author,category,noofpages,year,amazonredirecturl,authorlink,fivestarrating,fourstarrating,booklinks,onestarrating,ratingcount,twostarrating,threestarrating);


    }

    public void setDetailspage3( String bookname, String coverlink, String authorname, String category, String noofpages, String year, String amazon_redirect_url, String author_link, String five_star_rating, String four_star_rating, String booklinks, String onr_star_rating, String rating_count,String twostarrating,String threestarrating)
    {

        TextView book_name = findViewById(R.id.booknametext1);
        TextView author_name = findViewById(R.id.authornametext1);
        TextView Category_name = findViewById(R.id.categorytext1);
        TextView year_published = findViewById(R.id.yeartext1);
        ImageView image = findViewById(R.id.imageView);
        ImageView amazon = findViewById(R.id.amazonlink);
        ImageView goodread =findViewById(R.id.goodreadlink);
        ImageView authorlink =findViewById(R.id.authorlink);
        TextView noofpages1 = findViewById(R.id.noofpagestext1);
        TextView ratingones1 = findViewById(R.id.rating1);
        TextView ratingones2 = findViewById(R.id.rating2);
        TextView ratingones3 = findViewById(R.id.rating3);
        TextView ratingones4 = findViewById(R.id.rating4);
        TextView ratingones5 = findViewById(R.id.rating5);
        TextView overallrating = findViewById(R.id.overallrating);

        double n5=5*Integer.parseInt(five_star_rating);
        double n4=4*Integer.parseInt(four_star_rating);
        double n3=3*Integer.parseInt(threestarrating);
        double n2=2*Integer.parseInt(twostarrating);
        double n1= Integer.parseInt(onr_star_rating);
        double add=n1+n2+n3+n4+n5;
        double nooftimesrated=Integer.parseInt(rating_count);
        double rate=add/nooftimesrated;
        DecimalFormat df2=new DecimalFormat();
        df2.setMinimumFractionDigits(1);
        df2.setMaximumFractionDigits(1);


        String ratingover=String.valueOf(df2.format(rate));
        book_name.setText(bookname);
        author_name.setText(authorname);
        Category_name.setText(category);
        year_published.setText(year);
        noofpages1.setText(noofpages);
        ratingones1.setText(onr_star_rating);
        ratingones2.setText(twostarrating);
        ratingones3.setText(threestarrating);
        overallrating.setText(ratingover);
        ratingones4.setText(four_star_rating);
        ratingones5.setText(five_star_rating);

        Glide.with(getApplicationContext()).load(coverlink).into(image);

        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(amazon_redirect_url));
                startActivity(intent);
            }
        });
        goodread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(booklinks));
                startActivity(intent);
            }
        });
        authorlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(author_link));
                startActivity(intent);
            }
        });
        identifer="NO";

    }

}