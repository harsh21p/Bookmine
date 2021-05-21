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
    String searchContent,Filterbartext;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    public static final String EXTRA_TEXT1 = "Extra.search.for.third.page.title";
    public static final String EXTRA_TEXT2 = "Extra.search.for.third.page.author";
    public static final String EXTRA_TEXT3 = "Extra.search.for.third.page.category";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        //my code
        Intent intent = getIntent();
        searchContent = intent.getStringExtra(MainActivity.EXTRA_NAME);
        Filterbartext = intent.getStringExtra(MainActivity.EXTRA_NAME_F);
        //Code of recyclerview
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");

        mResultList=findViewById(R.id.recycler_view_secondpage);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        //call
        firebaseUserSearch(searchContent,Filterbartext);

    }

    private void firebaseUserSearch(String s,String y) {

        Query firebaseSearchQueary;
        if(y.equals("All"))
        {
            firebaseSearchQueary = mUserDatabase.orderByChild("author").startAt(s).endAt(s);
        }
        else
            {
                    firebaseSearchQueary = mUserDatabase.orderByChild("author").startAt(s).endAt(s); //.orderByChild("genre_and_votes").startAt(y).endAt(y)
            }

        FirebaseRecyclerAdapter<Book1, MainActivity2.UsersViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Book1, MainActivity2.UsersViewHolder>(
                Book1.class,
                R.layout.second_screen_cardview,
                MainActivity2.UsersViewHolder.class,
                firebaseSearchQueary
        ) {
            @Override
            protected void populateViewHolder(MainActivity2.UsersViewHolder usersViewHolder, Book1 book1, int i) {
                usersViewHolder.setDetails(getApplicationContext(),book1.getTitle(),book1.getAuthor(),book1.getGenre_and_votes(),book1.getNumber_of_pages(),book1.getCover_link());

                usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openActivity3(v,book1.getTitle(),book1.getAuthor(),book1.getGenre_and_votes());

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

        public void setDetails(Context ctx, String bookname, String authorname, String category, String noofpages, String coverlink)
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




    public void openActivity3(View view,String title,String author,String category)
    {
        Toast.makeText(MainActivity2.this,"Opening "+title,Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, BOOKMINE3.class);
        intent1.putExtra(EXTRA_TEXT1,title);
        intent1.putExtra(EXTRA_TEXT2,author);
        intent1.putExtra(EXTRA_TEXT3,category);
        startActivity(intent1);
    }


}