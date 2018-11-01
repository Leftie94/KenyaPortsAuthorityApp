package com.example.leftie.Essapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class cardadapter extends RecyclerView.Adapter<cardadapter.cardholder> {
    private ArrayList<carditems> mcardlist;

    public static class cardholder extends RecyclerView.ViewHolder{
        public TextView mtextview1, mtextview2;

        public cardholder(View itemView) {
            super(itemView);
            mtextview1 = itemView.findViewById(R.id.txttitle);
            mtextview2 = itemView.findViewById(R.id.txtsubtitle);
        }
    }

    public cardadapter(ArrayList<carditems> cardlist){
        mcardlist = cardlist;

    }

    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_items,parent,false);
        cardholder ch = new cardholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(cardholder holder, int position) {
        carditems currentitem = mcardlist.get(position);

        holder.mtextview1.setText(currentitem.getMtext1());
        holder.mtextview2.setText(currentitem.getMtext2());

    }

    @Override
    public int getItemCount() {
        return mcardlist.size();
    }

}
