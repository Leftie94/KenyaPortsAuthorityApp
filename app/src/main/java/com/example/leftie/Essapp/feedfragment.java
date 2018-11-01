package com.example.leftie.Essapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leftie.Essapp.R;

import java.util.ArrayList;

public class feedfragment extends Fragment {

    private RecyclerView mrecyclerview;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.feed_fragment, container, false);

        ArrayList<carditems> cardlist = new ArrayList<>();
        cardlist.add(new carditems("Line 1","Line 2"));

        mrecyclerview = view.findViewById(R.id.myrecycler);
        mrecyclerview.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this.getActivity());
        madapter = new cardadapter(cardlist);
        mrecyclerview.setLayoutManager(mlayoutManager);
        mrecyclerview.setAdapter(madapter);


        return view ;

    }


}


