package com.example.leftie.Essapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.Models.Upload;

import java.util.ArrayList;

public class inquirynotificationadapter extends RecyclerView.Adapter<inquirynotificationadapter.cardholder> {
    private ArrayList<Upload> incardlist;
    private OnItemClickListener inquirynotificationlistener;
    AlertDialog.Builder dialog;
    Context context;
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

    public inquirynotificationadapter(Context context,ArrayList<Upload> cardlist){
        context = context;
        incardlist = cardlist;
        dialog = new AlertDialog.Builder(context,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
    }


    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inquiriesnotifications_cardview,parent,false);
        cardholder ch = new cardholder(v,inquirynotificationlistener);
        return ch;
    }

    @Override
    public void onBindViewHolder(cardholder holder, int position) {
        final Upload currentitem = incardlist.get(position);

        holder.mtextview1.setText(currentitem.getTitle());
        holder.mtextview2.setText(currentitem.getSubtitle());
        holder.mtextview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(false);
                dialog.setTitle(currentitem.getTitle());
                dialog.setMessage(currentitem.getMessage());
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return incardlist.size();
    }
}
