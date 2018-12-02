package com.example.leftie.Essapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leftie.Essapp.Adapters.leavenotificationsadapter;
import com.example.leftie.Essapp.Interface.ClickListener;
import com.example.leftie.Essapp.Interface.RecyclerTouchListener;
import com.example.leftie.Essapp.Models.Upload;

import java.util.ArrayList;

public class leave_notifications extends AppCompatActivity {

    private RecyclerView leaverecyclerview;
    private leavenotificationsadapter leaveadapter;
    ArrayList<Upload> cardList;
    AlertDialog.Builder dialog;
    private ProgressBar mProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_notifications);

        cardList = new ArrayList<>();
        dialog = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(false);
        cardList.add(new Upload("Leave Request","Confirmed","Your Leave Request has been Approved"));
        cardList.add(new Upload("Leave Request","Pending","Waiting for approval"));

        leaverecyclerview = findViewById(R.id.leavenotificationrecycler);
        leaverecyclerview.setHasFixedSize(true);
        leaveadapter = new leavenotificationsadapter(cardList);
        leaverecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        leaverecyclerview.setAdapter(leaveadapter);

        mProgressCircle = findViewById(R.id.progress_circle);

        leaverecyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, leaverecyclerview, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                final Upload item = cardList.get(position);
                dialog.setTitle(item.getSubtitle());
                dialog.setMessage(item.getMessage());
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                Toast.makeText(leave_notifications.this, "Single Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(leave_notifications.this, "LONG CLICK\n" , Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
