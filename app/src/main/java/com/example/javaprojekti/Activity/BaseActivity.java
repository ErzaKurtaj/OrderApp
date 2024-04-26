package com.example.javaprojekti.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.javaprojekti.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    public String TAG = "uilover";
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        setContentView(R.layout.activity_base);
    }
}