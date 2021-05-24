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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity2 extends AppCompatActivity {
    TextView secondPageSearchContent;
    String searchContent,Filterbartext1,Filterbartext,searchContent1;
    private RecyclerView mResultList;
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

        mResultList=findViewById(R.id.recycler_view_secondpage);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        //call
        if(searchContent.contains(".")) {
            firebaseUserSearch(searchContent1, Filterbartext);
        }else {

            firebaseUserSearch(searchContent, Filterbartext);
        }

    }

    private void firebaseUserSearch(String s,String y) {

        Query firebaseSearchQueary;
        if(y.equals("All")){
            if(s.equals("All"))
            {
                firebaseSearchQueary = mUserDatabase;
            }else {
                firebaseSearchQueary = mUserDatabase.orderByChild("author").startAt(s).endAt(s);
            }

        }else {
            if(s.equals("All"))
            {
                firebaseSearchQueary = mUserDatabase.orderByChild("genre_and_votes").startAt(y).endAt(y);;
            }else
                {
                firebaseSearchQueary = mUserDatabase.orderByChild("authcat").startAt(s+y).endAt(s+y);
            }
        }

        FirebaseRecyclerAdapter<Book1, MainActivity2.UsersViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Book1, MainActivity2.UsersViewHolder>(
                Book1.class,
                R.layout.second_screen_cardview,
                MainActivity2.UsersViewHolder.class,
                firebaseSearchQueary
        ) {
            @Override
            protected void populateViewHolder(MainActivity2.UsersViewHolder usersViewHolder, Book1 book1, int i) {

                    usersViewHolder.setDetails(getApplicationContext(), book1.getTitle(), book1.getCover_link(), book1.getAuthor(), book1.getGenre_and_votes(), book1.getNumber_of_pages());


                    usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            openActivity3(v, book1.getTitle(), book1.getCover_link(), book1.getAuthor(), book1.getGenre_and_votes(), book1.getNumber_of_pages(), book1.getYear_published(), book1.getAmazon_redirect_link(), book1.getAuthor_link(), book1.getFive_star_ratings(), book1.getFour_star_ratings(), book1.getBooklinks(), book1.getOne_star_ratings(), book1.getRating_count(),book1.getThree_star_ratings(),book1.getTwo_star_ratings());

                        }
                    });
                    }
                 };

        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx,String bookname,String coverlink,String authorname, String category, String noofpages)
        {

            TextView book_name = mView.findViewById(R.id.booknametext);
            TextView author_name = mView.findViewById(R.id.authornametext);
            TextView Category_name = mView.findViewById(R.id.categorytext);
            TextView page_no = mView.findViewById(R.id.noofpagestext);
            ImageView image = mView.findViewById(R.id.bookcoverimg);

            book_name.setText(bookname);
            author_name.setText(authorname);
            Category_name.setText(category);
            page_no.setText(noofpages);
            Glide.with(ctx).load(coverlink).into(image);

        }
    }

    public void openActivity3(View view,String bookname,String coverlink,String authorname, String category, String noofpages,String year,String amazon_redirect_url,String author_link,String five_star_rating,String four_star_rating,String booklinks,String onr_star_rating,String rating_count,String three_star_rating,String toe_star_rating)
    {
        Toast.makeText(MainActivity2.this,"Opening "+bookname,Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, BOOKMINE3.class);
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
        startActivity(intent1);
    }


}