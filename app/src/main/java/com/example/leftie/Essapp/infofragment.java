package com.example.leftie.Essapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class infofragment extends Fragment {

    EditText firstname,secondname,thirdname,jobtitle,dob,telno,phoneno,mail,mailcode,city,town,myemail,spousename,stelno,sphoneno,semail,bank,branch,account;
    Spinner gender, maritalstatus,children;
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
        jobtitle = view.findViewById(R.id.editdesignation);
        dob = view.findViewById(R.id.editdob);
        gender = view.findViewById(R.id.genderspinner);
        maritalstatus = view.findViewById(R.id.maritalspinner);
        children = view.findViewById(R.id.childspiner);
        telno = view.findViewById(R.id.edittel);
        phoneno = view.findViewById(R.id.editphone);
        mail = view.findViewById(R.id.editmail);
        mailcode = view.findViewById(R.id.editcode);
        city = view.findViewById(R.id.editcity);
        town = view.findViewById(R.id.edittown);
        myemail = view.findViewById(R.id.editemail);
        spousename = view.findViewById(R.id.editspousename);
        stelno = view.findViewById(R.id.editttel);
        sphoneno = view.findViewById(R.id.editpphone);
        semail = view.findViewById(R.id.editeemail);
        bank = view.findViewById(R.id.editbankname);
        branch = view.findViewById(R.id.editbranch);
        account = view.findViewById(R.id.editaccno);
        applychanges = view.findViewById(R.id.btnapply);


        ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(getActivity(),R.array.gender, android.R.layout.simple_spinner_item);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderadapter);

        ArrayAdapter<CharSequence> maritaladapter = ArrayAdapter.createFromResource(getActivity(),R.array.maritalstatus, android.R.layout.simple_spinner_item);
        maritaladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalstatus.setAdapter(maritaladapter);

        ArrayAdapter<CharSequence> childrenadapter = ArrayAdapter.createFromResource(getActivity(),R.array.noofchildren, android.R.layout.simple_spinner_item);
        childrenadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        children.setAdapter(childrenadapter);



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
        String third_name = thirdname.getText().toString().trim();
        String job_title = jobtitle.getText().toString().trim();
        String d_ob = dob.getText().toString().trim();
        String g_ender = gender.getSelectedItem().toString().trim();
        String c_hildren = children.getSelectedItem().toString().trim();
        String tel_no = telno.getText().toString().trim();
        String phone_no = phoneno.getText().toString().trim();
        String m_ail = mail.getText().toString().trim();
        String mail_code = mailcode.getText().toString().trim();
        String c_ity = city.getText().toString().trim();
        String t_own = town.getText().toString().trim();
        String my_email = myemail.getText().toString().trim();
        String spouse_name = spousename.getText().toString().trim();
        String stel_no = stelno.getText().toString().trim();
        String sphone_no = sphoneno.getText().toString().trim();
        String s_email = semail.getText().toString().trim();
        String b_ank = bank.getText().toString().trim();
        String b_ranch = branch.getText().toString().trim();
        String a_ccount = account.getText().toString().trim();

        if(!TextUtils.isEmpty(first_name) && (!TextUtils.isEmpty(job_title))){

            String id = databasepersonalinfo.push().getKey();
            personalinfo Personalinfo = new personalinfo(id, first_name,second_name,third_name,job_title, d_ob, g_ender ,c_hildren, tel_no, phone_no, m_ail, mail_code, c_ity, t_own, my_email, spouse_name, stel_no, sphone_no, s_email, b_ank, b_ranch, a_ccount);
            databasepersonalinfo.child(id).setValue(Personalinfo);

            Toast.makeText(getActivity(), "Your personal information has been updated successfully. Thank you.", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }



    }

}


