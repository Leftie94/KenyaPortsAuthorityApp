package com.example.leftie.Essapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.leftie.Essapp.Adapters.inquirynotificationadapter;
import com.example.leftie.Essapp.Models.Upload;

import java.util.ArrayList;

public class inquiry_notifications extends AppCompatActivity {

    private RecyclerView inquiryrecyclerview;
    private inquirynotificationadapter inquiryadapter;
    private RecyclerView.LayoutManager inquirylayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_notifications);

        ArrayList<Upload> cardlist = new ArrayList<>();
        cardlist.add(new Upload("Request Confirmation","Confirmed","Your Request for ??? has been Approved"));
        cardlist.add(new Upload("Request Confirmation","Pending","Waiting for approval"));

        inquiryrecyclerview = findViewById(R.id.inquirynotificationrecycler);
        inquiryrecyclerview.setHasFixedSize(true);
        inquirylayoutManager = new LinearLayoutManager(this);
        inquiryadapter = new inquirynotificationadapter(this,cardlist);
        inquiryrecyclerview.setLayoutManager(inquirylayoutManager);
        inquiryrecyclerview.setAdapter(inquiryadapter);


    }


}
