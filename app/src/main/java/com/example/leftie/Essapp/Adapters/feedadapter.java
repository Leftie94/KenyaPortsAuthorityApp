package com.example.leftie.Essapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leftie.Essapp.Models.Upload;
import com.example.leftie.Essapp.R;

import java.util.ArrayList;

public class feedadapter extends RecyclerView.Adapter<feedadapter.cardholder> {
    private ArrayList<Upload> fcardlist;
    private OnItemClickListener feedlistener;
    AlertDialog.Builder dialog;
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public static class cardholder extends RecyclerView.ViewHolder{
        public TextView mtextview1, mtextview2;

        public cardholder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mtextview1 = itemView.findViewById(R.id.txttitle);
            mtextview2 = itemView.findViewById(R.id.txtsubtitle);

        }
    }

    public feedadapter(ArrayList<Upload> cardlist){
        fcardlist = cardlist;

    }

    @Override
    public cardholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_cardview,parent,false);
        cardholder ch = new cardholder(v,feedlistener);
        return ch;
    }

    @Override
    public void onBindViewHolder(final cardholder holder, final int position) {
        final Upload currentitem = fcardlist.get(position);

        holder.mtextview1.setText(currentitem.getTitle());
        holder.mtextview2.setText(currentitem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return fcardlist.size();
    }

}
