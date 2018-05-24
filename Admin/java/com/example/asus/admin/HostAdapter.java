package com.example.asus.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HostAdapter  extends RecyclerView.Adapter<HostAdapter.MyViewHolderHost>
{
    ArrayList<HostDetails> hd;
    Context context;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    String village;
    AlertDialog d;

    public HostAdapter(ArrayList<HostDetails> d, Context c)
    {
        hd=d;
        context=c;
    }

    @Override
    public HostAdapter.MyViewHolderHost onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        HostAdapter.MyViewHolderHost vh = new HostAdapter.MyViewHolderHost(v);
        context=v.getContext();
        return vh;
    }

    @Override

    public void onBindViewHolder(final HostAdapter.MyViewHolderHost holder, final int position)
    {

        holder.hostname.setText(hd.get(position).name.toString());
        holder.contact.setText("Contact: " +hd.get(position).contact);
        holder.address.setText("Address: "+hd.get(position).address.toString());
        holder.rooms.setText("Rooms Available: " +hd.get(position).rooms_ava);
        if(hd.get(position).approved.equals("APPROVED"))
        {

            holder.approved.setTextColor(Color.parseColor("#005500"));
            holder.approved.setText("APPROVED");
           holder.sc.setChecked(true);


        }
        else
        {
            holder.approved.setTextColor(Color.parseColor("#c33636"));
            holder.approved.setText("NOT APPROVED");
            holder.sc.setChecked(false);

        }
        village=hd.get(position).village;

        holder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    databaseReference=firebaseDatabase.getReference().child("VillageRelation").child(village).child("HostRelation");
                    if(isChecked)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseReference.child(hd.get(position).name).child("approved").setValue("APPROVED");
                                holder.sc.setChecked(true);
                                hd.clear();

                            }
                        })
                                .setTitle("Approve Host?").setMessage("Do you want to approve?");
                        AlertDialog d = alert.create();
                        d.show();
                    }
                    else
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseReference.child(hd.get(position).name).child("approved").setValue("NOT APPROVED");
                                holder.sc.setChecked(false);
                                hd.clear();

                            }
                        })
                              .setTitle("Disapprove Host?").setMessage("Do you want to disapprove?");
                        d = alert.create();
                        d.show();
                    }

                }

        });

    }

    @Override
    public int getItemCount() {
        return hd.size();
    }


    public class MyViewHolderHost extends RecyclerView.ViewHolder {
        public TextView hostname;
        public TextView address;
        public TextView contact;
        public TextView rooms;
        TextView approved;
       CheckBox sc;


        public MyViewHolderHost(View v) {
            super(v);
            hostname = (TextView) v.findViewById(R.id.host);
            address = (TextView) v.findViewById(R.id.address);
            contact = (TextView) v.findViewById(R.id.contact);
            rooms=(TextView) v.findViewById(R.id.rooms);
            approved=(TextView) v.findViewById(R.id.h_approved);
            sc = (CheckBox) v.findViewById(R.id.check);

        }



 }
}
