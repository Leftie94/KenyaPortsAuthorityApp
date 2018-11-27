package com.example.leftie.Essapp.Fragments;

import android.app.Activity;
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

import com.example.leftie.Essapp.Adapters.feedadapter;
import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.aboutus;
import com.example.leftie.Essapp.carditems;
import com.example.leftie.Essapp.Login.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class feedfragment extends Fragment {

    private RecyclerView mrecyclerview;
    private feedadapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference feeddatabase;
    private Activity mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.feed_fragment, container, false);
           setHasOptionsMenu(true);

        ArrayList<carditems> cardlist = new ArrayList<>();
        cardlist.add(new carditems("Line 1","Line 2"));

        firebaseAuth = FirebaseAuth.getInstance();
        feeddatabase = FirebaseDatabase.getInstance().getReference().child("FEED DATA");
        feeddatabase.keepSynced(true);

        mrecyclerview = view.findViewById(R.id.myrecycler);
        mrecyclerview.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getActivity());
        madapter = new feedadapter(cardlist);
        mrecyclerview.setLayoutManager(mlayoutManager);
        mrecyclerview.setAdapter(madapter);
        return view ;

    }

   // @Override
    //public void onStart() {
      //  super.onStart();
        //FirebaseRecyclerAdapter<feed,feedViewHolder>FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<feed, feedViewHolder>
          //      (feed.class,R.layout.feed_cardview,feedViewHolder.class,feeddatabase) {
            //@Override
            //protected void populateViewHolder(feedViewHolder viewHolder, feed model, int position) {

              //  viewHolder.settitle(model.getTitle());
                //viewHolder.setsubtitle(model.getSub_title());
            //}
        //};

        //mrecyclerview.setAdapter(FirebaseRecyclerAdapter);


    //}

    //public static class feedViewHolder extends RecyclerView.ViewHolder{

      //  View mView;
        //public feedViewHolder(View itemView){

          //  super(itemView);
            //mView = itemView;
        //}


        //public void settitle(String Title){

          //  TextView post_title = (TextView)mView.findViewById(R.id.txttitle);
            //post_title.setText(Title);
        //}

        //public void setsubtitle(String Sub_title){

//            TextView post_title = (TextView)mView.findViewById(R.id.txtsubtitle);
  //          post_title.setText(Sub_title);
    //    }


    //}


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
                firebaseAuth.signOut();
                Intent i = new Intent(getActivity(),login.class);
                startActivity(i);
                return true;
            case R.id.aboutus:
                Intent j = new Intent(getActivity(),aboutus.class);
                startActivity(j);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}


