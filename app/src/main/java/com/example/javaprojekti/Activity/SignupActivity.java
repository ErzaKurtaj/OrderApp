package com.example.javaprojekti.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.javaprojekti.R;
import com.example.javaprojekti.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private static final String TAG = "SignupActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        setVariable();
    }

    private void setVariable() {
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEdt.getText().toString();
                String password = binding.passEdt.getText().toString();

                if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Your password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Log.i(TAG, "onComplete: User registration successful");
                            startActivity(new Intent(SignupActivity.this,MainActivity.class));
                            Toast.makeText(SignupActivity.this, "User registration successful", Toast.LENGTH_SHORT).show();
                            // You can add further actions here, such as navigating to another activity
                        } else {
                            Log.e(TAG, "onComplete: User registration failed", task.getException());
                            Toast.makeText(SignupActivity.this, "User registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
