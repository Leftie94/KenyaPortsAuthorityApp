package com.example.leftie.Essapp.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leftie.Essapp.home;
import com.example.leftie.Essapp.R;
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
        newemail = (EditText) findViewById(R.id.editemail);
        newpass = (EditText) findViewById(R.id.editpassword);
        FireAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

    }

    public void signup_click(View view) {

        final String email=newemail.getText().toString().trim();
        final String password=newpass.getText().toString().trim();


        if (email.isEmpty()){
            newemail.setError("Email is Required");
            newemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            newemail.setError("Please Enter Valid Email");
            newemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            newpass.setError("Password is Required");
            newpass.requestFocus();
            return;
        }
        if (password.length()<6){
            newpass.setError("Minimum length should be 6 characters");
            newpass.requestFocus();
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(newaccount.this, "Please wait...", "Processing...", true);
        (FireAuth.createUserWithEmailAndPassword(email ,password )).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    Toast.makeText(newaccount.this, "Registration Successful!!! ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(newaccount.this, home.class);
                    startActivity(i);
                }
                else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(newaccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
