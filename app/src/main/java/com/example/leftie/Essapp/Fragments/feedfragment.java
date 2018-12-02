package com.example.leftie.Essapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leftie.Essapp.Adapters.feedadapter;
import com.example.leftie.Essapp.Interface.ClickListener;
import com.example.leftie.Essapp.Interface.RecyclerTouchListener;
import com.example.leftie.Essapp.Login.login;
import com.example.leftie.Essapp.Models.Upload;
import com.example.leftie.Essapp.Models.ViewHolder;
import com.example.leftie.Essapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class feedfragment extends Fragment {

    private RecyclerView mRecyclerView;
    private feedadapter mAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    ArrayList<Upload> cardList;
    AlertDialog.Builder dialog;
    private ProgressBar mProgressCircle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.feed_fragment, container, false);
           setHasOptionsMenu(true);

        cardList = new ArrayList<>();
        dialog = new AlertDialog.Builder(getActivity(),R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(false);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("FEED DATA");
        mDatabaseRef.keepSynced(true);

        mRecyclerView = view.findViewById(R.id.myrecycler);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new feedadapter(cardList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);

        mProgressCircle = view.findViewById(R.id.progress_circle);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {

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
                Toast.makeText(getActivity(), "Single Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "LONG CLICK\n" , Toast.LENGTH_SHORT).show();
            }
        }));
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cardList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    cardList.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
        return view ;

    }

    @Override
    public void onStart() {
       super.onStart();
        FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Upload, ViewHolder>(
                        Upload.class,
                        R.layout.feed_cardview,
                        ViewHolder.class,
                        mDatabaseRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Upload model, int position) {
                        viewHolder.setDetails(getActivity(),model.getTitle(),model.getSubtitle(),model.getMessage());
                    }

                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    // Inflate the menu; this adds items to the action bar.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { inflater.inflate(R.menu.menufeed, menu);
            super.onCreateOptionsMenu(menu, inflater);

    }

    // Handles action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                return true;
            case R.id.signout:
                mAuth.signOut();
                Intent i = new Intent(getActivity(),login.class);
                startActivity(i);
                return true;
            case R.id.aboutus:
                dialog.setTitle(item.getTitle());
                dialog.setMessage(getResources().getString(R.string.aboutUs));
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

}


