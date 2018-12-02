package com.example.leftie.Essapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leftie.Essapp.Models.Upload;
import com.example.leftie.Essapp.R;

import java.util.ArrayList;

public class inquirynotificationadapter extends RecyclerView.Adapter<inquirynotificationadapter.cardholder> {
    private ArrayList<Upload> incardlist;

    public static class cardholder extends RecyclerView.ViewHolder{
        public TextView mtextview1, mtextview2;

        public cardholder(View itemView) {
            super(itemView);
            mtextview1 = itemView.findViewById(R.id.txttitle);
            mtextview2 = itemView.findViewById(R.id.txtsubtitle);
        }
    }

    public inquirynotificationadapter(ArrayList<Upload> cardlist){
        incardlist = cardlist;
    }


    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiriesnotifications_cardview,parent,false);
        cardholder ch = new cardholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(cardholder holder, int position) {
        final Upload currentitem = incardlist.get(position);

        holder.mtextview1.setText(currentitem.getTitle());
        holder.mtextview2.setText(currentitem.getSubtitle());
      }

    @Override
    public int getItemCount() {
        return incardlist.size();
    }
}
