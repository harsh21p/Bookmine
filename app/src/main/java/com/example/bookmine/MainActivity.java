 package com.example.bookmine;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.SearchView;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recview=findViewById(R.id.recycler_view);
        recview.setLayoutManager(new LinearLayoutManager(this));

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