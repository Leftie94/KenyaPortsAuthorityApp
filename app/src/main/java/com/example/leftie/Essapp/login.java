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


public class login extends AppCompatActivity {

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
        email = findViewById(R.id.editemail);
        password = findViewById(R.id.editpass);
        fireauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");



    }

    public void signin_click(View view){



        final ProgressDialog progressDialog = ProgressDialog.show(login.this, "Please wait...", "Processing...", true);
        (fireauth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    Toast.makeText(login.this, "Login Successful!!! ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(login.this, Home.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    checkUserExist();
                    email.getText().clear();
                    password.getText().clear();


                }


                else{

                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }



            }

        });




    }

    //checks if User exists in the database

    public void checkUserExist(){
        final String user_id=fireauth.getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user_id)){
                    Intent logintInt=new Intent(login.this,Home.class );
                    startActivity(logintInt);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }







    public void newaccount_click(View view){

        Intent i = new Intent(login.this, newaccount.class);
        startActivity(i);
    }

    public void resetpassword_click(View view){

        Intent i = new Intent(login.this, resetpassword.class);
    }


}
