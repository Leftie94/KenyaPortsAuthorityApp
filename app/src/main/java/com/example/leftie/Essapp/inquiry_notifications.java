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

import com.example.leftie.Essapp.Adapters.inquirynotificationadapter;
import com.example.leftie.Essapp.Interface.ClickListener;
import com.example.leftie.Essapp.Interface.RecyclerTouchListener;
import com.example.leftie.Essapp.Models.InquiryHolder;
import com.example.leftie.Essapp.Models.inquiry;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class inquiry_notifications extends AppCompatActivity {

    private RecyclerView inquiryrecyclerview;
    private inquirynotificationadapter inquiryadapter;
    ArrayList<inquiry> cardList;
    AlertDialog.Builder dialog;
    private ProgressBar mProgressCircle;
    DatabaseReference mDatabaseRef;
    ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_notifications);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("INQUIRY DATA");
        mDatabaseRef.keepSynced(true);

        cardList = new ArrayList<>();
        dialog = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(false);

        inquiryrecyclerview = findViewById(R.id.inquirynotificationrecycler);
        inquiryrecyclerview.setHasFixedSize(true);
        inquiryadapter = new inquirynotificationadapter(cardList);
        LinearLayoutManager mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        inquiryrecyclerview.setLayoutManager(mManager);
        inquiryrecyclerview.setAdapter(inquiryadapter);

        mProgressCircle = findViewById(R.id.progress_circle);

        inquiryrecyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, inquiryrecyclerview, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                final inquiry item = cardList.get(position);
                dialog.setTitle(item.getSubject());
                dialog.setMessage(item.getResponse());
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                Toast.makeText(inquiry_notifications.this, "Single Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(inquiry_notifications.this, "LONG CLICK\n" , Toast.LENGTH_SHORT).show();
            }
        }));
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cardList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    inquiry upload = postSnapshot.getValue(inquiry.class);
                    upload.setKey(postSnapshot.getKey());
                    cardList.add(upload);
                }

                inquiryadapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(inquiry_notifications.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<inquiry,InquiryHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<inquiry, InquiryHolder>(
                        inquiry.class,
                        R.layout.inquiriesnotifications_cardview,
                        InquiryHolder.class,
                        mDatabaseRef
                ) {
                    @Override
                    protected void populateViewHolder(InquiryHolder viewHolder, inquiry model, int position) {
                        viewHolder.setDetails(inquiry_notifications.this,model.getTo(),model.getSubject(),model.getMessage(),model.getResponse());
                    }

                };
        inquiryrecyclerview.setAdapter(firebaseRecyclerAdapter);

    }
}

