package com.example.leftie.Essapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.inquiry;
import com.example.leftie.Essapp.inquiry_notifications;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class inquiriesfragment extends Fragment {

    EditText edtsubject,edtmessage;
    Spinner spinner;
    Button send;
    DatabaseReference databaseinquiries;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inquiries_fragment, container, false);
        setHasOptionsMenu(true);

        spinner = view.findViewById(R.id.editto);
        edtsubject = view.findViewById(R.id.editsubject);
        edtmessage = view.findViewById(R.id.editmessage);
        send = view.findViewById(R.id.btnsend);
        databaseinquiries = FirebaseDatabase.getInstance().getReference("INQUIRY DATA");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendfeeback();
                spinner.setSelection(0);
                edtsubject.setText(null);
                edtmessage.setText(null);
            }
        });

        return view;
    }
    private void sendfeeback(){

        String to = spinner.getSelectedItem().toString().trim();
        String subject = edtsubject.getText().toString().trim();
        String message = edtmessage.getText().toString().trim();

        if(!TextUtils.isEmpty(to) && (!TextUtils.isEmpty(message))){

            String id = databaseinquiries.push().getKey();
            inquiry feedback = new inquiry(id,to, subject,message);
            databaseinquiries.child(id).setValue(feedback);
            Toast.makeText(getActivity(), "Your inquiry has been recieved and will be responded to as soon as possible", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {inflater.inflate(R.menu.inquiries, menu);
            super.onCreateOptionsMenu(menu, inflater);

    }

    // Handle action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notfcation:
                Intent i = new Intent(getActivity(),inquiry_notifications.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
