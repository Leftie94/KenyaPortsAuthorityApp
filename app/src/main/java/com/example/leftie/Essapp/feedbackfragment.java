package com.example.leftie.Essapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class feedbackfragment extends Fragment {

    EditText edtto,edtsubject,edtmessage;
    Button send;
    DatabaseReference databasefeedback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_fragment, container, false);

        edtto = view.findViewById(R.id.editto);
        edtsubject = view.findViewById(R.id.editsubject);
        edtmessage = view.findViewById(R.id.editmessage);
        send = view.findViewById(R.id.btnsend);
        databasefeedback = FirebaseDatabase.getInstance().getReference("FEEDBACK");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendfeeback();

            }
        });

        return view;
    }
    private void sendfeeback(){

        String to = edtto.getText().toString().trim();
        String subject = edtsubject.getText().toString().trim();
        String message = edtmessage.getText().toString().trim();

        if(!TextUtils.isEmpty(to) && (!TextUtils.isEmpty(message))){

            String id = databasefeedback.push().getKey();
            Feedback feedback = new Feedback(id,to, subject,message);
            databasefeedback.child(id).setValue(feedback);
            Toast.makeText(getActivity(), "Your feedback has been recieved and will be responded to as soon as possible", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }

    }
}
