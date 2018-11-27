package com.example.leftie.Essapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.leftie.Essapp.Adapters.inquirynotificationadapter;

import java.util.ArrayList;

public class inquiry_notifications extends AppCompatActivity {

    private RecyclerView inquiryrecyclerview;
    private inquirynotificationadapter inquiryadapter;
    private RecyclerView.LayoutManager inquirylayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_notifications);

        ArrayList<carditems> cardlist = new ArrayList<>();
        cardlist.add(new carditems("Request Confirmation","Confirmed","Leo ni leo"));
        cardlist.add(new carditems("Request Confirmation","Pending","Leo ni leo"));

        inquiryrecyclerview = findViewById(R.id.inquirynotificationrecycler);
        inquiryrecyclerview.setHasFixedSize(true);
        inquirylayoutManager = new LinearLayoutManager(this);
        inquiryadapter = new inquirynotificationadapter(cardlist);
        inquiryrecyclerview.setLayoutManager(inquirylayoutManager);
        inquiryrecyclerview.setAdapter(inquiryadapter);


    }


}
