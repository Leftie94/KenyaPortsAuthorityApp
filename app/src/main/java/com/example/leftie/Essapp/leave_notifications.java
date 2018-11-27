package com.example.leftie.Essapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.leftie.Essapp.Adapters.leavenotificationsadapter;

import java.util.ArrayList;

public class leave_notifications extends AppCompatActivity {

    private RecyclerView leaverecyclerview;
    private leavenotificationsadapter leaveadapter;
    private RecyclerView.LayoutManager leavelayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_notifications);



        final ArrayList<carditems> cardlist = new ArrayList<>();
        cardlist.add(new carditems("Line 1","Line 2"));

        leaverecyclerview = findViewById(R.id.leavenotificationrecycler);
        leaverecyclerview.setHasFixedSize(true);
        leavelayoutManager = new LinearLayoutManager(this);
        leaveadapter = new leavenotificationsadapter(cardlist);
        leaverecyclerview.setLayoutManager(leavelayoutManager);
        leaverecyclerview.setAdapter(leaveadapter);


        leaveadapter.setOnItemClickListener(new leavenotificationsadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent i = new Intent(leave_notifications.this, leavenotificationmsg.class);
                startActivity(i);

            }
        });


    }

}
