package com.example.android.panduarywijaya_1202150009_modul6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private Button mlogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.txt_email);
        mPassword = (EditText) findViewById(R.id.txt_pass);
        mlogin = (Button) findViewById(R.id.btn_login);



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(MainActivity.this, Home.class) );
                }
            }
        };
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

    }

    private void SignIn() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Harap isi Email dan Password Anda", Toast.LENGTH_SHORT).show();

        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() {

                @Override

                public void onComplete(@NonNull Task task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}