package com.example.leftie.Essapp.Models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.leftie.Essapp.R;

public class LeaveHolder extends RecyclerView.ViewHolder {
    View mView;
    public LeaveHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(String type, String from, String to, String reason){

        TextView mType = itemView.findViewById(R.id.txttitle);
        TextView mDate = itemView.findViewById(R.id.txtsubtitle);

        mType.setText(type);
        mDate.setText(from + to);
    }
}
