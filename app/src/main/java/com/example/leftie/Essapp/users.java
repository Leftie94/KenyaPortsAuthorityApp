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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class users extends AppCompatActivity {

    Button signbtn;
    EditText email,password;
    private FirebaseAuth fireauth;
    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        getSupportActionBar().hide();

        signbtn =(Button) findViewById(R.id.btnSignin);
        email = findViewById(R.id.edtemail);
        password = findViewById(R.id.editpass);
        fireauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");



    }

    public void signin_click(View view){

        final ProgressDialog progressDialog = ProgressDialog.show(users.this, "Please wait...", "Processing...", true);
        (fireauth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    Toast.makeText(users.this, "Login Successful!!! ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(users.this, Home.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    checkUserExist();

                }

                else {

                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(users.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }


            }

        });

    }
    public void checkUserExist(){
        final String user_id=fireauth.getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user_id)){
                    Intent logintInt=new Intent(users.this,Home.class );
                    startActivity(logintInt);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }







    public void newaccount_click(View view){

        Intent i = new Intent(users.this, newaccount.class);
        startActivity(i);
    }

    public void resetpassword_click(View view){

        Intent i = new Intent(users.this, resetpassword.class);
    }



}
