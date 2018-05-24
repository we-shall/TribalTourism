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
 * Created by vishalsingh on 29/03/18.
 */

public class AdapterAttire extends RecyclerView.Adapter<AdapterAttire.MyViewHolder> {

    ArrayList<Attire> attires;
    Context context;
    AdapterAttire.OnItemClicked onClick;
    StorageReference mstorage;

    AdapterAttire(Context context, ArrayList<Attire> attires){
        this.context = context;
        this.attires = attires;
    }


    public interface OnItemClicked{
        void onItemClick(int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        AdapterAttire.MyViewHolder vh = new AdapterAttire.MyViewHolder(v);
        return vh;
    }



    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(attires.get(position).getNameOfOutFit());

        mstorage= FirebaseStorage.getInstance().getReference().child(attires.get(position).getImageOfCloth());
        Glide.with(context).using(new FirebaseImageLoader())
                .load(mstorage)
                .into(holder.attire);

        holder.address.setText("");

        holder.price.setText("Avg price: Rs. "+attires.get(position).getCost());

        double someRating = attires.get(position).getRating();

        if (someRating < 2)
            holder.rating.setBackgroundColor(Color.parseColor("#ca0c06"));
        else if (someRating >= 2 && someRating <= 3.5)
            holder.rating.setBackgroundColor(Color.parseColor("#FFF29741"));

        holder.rating.setText(String.valueOf(attires.get(position).getRating()));


        holder.attire.setOnClickListener(new View.OnClickListener() {
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
        return attires.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, rating, address, price;
        ImageView attire;

        public MyViewHolder(View itemView) {
            super(itemView);
            attire = (ImageView) itemView.findViewById(R.id.image1);
            name = (TextView)itemView.findViewById(R.id.foodname);
            rating = (TextView)itemView.findViewById(R.id.rating);
            address = (TextView)itemView.findViewById(R.id.foodlocation);
            price = (TextView)itemView.findViewById(R.id.price);
        }
    }
}

