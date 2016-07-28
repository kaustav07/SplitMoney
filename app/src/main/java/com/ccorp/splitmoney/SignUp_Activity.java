package com.ccorp.splitmoney;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.*;


public class SignUp_Activity extends AppCompatActivity {

    @InjectView(R.id.fullNameWrapper) TextInputLayout fullNameWrapper;
    @InjectView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @InjectView(R.id.phoneWrapper) TextInputLayout phoneWrapper;
    @InjectView(R.id.passwordWrapper) TextInputLayout passwordWrapper;
    @InjectView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameWrapper.setHint("Full Name");
        emailWrapper.setHint("Email");
        phoneWrapper.setHint("Mobile No");
        passwordWrapper.setHint("Password");
        confirmPasswordWrapper.setHint("Confirm Password");
    }
}
