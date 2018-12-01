package com.example.leftie.Essapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.personalinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class infofragment extends Fragment {

    EditText firstname, secondname, dob, telno, mailbox, city, myemail;
    Spinner gender;
    Button applychanges;
    DatabaseReference databasepersonalinfo;

    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);

        databasepersonalinfo = FirebaseDatabase.getInstance().getReference("PERSONAL INFO DATA");
        mAuth = FirebaseAuth.getInstance();
        firstname = view.findViewById(R.id.editname1);
        secondname = view.findViewById(R.id.editname2);
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
                registerUser();

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
    private void loadUser() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getDisplayName() != null) {
                firstname.setText(user.getDisplayName());
            }
            if (user.getEmail() != null) {
                myemail.setText(user.getEmail());
            }
        }
    }

    protected void registerUser(){

        String first_name = firstname.getText().toString().trim();
        String second_name = secondname.getText().toString().trim();
        String d_ob = dob.getText().toString().trim();
        String g_ender = gender.getSelectedItem().toString().trim();
        String tel_no = telno.getText().toString().trim();
        String box_no = mailbox.getText().toString().trim();
        String c_ity = city.getText().toString().trim();
        String my_email = myemail.getText().toString().trim();

        if (first_name.isEmpty()){
            firstname.setError("Username is Required");
            firstname.requestFocus();
            return;
        }
        if (second_name.isEmpty()){
            secondname.setError("Password is Required");
            secondname.requestFocus();
            return;
        }
        if (d_ob.isEmpty()){
            dob.setError("Password is Required");
            dob.requestFocus();
            return;
        }
        if (g_ender.isEmpty()){
            //gender.setError("Password is Required");
            gender.requestFocus();
            return;
        }
        if (tel_no.isEmpty()){
            telno.setError("Password is Required");
            telno.requestFocus();
            return;
        }
        if (box_no.isEmpty()){
            mailbox.setError("Password is Required");
            mailbox.requestFocus();
            return;
        }
        if (c_ity.isEmpty()){
            city.setError("Password is Required");
            city.requestFocus();
            return;
        }

        if (my_email.isEmpty()){
            myemail.setError("Email is Required");
            myemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(my_email).matches()){
            myemail.setError("Please Enter Valid Email");
            myemail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(my_email, second_name)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registration Successfull.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(),personalinfo.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){

                                Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
    }
}


