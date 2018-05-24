package com.example.vishalsingh.villageexpandedview;

import android.content.Context;
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

/**
 * Created by vishalsingh on 24/03/18.
 */

public class RecyclerViewAdapterAccomodation extends RecyclerView.Adapter<RecyclerViewAdapterAccomodation.MyViewHolder>  {

    ArrayList<Accomodation> accomodations;
    Context context;
    RecyclerViewAdapterCuisine.OnItemClicked onClick;
    StorageReference mstorage;

    RecyclerViewAdapterAccomodation(Context context, ArrayList<Accomodation> accomodations){
        this.context = context;
        this.accomodations = accomodations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        RecyclerViewAdapterAccomodation.MyViewHolder vh = new RecyclerViewAdapterAccomodation.MyViewHolder(v);
        return vh;
    }


    public interface OnItemClicked{
        void onItemClick(int position);
    }



    public void onBindViewHolder(RecyclerViewAdapterAccomodation.MyViewHolder holder, final int position) {

        holder.nameOfPlace.setText(accomodations.get(position).getNameOfplace());

        mstorage= FirebaseStorage.getInstance().getReference().child(accomodations.get(position).getImage1());
        Glide.with(context).using(new FirebaseImageLoader())
                .load(mstorage)
                .into(holder.imagesofplace1);

        holder.address.setText("");

        holder.price.setText("Avg price: Rs. "+accomodations.get(position).getPrice());

        double someRating = accomodations.get(position).getRating();

        if (someRating < 2)
            holder.rating.setBackgroundColor(Color.parseColor("#ca0c06"));
        else if (someRating >= 2 && someRating <= 3.5)
            holder.rating.setBackgroundColor(Color.parseColor("#FFF29741"));

        holder.rating.setText(String.valueOf(accomodations.get(position).getRating()));


        holder.imagesofplace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }
    public void setOnClick(RecyclerViewAdapterCuisine.OnItemClicked onClick){
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return accomodations.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfPlace, rating , address, price; //for now address is stored in textview
        ImageView imagesofplace1;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameOfPlace = (TextView)itemView.findViewById(R.id.foodname);
            rating = (TextView)itemView.findViewById(R.id.rating);
            address = (TextView)itemView.findViewById(R.id.foodlocation);
            imagesofplace1 = (ImageView)itemView.findViewById(R.id.image1);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}

//    Accomodation(Address address, String price, String h_name, String contact, int capacityEachRoom, String image1,String image2, int roomsAvailable,String email,int rating){

