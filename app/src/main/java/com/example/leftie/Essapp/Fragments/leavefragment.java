package com.example.leftie.Essapp.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leftie.Essapp.Leave;
import com.example.leftie.Essapp.R;
import com.example.leftie.Essapp.leave_notifications;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

public class leavefragment extends Fragment {

    Spinner spinleave;
    Button pickdate1, pickdate2, submit;
    TextView date1, date2;
    EditText reasonedit;
    DatePickerDialog datePickerDialog;
    DatabaseReference databaseleave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leave_fragment, container, false);
        setHasOptionsMenu(true);
        spinleave = view.findViewById(R.id.leavespinner);
        pickdate1 = view.findViewById(R.id.btndatefrom);
        pickdate2 = view.findViewById(R.id.btndateto);
        date1 = view.findViewById(R.id.txtfrom);
        date2 = view.findViewById(R.id.txtto);
        reasonedit = view.findViewById(R.id.editreason);
        submit = view.findViewById(R.id.btnsubmit);
        databaseleave = FirebaseDatabase.getInstance().getReference("LEAVE REQUEST DATA");




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.leavetype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinleave.setAdapter(adapter);





        pickdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date1.setText(day + "/" + (month + 1) + "/" + year);

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });


        pickdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date2.setText(day + "/" + (month + 1) + "/" + year);

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newleaverequest();
                spinleave.setSelection(0);
                date1.setText("Selected Date");
                date2.setText("Selected Date");
                reasonedit.getText().clear();

            }
        });


        return view;
    }

    public void newleaverequest(){

        String leavetype = spinleave.getSelectedItem().toString();
        String fromdate = date1.getText().toString().trim();
        String todate = date2.getText().toString().trim();
        String leavereason = reasonedit.getText().toString().trim();

        if(!TextUtils.isEmpty(leavetype) && (!TextUtils.isEmpty(leavereason))){

            String id = databaseleave.push().getKey();

            Leave leave= new Leave(id,leavetype,fromdate,todate,leavereason);
            databaseleave.child(id).setValue(leave);

            Toast.makeText(getActivity(), "Your request has been recieved and will be processed. Thank you.", LENGTH_LONG).show();

        }else {
            Toast.makeText(getActivity(), "Please enter the missing field.", LENGTH_LONG).show();
        }



    }


    // Inflate the menu; this adds items to the action bar.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {inflater.inflate(R.menu.menuleave, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    // Handles action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                Intent i = new Intent(getActivity(),leave_notifications.class);
                startActivity(i);
                return true;




            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
