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
import com.example.leftie.Essapp.Models.Leave;
import com.example.leftie.Essapp.Models.LeaveHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class leave_notifications extends AppCompatActivity {

    private RecyclerView leaverecyclerview;
    private leavenotificationsadapter leaveadapter;
    ArrayList<Leave> cardList;
    AlertDialog.Builder dialog;
    LinearLayoutManager mManager;
    private ProgressBar mProgressCircle;
    DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;
    ValueEventListener mDBListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_notifications);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("LEAVE REQUEST DATA");
        mDatabaseRef.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        cardList = new ArrayList<>();
        dialog = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(false);

        leaverecyclerview = findViewById(R.id.leavenotificationrecycler);
        leaverecyclerview.setHasFixedSize(true);
        leaveadapter = new leavenotificationsadapter(cardList);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mManager.setStackFromEnd(true);
        mManager.setReverseLayout(true);
        leaverecyclerview.setLayoutManager(mManager);
        leaverecyclerview.setAdapter(leaveadapter);

        mProgressCircle = findViewById(R.id.progress_circle);

        leaverecyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, leaverecyclerview, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                final Leave item = cardList.get(position);
                dialog.setTitle(item.getType());
                dialog.setMessage(item.getReason());
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
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cardList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Leave upload = postSnapshot.getValue(Leave.class);
                    upload.setKey(postSnapshot.getKey());
                    cardList.add(upload);
                }

                leaveadapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(leave_notifications.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Leave,LeaveHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Leave, LeaveHolder>(
                        Leave.class,
                        R.layout.leavenotifications_cardview,
                        LeaveHolder.class,
                        mDatabaseRef
                ) {
                    @Override
                    protected void populateViewHolder(LeaveHolder viewHolder, Leave model, int position) {
                        viewHolder.setDetails(model.getType(),model.getFrom(),model.getTo(),model.getReason());
                    }

                };
        leaverecyclerview.setAdapter(firebaseRecyclerAdapter);

    }

}
