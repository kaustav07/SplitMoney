package com.ccorp.splitmoney;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final TextInputLayout fullNameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        final TextInputLayout phoneWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        final TextInputLayout confirmPasswordWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
    }
}
