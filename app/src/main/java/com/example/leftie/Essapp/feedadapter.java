package com.example.leftie.Essapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class feedadapter extends RecyclerView.Adapter<feedadapter.cardholder> {
    private ArrayList<carditems> fcardlist;
    private OnItemClickListener feedlistener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        feedlistener = listener;
    }


    public static class cardholder extends RecyclerView.ViewHolder{
        public TextView mtextview1, mtextview2;

        public cardholder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mtextview1 = itemView.findViewById(R.id.txttitle);
            mtextview2 = itemView.findViewById(R.id.txtsubtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener !=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }

                }
            });

        }
    }

    public feedadapter(ArrayList<carditems> cardlist){
        fcardlist = cardlist;

    }

    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_cardview,parent,false);
        cardholder ch = new cardholder(v,feedlistener);
        return ch;
    }

    @Override
    public void onBindViewHolder(cardholder holder, int position) {
        carditems currentitem = fcardlist.get(position);

        holder.mtextview1.setText(currentitem.getMtext1());
        holder.mtextview2.setText(currentitem.getMtext2());

    }

    @Override
    public int getItemCount() {
        return fcardlist.size();
    }

}
