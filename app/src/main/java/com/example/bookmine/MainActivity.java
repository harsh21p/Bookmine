 package com.example.bookmine;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.SearchView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.Objects;

 public class MainActivity extends AppCompatActivity {

     private SearchView searchView;
     private String author;
     public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }

    public void onClick(View view)
    {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Books").child("1");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                searchView= findViewById(R.id.search_field);
                author = Objects.requireNonNull(snapshot.child("author").getValue()).toString();
                searchView.setQuery(author,false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        searchView= findViewById(R.id.search_field);
        String searchContent = searchView.getQuery().toString();
        intent.putExtra(EXTRA_NAME,searchContent);
        startActivity(intent);

    }

}