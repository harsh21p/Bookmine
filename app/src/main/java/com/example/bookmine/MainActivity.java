 package com.example.bookmine;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.SearchView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.firebase.ui.database.FirebaseRecyclerOptions;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.Objects;

 public class MainActivity extends AppCompatActivity {

     private SearchView mSearchField;
     private String author;
     public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";
     RecyclerView recview;
     myadaper adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recview=findViewById(R.id.recycler_view);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Books"), model.class)
                        .build();

        adapter=new myadaper(options);
        recview.setAdapter(adapter);


    }

    void search ()
    {
        mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processsearch(newText);
                return false;
            }
        });
    }
     private void processsearch(String s) {

         FirebaseRecyclerOptions<model> options =
                 new FirebaseRecyclerOptions.Builder<model>()
                         .setQuery(FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), model.class)
                         .build();
         adapter=new myadaper(options);
         adapter.startListening();
         recview.setAdapter(adapter);


     }


     @Override
     protected void onStart() {
         super.onStart();
         adapter.startListening();
     }

     @Override
     protected void onStop() {
         super.onStop();
         adapter.stopListening();
     }

     public void redirect()
     {

     }

    public void onClick(View view)
    {
        this.search();
//        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, MainActivity2.class);
//        mSearchField= findViewById(R.id.search_field);
//        String searchContent = mSearchField.getQuery().toString();
//        intent.putExtra(EXTRA_NAME,searchContent);
//        startActivity(intent);

    }

}