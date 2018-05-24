package com.example.asus.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ImageApprovalAdapter extends RecyclerView.Adapter<ImageApprovalAdapter.MyViewHolder>
{
    ArrayList<ImageApprovalDetails> imgs;
    Context context;
    StorageReference mstorage;
    DatabaseReference databaseReference;
    AlertDialog d;

    public ImageApprovalAdapter(ArrayList<ImageApprovalDetails> imgs, Context context)
    {
        this.imgs=imgs;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row_layout, parent, false);
        ImageApprovalAdapter.MyViewHolder vh = new ImageApprovalAdapter.MyViewHolder(v);
        context=v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position)
    {
        mstorage= FirebaseStorage.getInstance().getReference(imgs.get(position).img);
        Glide.with(context).using(new FirebaseImageLoader()).load(mstorage).into(holder.img);

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // databaseReference.child(td.get(position).shopname).child("approved").setValue("APPROVED");
                            databaseReference=FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(imgs.get(position).village);
                            databaseReference.child("ImageGallery").child(imgs.get(position).key).setValue(imgs.get(position).img);
                            holder.cb.setChecked(true);
                            imgs.clear();

                        }
                    })
                            .setTitle("Approve Trader?").setMessage("Do you want to approve?");
                    AlertDialog d = alert.create();
                    d.show();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    })
                            .setTitle("Disapprove Trader?").setMessage("Do you want to disapprove?");
                    d = alert.create();
                    d.show();
                }

            }

        });




    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView approved;
        CheckBox cb;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imgview);
            approved=(TextView)itemView.findViewById(R.id.approved);


        }
    }
}

