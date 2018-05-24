package com.example.asus.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.MyViewHolderGuide>
{
    ArrayList<GuideDetails> gd;
    Context context;
    String village;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    AlertDialog d;



    public GuideAdapter(ArrayList<GuideDetails> d,Context c)
    {
        gd=d;
        context=c;
    }

    @Override
    public GuideAdapter.MyViewHolderGuide onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_row_layout, parent, false);
        GuideAdapter.MyViewHolderGuide vh = new GuideAdapter.MyViewHolderGuide(v);
        context=v.getContext();
        return vh;
    }



    @Override
    public void onBindViewHolder(final GuideAdapter.MyViewHolderGuide holder, final int position)
    {

        holder.guidename.setText(gd.get(position).guide_name.toString());
        holder.contact.setText("Contact: "+gd.get(position).guide_contact);

        if(gd.get(position).guide_approved.equals("APPROVED"))
        {
            holder.approved.setTextColor(Color.parseColor("#005500"));
            holder.approved.setText("APPROVED");
            holder.scg.setChecked(true);

        }
        else
        {
            holder.approved.setTextColor(Color.parseColor("#c33636"));
            holder.approved.setText("NOT APPROVED");
            holder.scg.setChecked(false);
        }
        village=gd.get(position).village;
        holder.scg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                databaseReference=firebaseDatabase.getReference().child("VillageRelation").child(village).child("GuideRelation");
                if(isChecked)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseReference.child(gd.get(position).guide_name).child("approved").setValue("APPROVED");
                            holder.scg.setChecked(true);
                            gd.clear();

                        }
                    })
                            .setTitle("Approve Guide?").setMessage("Do you want to approve?");
                   d = alert.create();
                    d.show();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseReference.child(gd.get(position).guide_name).child("approved").setValue("NOT APPROVED");
                            holder.scg.setChecked(false);
                            gd.clear();

                        }
                    })
                            .setTitle("Disapprove Guide?").setMessage("Do you want to disapprove?");
                    d = alert.create();
                    d.show();
                }

            }

        });


    }

    @Override
    public int getItemCount() {
        return gd.size();
    }


    public class MyViewHolderGuide extends RecyclerView.ViewHolder {
        public TextView guidename;
        public TextView contact;
        public TextView approved;
        CheckBox scg;


        public MyViewHolderGuide(View v) {
            super(v);
            guidename = (TextView) v.findViewById(R.id.name);
            contact = (TextView) v.findViewById(R.id.contact);
            approved=(TextView) v.findViewById(R.id.approved);
            scg = (CheckBox) v.findViewById(R.id.check);
        }
    }
}

