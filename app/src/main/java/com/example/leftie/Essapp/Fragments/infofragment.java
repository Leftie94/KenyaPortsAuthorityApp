package com.example.leftie.Essapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.personalinfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class infofragment extends Fragment {

    EditText firstname, secondname, thirdname, dob, telno, mailbox, city, myemail;
    Spinner gender;
    Button applychanges;
    DatabaseReference databasepersonalinfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);

        databasepersonalinfo = FirebaseDatabase.getInstance().getReference("PERSONAL INFO DATA");

        firstname = view.findViewById(R.id.editname1);
        secondname = view.findViewById(R.id.editname2);
        thirdname = view.findViewById(R.id.editname3);
        dob = view.findViewById(R.id.editdob);
        gender = view.findViewById(R.id.genderspinner);
        telno = view.findViewById(R.id.edittel);
        mailbox = view.findViewById(R.id.editmailbox);
        city = view.findViewById(R.id.editcity);
        myemail = view.findViewById(R.id.editemail);
        applychanges = view.findViewById(R.id.btnapply);


        ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(getActivity(),R.array.gender, android.R.layout.simple_spinner_item);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderadapter);


        applychanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addinfo();

            }
        });

        return view;
    }

    public void addinfo(){

        String first_name = firstname.getText().toString().trim();
        String second_name = secondname.getText().toString().trim();
        String d_ob = dob.getText().toString().trim();
        String g_ender = gender.getSelectedItem().toString().trim();
        String tel_no = telno.getText().toString().trim();
        String box_no = mailbox.getText().toString().trim();
        String c_ity = city.getText().toString().trim();
        String my_email = myemail.getText().toString().trim();

        if(!TextUtils.isEmpty(first_name) && (!TextUtils.isEmpty(second_name))){

            String id = databasepersonalinfo.push().getKey();
            personalinfo Personalinfo = new personalinfo(id, first_name,second_name, d_ob, g_ender , tel_no,box_no, c_ity, my_email);
            databasepersonalinfo.child(id).setValue(Personalinfo);

            Toast.makeText(getActivity(), "Your personal information has been updated successfully. Thank you.", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }



    }

}


