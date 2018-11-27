package com.example.leftie.Essapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.carditems;

import java.util.ArrayList;

public class inquirynotificationadapter extends RecyclerView.Adapter<inquirynotificationadapter.cardholder> {
    private ArrayList<carditems> incardlist;
    private OnItemClickListener inquirynotificationlistener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        inquirynotificationlistener = listener;
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

    public inquirynotificationadapter(ArrayList<carditems> cardlist){
        incardlist = cardlist;

    }


    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiriesnotifications_cardview,parent,false);
        cardholder ch = new cardholder(v,inquirynotificationlistener);
        return ch;
    }

    @Override
    public void onBindViewHolder(cardholder holder, int position) {
        carditems currentitem = incardlist.get(position);

        holder.mtextview1.setText(currentitem.getMtext1());
        holder.mtextview2.setText(currentitem.getMtext2());

    }

    @Override
    public int getItemCount() {
        return incardlist.size();
    }
}
