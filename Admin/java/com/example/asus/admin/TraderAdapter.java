package com.example.asus.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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

public class TraderAdapter extends RecyclerView.Adapter<TraderAdapter.MyViewHolderTrader>
{
    ArrayList<TraderDetails> td;
    Context context;
    String village;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    AlertDialog d;

    public TraderAdapter(ArrayList<TraderDetails> d,Context c)
    {
        td=d;
        context=c;
    }

    @Override
    public TraderAdapter.MyViewHolderTrader onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trader_row_layout, parent, false);
        TraderAdapter.MyViewHolderTrader vh = new TraderAdapter.MyViewHolderTrader(v);
        context=v.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolderTrader holder,final int position) {

        holder.shopname.setText(td.get(position).shopname);
        holder.contact.setText("Contact: "+td.get(position).contact);
        holder.owner.setText("Owner: "+td.get(position).owner);
        holder.category.setText("Category: "+td.get(position).category);

        if (td.get(position).approved.equals("APPROVED")) {
            holder.approved.setTextColor(Color.parseColor("#005500"));
            holder.approved.setText("APPROVED");
            holder.sct.setChecked(true);
        }
        else
        {
            holder.approved.setTextColor(Color.parseColor("#c33636"));
            holder.approved.setText("NOT APPROVED");
            holder.sct.setChecked(false);

        }
        village=td.get(position).village;

        holder.sct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                databaseReference=firebaseDatabase.getReference().child("VillageRelation").child(village).child("TraderRelation");
                if(isChecked)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseReference.child(td.get(position).shopname).child("approved").setValue("APPROVED");
                            holder.sct.setChecked(true);
                            td.clear();

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
                            databaseReference.child(td.get(position).shopname).child("approved").setValue("NOT APPROVED");
                            holder.sct.setChecked(false);
                            td.clear();

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
        return td.size();
    }


    public class MyViewHolderTrader extends RecyclerView.ViewHolder {
        public TextView shopname;
        public TextView owner;
        public TextView contact;
        public TextView category;
        TextView approved;
        CheckBox sct;


        public MyViewHolderTrader(View v) {
            super(v);
            shopname = (TextView) v.findViewById(R.id.shopname);
            owner = (TextView) v.findViewById(R.id.owner);
            contact = (TextView) v.findViewById(R.id.contact);
            category=(TextView) v.findViewById(R.id.category);
            approved=(TextView) v.findViewById(R.id.approved);
            sct = (CheckBox) v.findViewById(R.id.check);
        }
    }
}
