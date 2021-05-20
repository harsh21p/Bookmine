 package com.example.bookmine;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.SearchView;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.firebase.ui.database.FirebaseRecyclerAdapter;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.Query;

 import java.util.Objects;

 public class MainActivity extends AppCompatActivity {

     public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";
     private SearchView mSearchField;
     RecyclerView mResultList;
     DatabaseReference mUserDatabase;
     EditText mFilterBar;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mFilterBar=findViewById(R.id.editTextTextPersonName2);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");

        mSearchField=findViewById(R.id.search_field);

        mResultList=findViewById(R.id.recycler_view);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        search();


    }


void search(){
    mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            firebaseUserSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            firebaseUserSearch(newText);

            return false;
        }
    });

}
     private void firebaseUserSearch(String searchText) {

         //Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_LONG).show();

         Query firebaseSearchQueary = mUserDatabase.orderByChild("author").startAt(searchText).endAt(searchText+"\uf8ff");

         FirebaseRecyclerAdapter<Books,UsersViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Books, UsersViewHolder>(
                 Books.class,
                 R.layout.singlerow,
                 UsersViewHolder.class,
                 firebaseSearchQueary
         ) {
             @Override
             protected void populateViewHolder(UsersViewHolder usersViewHolder, Books books, int i) {
                 usersViewHolder.setDetails(books.getAuthor());
                 usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         mSearchField.setQuery(books.getAuthor(),false);

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

        public void setDetails(String author)
        {
            TextView user_name = mView.findViewById(R.id.nametext);
            user_name.setText(author);
        }
    }

     public void onClick(View view)
     {

        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        mSearchField= findViewById(R.id.search_field);
        String searchContent = mSearchField.getQuery().toString();
        intent.putExtra(EXTRA_NAME,searchContent);
        startActivity(intent);

     }

}