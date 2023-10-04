package com.dbvetex.ecomerc_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registation extends AppCompatActivity {
    private TextView tvBack, tvLogin, edtname, edtemail, edtpass, edtnumber;
    private Button buttonsubmitreg;
    private EditText name,number,password;
    FirebaseAuth auth;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
//        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        tvBack = findViewById(R.id.tvBackRegis);
        tvLogin = findViewById(R.id.tvRegToLogin);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        buttonsubmitreg = findViewById(R.id.btnsubmitrag);
        buttonsubmitreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });

    }
    public void signin( ){
        String userName = name.getText().toString();
        String userNumber = number.getText().toString();
        String userpassword = password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "ENTER NAME", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(userNumber)){
            Toast.makeText(this, "ENTER NUMBER", Toast.LENGTH_SHORT).show();
            return;
        } if(TextUtils.isEmpty(userpassword)){
            Toast.makeText(this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpassword.length() < 6){
            Toast.makeText(this, "More then 6 number", Toast.LENGTH_SHORT).show();
          return;
        }
        auth.createUserWithEmailAndPassword (userNumber,userpassword)
                        .addOnCompleteListener(Registation.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Registation.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Registation.this,MainActivity.class));
                                }else {
                                    Toast.makeText(Registation.this, "Fail "+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        
    }
}