package com.example.leftie.Essapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.leftie.Essapp.Adapters.leavenotificationsadapter;
import com.example.leftie.Essapp.Models.Upload;

import java.util.ArrayList;

public class leave_notifications extends AppCompatActivity {

    private RecyclerView leaverecyclerview;
    private leavenotificationsadapter leaveadapter;
    private RecyclerView.LayoutManager leavelayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_notifications);



        final ArrayList<Upload> cardlist = new ArrayList<>();
        cardlist.add(new Upload("Leave Request","Confirmed","Your Leave Request has been Approved"));
        cardlist.add(new Upload("Leave Request","Pending","Waiting for approval"));

        leaverecyclerview = findViewById(R.id.leavenotificationrecycler);
        leaverecyclerview.setHasFixedSize(true);
        leavelayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        leaveadapter = new leavenotificationsadapter(this,cardlist);
        leaverecyclerview.setLayoutManager(leavelayoutManager);
        leaverecyclerview.setAdapter(leaveadapter);




    }

}
