package com.example.leftie.Essapp.Fragments;

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
import com.example.leftie.Essapp.PersonInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_LONG;

public class infofragment extends Fragment {

    EditText firstname, secondname, dob, telno, mailbox, city, myemail;
    Spinner gender;
    Button applychanges;
    DatabaseReference mDbRef;
    FirebaseDatabase database;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);

        mDbRef = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        firstname = view.findViewById(R.id.editname1);
        secondname = view.findViewById(R.id.editname2);
        dob = view.findViewById(R.id.editdob);
        gender = view.findViewById(R.id.genderspinner);
        telno = view.findViewById(R.id.edittel);
        mailbox = view.findViewById(R.id.editmailbox);
        city = view.findViewById(R.id.editcity);
        myemail = view.findViewById(R.id.editemail);
        progressBar = view.findViewById(R.id.progress_circle);
        applychanges = view.findViewById(R.id.btnapply);
        progressBar.setVisibility(View.VISIBLE);

        applychanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo();

            }
        });
        loadUser();
        return view;
    }

    public void addInfo(){

        String first_name = firstname.getText().toString().trim();
        String second_name = secondname.getText().toString().trim();
        String d_ob = dob.getText().toString().trim();
        String g_ender = gender.getSelectedItem().toString().trim();
        String tel_no = telno.getText().toString().trim();
        String box_no = mailbox.getText().toString().trim();
        String c_ity = city.getText().toString().trim();
        String my_email = myemail.getText().toString().trim();

        if (first_name.isEmpty()){
            firstname.setError("First Name is Required");
            firstname.requestFocus();
            return;
        }
        if (second_name.isEmpty()){
            secondname.setError("Second Name is Required");
            secondname.requestFocus();
            return;
        }
        if (d_ob.isEmpty()){
            dob.setError("Date of Birth is Required");
            dob.requestFocus();
            return;
        }
        if (g_ender.isEmpty()){
            //gender.setError("Password is Required");
            gender.requestFocus();
            return;
        }
        if (tel_no.isEmpty()){
            telno.setError("Tel Number is Required");
            telno.requestFocus();
            return;
        }
        if (box_no.isEmpty()){
            mailbox.setError("Box Number is Required");
            mailbox.requestFocus();
            return;
        }
        if (c_ity.isEmpty()){
            city.setError("City is Required");
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
        if(!TextUtils.isEmpty(first_name) && (!TextUtils.isEmpty(second_name))){


            String email = mAuth.getCurrentUser().getUid();
            PersonInfo Info = new PersonInfo(first_name,second_name, d_ob, g_ender , tel_no,box_no, c_ity, my_email);
            mDbRef.child(email).setValue(Info);
            progressBar.setVisibility(View.GONE);

            Toast.makeText(getActivity(), "Your personal information has been updated successfully.\n Thank you.", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }

    }
    private void loadUser() {
        final FirebaseUser user = mAuth.getCurrentUser();

        /*String user_id= mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = databaseReference.child(user_id);
        current_user_db.child("Name").setValue(email);*/
        if (user != null) {
            progressBar.setVisibility(View.INVISIBLE);
            if (user.getDisplayName() != null) {
                firstname.setText(user.getDisplayName());
            }
            if (user.getEmail() != null) {
                myemail.setText(user.getEmail());
            }

            database = FirebaseDatabase.getInstance();
            DatabaseReference first_name = database.getReference("Users").child(user.getUid()).child("first_name");
            DatabaseReference second_name = database.getReference("Users").child(user.getUid()).child("second_name");
            DatabaseReference d_ob = database.getReference("Users").child(user.getUid()).child("dob");
            DatabaseReference g_ender = database.getReference("Users").child(user.getUid()).child("gender");
            DatabaseReference tel = database.getReference("Users").child(user.getUid()).child("tel");
            DatabaseReference mail = database.getReference("Users").child(user.getUid()).child("mail");
            DatabaseReference c_ity = database.getReference("Users").child(user.getUid()).child("city");
            first_name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    firstname.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            second_name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    secondname.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            d_ob.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    dob.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            g_ender.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    if(value.equals("Male")){
                        gender.setSelection(1);

                    }else{
                        gender.setSelection(2);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            tel.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    telno.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            mail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    mailbox.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            c_ity.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    city.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }

    }
}


