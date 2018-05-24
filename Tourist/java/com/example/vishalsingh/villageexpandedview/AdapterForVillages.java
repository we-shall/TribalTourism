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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by vishalsingh on 25/03/18.
 */

public class AdapterForVillages extends RecyclerView.Adapter<AdapterForVillages.MyViewHolder> {

    ArrayList<Description> descriptions;
    Context context;
    AdapterForVillages.OnItemClicked onClick;
    StorageReference reference;

    AdapterForVillages(Context context, ArrayList<Description> descriptions){
        this.context = context;
        this.descriptions = descriptions;
    }



    public interface OnItemClicked{
        void onItemClick(int position);
    }

    public void setOnClick(OnItemClicked onClick){
        this.onClick = onClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_village, parent, false);
        AdapterForVillages.MyViewHolder vh = new AdapterForVillages.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(descriptions.get(position).getNameOfVillage());

        String[] arrayofstrings = descriptions.get(position).getDescription().split(" ");
        if (arrayofstrings.length < 20) {
            holder.desc.setText(descriptions.get(position).getDescription());
        }
        else
        {
            String str = "";
            for (int i = 0; i < 20; i ++){
                str = str + " " + arrayofstrings[i];
            }
            holder.desc.setText(str.toString());
        }

        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });

        double someRating = descriptions.get(position).getRating();

        if (someRating < 2)
            holder.rating.setBackgroundColor(Color.parseColor("#ca0c06"));
        else if (someRating >= 2 && someRating <= 3.5)
            holder.rating.setBackgroundColor(Color.parseColor("#FFF29741"));

        holder.rating.setText(String.valueOf(descriptions.get(position).getRating()));


        reference = FirebaseStorage.getInstance().getReference().child(descriptions.get(position).getGalleryImage2().toString());

        Glide.with(context).using(new FirebaseImageLoader())
                .load(reference)
                .into(holder.img);

    }
    @Override
    public int getItemCount() {
        return descriptions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc,speciality,rating,readmore;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            readmore = (TextView)itemView.findViewById(R.id.readmore);
            name = (TextView)itemView.findViewById(R.id.name);
            desc = (TextView)itemView.findViewById(R.id.name2);
            rating = (TextView)itemView.findViewById(R.id.rating);
            img = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}


