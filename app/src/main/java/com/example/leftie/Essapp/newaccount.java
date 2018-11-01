package com.example.leftie.Essapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newaccount extends AppCompatActivity {

    Button signbtn;
    EditText newemail, newpass;
    private FirebaseAuth FireAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);

        signbtn = (Button) findViewById(R.id.btnsignup);
        newemail = (EditText) findViewById(R.id.edtemail);
        newpass = (EditText) findViewById(R.id.editpassword);
        FireAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");


    }

    public void signup_click(View view) {

        final String username=newemail.getText().toString().trim();
        final String password=newpass.getText().toString().trim();

        final ProgressDialog progressDialog = ProgressDialog.show(newaccount.this, "Please wait...", "Processing...", true);
        (FireAuth.createUserWithEmailAndPassword(username ,password )).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){

                    Toast.makeText(newaccount.this, "Registration Successful!!! ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(newaccount.this, Home.class);
                    startActivity(i);

                    String user_id=FireAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db=databaseReference.child(user_id);
                    current_user_db.child("Name").setValue(username);

                }
                else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(newaccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
