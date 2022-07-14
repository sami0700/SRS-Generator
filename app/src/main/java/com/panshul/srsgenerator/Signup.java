package com.panshul.srsgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText email,password,name,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup = findViewById(R.id.signUpButton);
        TextView signup1 = findViewById(R.id.signUpToLogin);
        email = findViewById(R.id.signUpEmail);
        password = findViewById(R.id.signUpPassword);
        name = findViewById(R.id.signUpName);
        mobile = findViewById(R.id.signUpMobileNumber);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mauth = FirebaseAuth.getInstance();
                mauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users");
                            UserModel  model = new UserModel(mauth.getCurrentUser().getUid(),mobile.getText().toString(),name.getText().toString(),email.getText().toString());
                            myref.child(mauth.getCurrentUser().getUid()).setValue(model);
                            startActivity(new Intent(Signup.this,Login.class));
                        }
                        else {
                            Toast.makeText(Signup.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
}