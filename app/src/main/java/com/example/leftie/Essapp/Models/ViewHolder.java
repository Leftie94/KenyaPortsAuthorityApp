package com.example.leftie.Essapp.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.leftie.Essapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(Context ctx, String title,String SubTitle, String message){

        TextView mTitle = itemView.findViewById(R.id.txttitle);
        TextView mSubTitle = itemView.findViewById(R.id.txtsubtitle);

        mTitle.setText(title);
        mSubTitle.setText(SubTitle);
    }
}
