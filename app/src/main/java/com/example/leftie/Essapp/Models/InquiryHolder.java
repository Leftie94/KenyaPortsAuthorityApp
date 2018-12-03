package com.example.leftie.Essapp.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.leftie.Essapp.R;

public class InquiryHolder extends RecyclerView.ViewHolder {
    View mView;
    public InquiryHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(Context ctx, String to, String subject, String message){

        TextView mT0 = itemView.findViewById(R.id.txttitle);
        TextView mSubject = itemView.findViewById(R.id.txtsubtitle);

        mT0.setText("Request Confirmation");
        mSubject.setText(subject);
    }
}
