package com.example.bookmine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter1 extends FirebaseRecyclerAdapter<Book1,myadapter1.myviewholder1> {



    public myadapter1(@NonNull FirebaseRecyclerOptions<Book1> options1) {
        super(options1);

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder1 holder, int position, @NonNull Book1 model) {
        holder.book_name.setText(model.getTitle());
        holder.author_name.setText(model.getAuthor());
        holder.Category_name.setText(model.getGenre_and_votes());
        holder.page_no.setText(model.getNumber_of_pages());
        Glide.with(holder.image.getContext()).load(model.getCover_link()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(holder.itemView.getContext(),"Opening "+model.getTitle(),Toast.LENGTH_SHORT).show();
                MainActivity2.openActivity3(v,holder.itemView.getContext(), model.getTitle(), model.getCover_link(), model.getAuthor(), model.getGenre_and_votes(), model.getNumber_of_pages(), model.getYear_published(), model.getAmazon_redirect_link(), model.getAuthor_link(), model.getFive_star_ratings(), model.getFour_star_ratings(), model.getBooklinks(), model.getOne_star_ratings(), model.getRating_count(),model.getThree_star_ratings(),model.getTwo_star_ratings());
            }
        });

    }

    @NonNull
    @Override
    public myviewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.second_screen_cardview,parent,false);
        return new myviewholder1(view);
    }

    class myviewholder1 extends RecyclerView.ViewHolder
    {
        TextView book_name;
        TextView author_name;
        TextView Category_name;
        TextView page_no;
        ImageView image;
        public myviewholder1(@NonNull View itemView) {

            super(itemView);
            book_name= itemView.findViewById(R.id.booknametext);
            author_name = itemView.findViewById(R.id.authornametext);
            Category_name  = itemView.findViewById(R.id.categorytext);
            page_no = itemView.findViewById(R.id.noofpagestext);
            image = itemView.findViewById(R.id.bookcoverimg);


        }
    }


}
