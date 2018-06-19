package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 21/03/18.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerViewAdapterCuisine extends RecyclerView.Adapter<RecyclerViewAdapterCuisine.MyViewHolder> {

    ArrayList<Cuisine> cuisine;
    Context context;
    OnItemClicked onClick;
    StorageReference mstorage;

    RecyclerViewAdapterCuisine(Context context, ArrayList<Cuisine> cuisine){
        this.context = context;
        this.cuisine = cuisine;
    }


    public interface OnItemClicked{
        void onItemClick(int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(cuisine.get(position).getLongname());
        holder.foodImage.post(new Runnable() {
            @Override
            public void run() {
                mstorage= FirebaseStorage.getInstance().getReference().child(cuisine.get(position).getImage());
                Glide.with(context).using(new FirebaseImageLoader())
                        .load(mstorage)
                        .into(holder.foodImage);
            }
        });



        holder.address.setText(cuisine.get(position).getLocation());

        double someRating = cuisine.get(position).getRating();

        if (someRating < 2)
            holder.rating.setBackgroundColor(Color.parseColor("#ca0c06"));
        else if (someRating >= 2 && someRating <= 3.5)
            holder.rating.setBackgroundColor(Color.parseColor("#FFF29741"));

        holder.rating.setText(String.valueOf(cuisine.get(position).getRating()));

        if (cuisine.get(position).getCategory() == 1)
            holder.category.setImageResource(R.drawable.nonveg);
        else
            holder.category.setImageResource(R.drawable.veg);

        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }
    public void setOnClick(OnItemClicked onClick){
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return cuisine.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, rating, address;
        ImageView foodImage, category;

        public MyViewHolder(View itemView) {
            super(itemView);
            foodImage = (ImageView) itemView.findViewById(R.id.image1);
            category = (ImageView) itemView.findViewById(R.id.category);
            name = (TextView)itemView.findViewById(R.id.foodname);
            rating = (TextView)itemView.findViewById(R.id.rating);
            address = (TextView)itemView.findViewById(R.id.foodlocation);
        }
    }
}
