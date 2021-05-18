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

 public class MainActivity extends AppCompatActivity {

     private SearchView editText;
     private String z;
     public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

    }

    public void openActivity2(View view)
    {


        DatabaseReference reff= FirebaseDatabase.getInstance().getReference().child("Books").child("1");
        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                editText= findViewById(R.id.editTextTextPersonName);
                 z = snapshot.child("author").getValue().toString();
                 editText.setQuery(z,false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        editText= findViewById(R.id.editTextTextPersonName);
        String searchContent = editText.getQuery().toString();
        intent.putExtra(EXTRA_NAME,searchContent);
        startActivity(intent);

    }

}