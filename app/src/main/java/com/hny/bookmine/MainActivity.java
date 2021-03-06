package com.hny.bookmine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.bookmine.extra.searchContent";
    public static final String EXTRA_NAME_F = "com.example.bookmine.extra.filtrbartext";

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
    public static final String EXTRA_TEXT18= "Extra.search.for.third.page.category15";

    public static SearchView mSearchField;
    private RecyclerView mResultList;
    private RecyclerView mResultList1;
    private FirebaseFirestore mUserDatabase;
    private TextInputLayout mFilterBar;
    private AutoCompleteTextView mFilterBarO2;
    private ArrayList<String> mFilterBarList;
    private ArrayAdapter<String> mFilterListArryAdap;
    private FirestorePagingAdapter adapter;
    private FirestorePagingAdapter adapter1;
    private String z;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mFilterBar=findViewById(R.id.textInputLayout);
        mFilterBarO2=findViewById(R.id.autoComplete);
        mFilterBarList=new ArrayList<>();
        mFilterBarList.add("All");
        mFilterBarList.add("Academic");
        mFilterBarList.add("Action");
        mFilterBarList.add("Adolescence");
        mFilterBarList.add("Adult");
        mFilterBarList.add("Adult Fiction");
        mFilterBarList.add("Adventure");
        mFilterBarList.add("Aeroplanes");
        mFilterBarList.add("Africa");
        mFilterBarList.add("African Literature");
        mFilterBarList.add("Alcohol");
        mFilterBarList.add("American");
        mFilterBarList.add("Amish");
        mFilterBarList.add("Anarchism");
        mFilterBarList.add("Animals");
        mFilterBarList.add("Anthologies");
        mFilterBarList.add("Anthropology");
        mFilterBarList.add("Apocalyptic");
        mFilterBarList.add("Architecture");
        mFilterBarList.add("Art");
        mFilterBarList.add("Artificial");
        mFilterBarList.add("Asian");
        mFilterBarList.add("Astronomy");
        mFilterBarList.add("Authors");
        mFilterBarList.add("Autobiography");
        mFilterBarList.add("Aviation");
        mFilterBarList.add("Awards");
        mFilterBarList.add("Beer");
        mFilterBarList.add("Biblical");
        mFilterBarList.add("Biography");
        mFilterBarList.add("Biology");
        mFilterBarList.add("Book");
        mFilterBarList.add("Books");
        mFilterBarList.add("Brides");
        mFilterBarList.add("Buddhism");
        mFilterBarList.add("Business");
        mFilterBarList.add("Canon");
        mFilterBarList.add("Cartography");
        mFilterBarList.add("Category");
        mFilterBarList.add("Central");
        mFilterBarList.add("Childrens");
        mFilterBarList.add("Christian");
        mFilterBarList.add("Christianity");
        mFilterBarList.add("Church");
        mFilterBarList.add("Cities");
        mFilterBarList.add("Civil");
        mFilterBarList.add("Classics");
        mFilterBarList.add("Club");
        mFilterBarList.add("Colouring");
        mFilterBarList.add("Combat");
        mFilterBarList.add("Comics");
        mFilterBarList.add("Computer");
        mFilterBarList.add("Computers");
        mFilterBarList.add("Conservation");
        mFilterBarList.add("Contemporary");
        mFilterBarList.add("Couture");
        mFilterBarList.add("Crafts");
        mFilterBarList.add("Crime");
        mFilterBarList.add("Criticism");
        mFilterBarList.add("Cryptids");
        mFilterBarList.add("Cultural");
        mFilterBarList.add("Culture");
        mFilterBarList.add("Currency");
        mFilterBarList.add("Dark");
        mFilterBarList.add("Death");
        mFilterBarList.add("Design");
        mFilterBarList.add("Diary");
        mFilterBarList.add("Did");
        mFilterBarList.add("Disability");
        mFilterBarList.add("Doctors");
        mFilterBarList.add("Dragons");
        mFilterBarList.add("Drama");
        mFilterBarList.add("Drink");
        mFilterBarList.add("Dungeons");
        mFilterBarList.add("Earth");
        mFilterBarList.add("Eastern");
        mFilterBarList.add("Economics");
        mFilterBarList.add("Education");
        mFilterBarList.add("Emergency");
        mFilterBarList.add("Engineering");
        mFilterBarList.add("English");
        mFilterBarList.add("Environment");
        mFilterBarList.add("Epic");
        mFilterBarList.add("Erotica");
        mFilterBarList.add("Esoterica");
        mFilterBarList.add("European");
        mFilterBarList.add("Fairy");
        mFilterBarList.add("Family");
        mFilterBarList.add("Fantasy");
        mFilterBarList.add("Favorites");
        mFilterBarList.add("Female");
        mFilterBarList.add("Feminism");
        mFilterBarList.add("Fiction");
        mFilterBarList.add("Finance");
        mFilterBarList.add("Finish");
        mFilterBarList.add("Folk");
        mFilterBarList.add("Folklore");
        mFilterBarList.add("Food");
        mFilterBarList.add("Football");
        mFilterBarList.add("Futurism");
        mFilterBarList.add("Futuristic");
        mFilterBarList.add("Gamebooks");
        mFilterBarList.add("Games");
        mFilterBarList.add("Gardening");
        mFilterBarList.add("Gender");
        mFilterBarList.add("Geography");
        mFilterBarList.add("Geology");
        mFilterBarList.add("German");
        mFilterBarList.add("GLBT");
        mFilterBarList.add("Gothic");
        mFilterBarList.add("Graphic");
        mFilterBarList.add("Harlequin");
        mFilterBarList.add("Health");
        mFilterBarList.add("Help");
        mFilterBarList.add("Heroic");
        mFilterBarList.add("Historical");
        mFilterBarList.add("History");
        mFilterBarList.add("Holiday");
        mFilterBarList.add("Horror");
        mFilterBarList.add("Hugo");
        mFilterBarList.add("Humanities");
        mFilterBarList.add("Humor");
        mFilterBarList.add("Illness");
        mFilterBarList.add("Inspirational");
        mFilterBarList.add("Inspired");
        mFilterBarList.add("Intelligence");
        mFilterBarList.add("International");
        mFilterBarList.add("Islam");
        mFilterBarList.add("Issues");
        mFilterBarList.add("Jewellery");
        mFilterBarList.add("Judaism");
        mFilterBarList.add("Justice");
        mFilterBarList.add("Kids");
        mFilterBarList.add("Labor");
        mFilterBarList.add("Language");
        mFilterBarList.add("Law");
        mFilterBarList.add("Leadership");
        mFilterBarList.add("Lesbian");
        mFilterBarList.add("LGBT");
        mFilterBarList.add("Library");
        mFilterBarList.add("Linguistics");
        mFilterBarList.add("Literary");
        mFilterBarList.add("Literature");
        mFilterBarList.add("Love");
        mFilterBarList.add("Lovecraftian");
        mFilterBarList.add("Magical");
        mFilterBarList.add("Mail");
        mFilterBarList.add("Manga");
        mFilterBarList.add("Marriage");
        mFilterBarList.add("Martial");
        mFilterBarList.add("Marvel");
        mFilterBarList.add("Mathematics");
        mFilterBarList.add("Media");
        mFilterBarList.add("Medical");
        mFilterBarList.add("Medicine");
        mFilterBarList.add("Medievalism");
        mFilterBarList.add("Memoir");
        mFilterBarList.add("Menage");
        mFilterBarList.add("Mental");
        mFilterBarList.add("Military");
        mFilterBarList.add("Modern");
        mFilterBarList.add("Mountaineering");
        mFilterBarList.add("Movements");
        mFilterBarList.add("Museology");
        mFilterBarList.add("Music");
        mFilterBarList.add("Mystery");
        mFilterBarList.add("Mythology");
        mFilterBarList.add("Native");
        mFilterBarList.add("Nature");
        mFilterBarList.add("Neuroscience");
        mFilterBarList.add("Nobel");
        mFilterBarList.add("Nonfiction");
        mFilterBarList.add("Novella");
        mFilterBarList.add("Novels");
        mFilterBarList.add("Number");
        mFilterBarList.add("Nurses");
        mFilterBarList.add("Outdoors");
        mFilterBarList.add("Paranormal");
        mFilterBarList.add("Parenting");
        mFilterBarList.add("Philosophy");
        mFilterBarList.add("Photography");
        mFilterBarList.add("Physics");
        mFilterBarList.add("Plants");
        mFilterBarList.add("Playing");
        mFilterBarList.add("Plays");
        mFilterBarList.add("Poetry");
        mFilterBarList.add("Political");
        mFilterBarList.add("Politics");
        mFilterBarList.add("Polyamorous");
        mFilterBarList.add("Polyamory");
        mFilterBarList.add("Pop");
        mFilterBarList.add("Pornography");
        mFilterBarList.add("Prayer");
        mFilterBarList.add("Presidents");
        mFilterBarList.add("Prize");
        mFilterBarList.add("Productivity");
        mFilterBarList.add("Pseudoscience");
        mFilterBarList.add("Psychology");
        mFilterBarList.add("Pulp");
        mFilterBarList.add("Queer");
        mFilterBarList.add("Race");
        mFilterBarList.add("Realism");
        mFilterBarList.add("Realistic");
        mFilterBarList.add("Reference");
        mFilterBarList.add("Relationships");
        mFilterBarList.add("Religion");
        mFilterBarList.add("Reportage");
        mFilterBarList.add("Revolution");
        mFilterBarList.add("Rock");
        mFilterBarList.add("Role");
        mFilterBarList.add("Roll");
        mFilterBarList.add("Roman");
        mFilterBarList.add("Romance");
        mFilterBarList.add("Romantic");
        mFilterBarList.add("Russian");
        mFilterBarList.add("Satanism");
        mFilterBarList.add("Scandinavian");
        mFilterBarList.add("School");
        mFilterBarList.add("Sci");
        mFilterBarList.add("Science");
        mFilterBarList.add("Sequential");
        mFilterBarList.add("Services");
        mFilterBarList.add("Sex");
        mFilterBarList.add("Sexuality");
        mFilterBarList.add("Shapeshifters");
        mFilterBarList.add("Short");
        mFilterBarList.add("Soccer");
        mFilterBarList.add("Social");
        mFilterBarList.add("Sociology");
        mFilterBarList.add("Southern");
        mFilterBarList.add("Space");
        mFilterBarList.add("Speculative");
        mFilterBarList.add("Spirituality");
        mFilterBarList.add("Sports");
        mFilterBarList.add("Spy");
        mFilterBarList.add("Star");
        mFilterBarList.add("Stories");
        mFilterBarList.add("Story");
        mFilterBarList.add("Subways");
        mFilterBarList.add("Superheroes");
        mFilterBarList.add("Surreal");
        mFilterBarList.add("Suspense");
        mFilterBarList.add("Tales");
        mFilterBarList.add("Teaching");
        mFilterBarList.add("Textbooks");
        mFilterBarList.add("Thriller");
        mFilterBarList.add("Tragedy");
        mFilterBarList.add("Transport");
        mFilterBarList.add("Travel");
        mFilterBarList.add("Trek");
        mFilterBarList.add("Unfinished");
        mFilterBarList.add("Urban");
        mFilterBarList.add("Vegetarian");
        mFilterBarList.add("War");
        mFilterBarList.add("Warcraft");
        mFilterBarList.add("Warfare");
        mFilterBarList.add("Weird");
        mFilterBarList.add("Western");
        mFilterBarList.add("Westerns");
        mFilterBarList.add("Wildlife");
        mFilterBarList.add("Witchcraft");
        mFilterBarList.add("Womens");
        mFilterBarList.add("Work");
        mFilterBarList.add("World");
        mFilterBarList.add("Writing");
        mFilterBarList.add("Young Adult");
        //add catageris

        mFilterListArryAdap=new ArrayAdapter<>(getApplicationContext(),R.layout.filterbar,mFilterBarList);
        mFilterBarO2.setAdapter(mFilterListArryAdap);
        mFilterBarO2.setThreshold(1);
        mSearchField=findViewById(R.id.search_field);
        mUserDatabase = FirebaseFirestore.getInstance();
        mResultList1=findViewById(R.id.recycler_view1);


        mResultList=findViewById(R.id.recycler_view);


        search();


        //firebaseUserSearch("J");
        //if else for swich

        TextView notification1 = findViewById(R.id.textView21);



        FirebaseFirestore notification = FirebaseFirestore.getInstance();

        notification.collection("notification").orderBy("new")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                z =document.getData().get("note").toString();
                                notification1.setText(z);

                            }
                        } else {
                            String z =" ";
                            TextView notification = findViewById(R.id.textView21);
                            notification.setText(z);

                        }
                    }
                });




    }

    protected void onStart(){
        super.onStart();
        firebaseUserSearch1();

    }
    protected  void onStop(){
        super.onStop();
        adapter1.stopListening();

    }


    void search(){
        mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String[] words = query.toString().split(" ");
                StringBuilder sb = new StringBuilder();
                if (words[0].length() > 0) {
                    sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
                    for (int i = 1; i < words.length; i++) {
                        sb.append(" ");
                        sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
                    }
                }
                String titleCaseValue1 = sb.toString();

                if(query.contains(".")){
                    firebaseUserSearch(query);
                }else {
                    firebaseUserSearch(titleCaseValue1);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String[] words = newText.toString().split(" ");
                StringBuilder sb = new StringBuilder();
                if (words[0].length() > 0) {
                    sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
                    for (int i = 1; i < words.length; i++) {
                        sb.append(" ");
                        sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
                    }
                }
                String titleCaseValue = sb.toString();

                if(newText.contains(".")){
                    firebaseUserSearch(newText);
                }else {
                    firebaseUserSearch(titleCaseValue);
                }

                return false;
            }
        });

    }
    private void firebaseUserSearch(String searchText) {

        PagedList.Config config = new PagedList.Config.Builder()
                .setPrefetchDistance(8)
                .setPageSize(1)
                .build();

        Query q = FirebaseFirestore.getInstance().collection("Books").orderBy("author").startAt(searchText).endAt(searchText+"\uf8ff");
        FirestorePagingOptions<Books> options = new FirestorePagingOptions.Builder<Books>()
                .setLifecycleOwner(this)
                .setQuery(q,config,Books.class)
                .build();


        adapter = new FirestorePagingAdapter<Books, viewHolder>(options) {
            @NonNull
            @Override
            public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);

                return new viewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Books model) {
                holder.authorname.setText(model.getAuthor());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        onclicksewtquery(v,model.getAuthor());
                    }
                });
            }

            @Override
            protected void onLoadingStateChanged(@NonNull LoadingState state) {
                super.onLoadingStateChanged(state);
                switch (state){
                    case LOADED:
                        break;
                    case ERROR:
                        break;
                    case FINISHED:
                        break;
                    case LOADING_MORE:
                        break;
                    case LOADING_INITIAL:
                        break;
                }
            }
        };
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mResultList.setAdapter(adapter);
    }

    private class viewHolder extends RecyclerView.ViewHolder {
        private TextView authorname;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            authorname=itemView.findViewById(R.id.nametext);
        }
    }

    //recycler adapter end

    public  void onclicksewtquery(View v, String author){

        mSearchField.setQuery(author,false);
        adapter.stopListening();

    }



    public void onClick(View view){
        String searchContent1 = mSearchField.getQuery().toString();
        String Filterbartext1 = mFilterBar.getEditText().getText().toString().trim();
        if(searchContent1.equals(""))
        {

            Toast.makeText(MainActivity.this,"Please Enter Author Name",Toast.LENGTH_SHORT).show();

        }
        else
        {
            if(Filterbartext1.equals(""))
            {
                Toast.makeText(MainActivity.this,"Please Select Category",Toast.LENGTH_SHORT).show();
            }
            else
            {
                onClick1(view);
            }
        }

    }


    public void onClickabouus(View view)
    {

        Intent intent = new Intent(this, aboutus.class);
        startActivity(intent);
    }

    public void onClick1(View view)
    {
        String Filterbartext = mFilterBar.getEditText().getText().toString().trim();
        Toast.makeText(MainActivity.this,"Searching...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2.class);
        mSearchField= findViewById(R.id.search_field);
        String searchContent = mSearchField.getQuery().toString();
        intent.putExtra(EXTRA_NAME,searchContent);
        intent.putExtra(EXTRA_NAME_F,Filterbartext);

        startActivity(intent);

    }

    //firest page cardview of all books

    private void firebaseUserSearch1() {

        set1(2);

    }

    private void set1(int i){

        Query firebaseSearchQueary;

        firebaseSearchQueary = mUserDatabase.collection("Books").orderBy("genre_and_votes");

        PagedList.Config config = new PagedList.Config.Builder()
                .setPrefetchDistance(15)
                .setPageSize(3)
                .build();
        FirestorePagingOptions<Book1> options =new FirestorePagingOptions.Builder<Book1>()
                .setLifecycleOwner(this)
                .setQuery(firebaseSearchQueary,config,Book1.class)
                .build();


        adapter1= new FirestorePagingAdapter<Book1, MainActivity.viewholder2>(options) {
            @NonNull
            @Override
            public MainActivity.viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.firstscreen,parent,false);
                return new MainActivity.viewholder2(view);
            }


            @Override
            protected void onBindViewHolder(@NonNull viewholder2 holder, int position, @NonNull Book1 model) {

                holder.setDetailsfirstcard1(model.getTitle(),model.getCover_link());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity3(v,getApplicationContext(),model.getTitle(), model.getCover_link(), model.getAuthor(), model.getGenre_and_votes(), model.getNumber_of_pages(), model.getYear_published(), model.getAmazon_redirect_link(), model.getAuthor_link(), model.getFive_star_ratings(), model.getFour_star_ratings(), model.getBooklinks(), model.getOne_star_ratings(), model.getRating_count(),model.getThree_star_ratings(),model.getTwo_star_ratings());
                    }
                });
            }

        };
        set();

    }

    private void set(){
        mResultList1.setHasFixedSize(true);
        mResultList1.setLayoutManager(new LinearLayoutManager(this));
        mResultList1.setAdapter(adapter1);
    }
    private class viewholder2 extends RecyclerView.ViewHolder
    {

        TextView book_name;
        TextView author_name;
        TextView Category_name;
        ImageView image;

        public viewholder2(@NonNull View itemView) {

            super(itemView);
        }

        private void setDetailsfirstcard1(String name,String getCover) {
            book_name = itemView.findViewById(R.id.booknametext7);
            image = itemView.findViewById(R.id.bookcoverimg7);

            book_name.setText(name);
            Glide.with(image.getContext()).load(getCover).into(image);

        }

    }


    private void openActivity3(View view, Context con, String bookname, String coverlink, String authorname, String category, String noofpages, String year, String amazon_redirect_url, String author_link, String five_star_rating, String four_star_rating, String booklinks, String onr_star_rating, String rating_count, String three_star_rating, String toe_star_rating) {
        Toast.makeText(MainActivity.this, "Opening " + bookname, Toast.LENGTH_SHORT).show();
        adapter1.stopListening();
        Intent intent1 = new Intent(MainActivity.this, BOOKMINE3.class);
        intent1.putExtra(EXTRA_TEXT1, bookname);
        intent1.putExtra(EXTRA_TEXT2, coverlink);
        intent1.putExtra(EXTRA_TEXT3, authorname);
        intent1.putExtra(EXTRA_TEXT4, category);
        intent1.putExtra(EXTRA_TEXT5, noofpages);
        intent1.putExtra(EXTRA_TEXT6, year);
        intent1.putExtra(EXTRA_TEXT7, amazon_redirect_url);
        intent1.putExtra(EXTRA_TEXT8, author_link);
        intent1.putExtra(EXTRA_TEXT9, five_star_rating);
        intent1.putExtra(EXTRA_TEXT10, four_star_rating);
        intent1.putExtra(EXTRA_TEXT11, booklinks);
        intent1.putExtra(EXTRA_TEXT12, onr_star_rating);
        intent1.putExtra(EXTRA_TEXT13, rating_count);
        // intent1.putExtra(EXTRA_TEXT14,review_count);
        // intent1.putExtra(EXTRA_TEXT15,worldcat_redirect_link);
        intent1.putExtra(EXTRA_TEXT16, three_star_rating);
        intent1.putExtra(EXTRA_TEXT17, toe_star_rating);
        intent1.putExtra(EXTRA_TEXT18,"MAIN1");
        startActivity(intent1);
    }



}