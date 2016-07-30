package com.ccorp.splitmoney;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUp_Activity extends AppCompatActivity {

    @BindView(R.id.fullNameWrapper)  TextInputLayout fullNameWrapper;
    @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @BindView(R.id.phoneWrapper) TextInputLayout phoneWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;
    @BindView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;
    @BindView(R.id.fullName) EditText inputName;
    @BindView(R.id.Email) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.phone) EditText inputPhone;
    @BindView(R.id.confirmPassword) EditText inputConfirmPassword;
    private static Context signupContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        signupContext = getApplicationContext();
        fullNameWrapper.setHint("Full Name");
        emailWrapper.setHint("Email");
        phoneWrapper.setHint("Mobile No");
        passwordWrapper.setHint("Password");
        confirmPasswordWrapper.setHint("Confirm Password");
        inputName.addTextChangedListener(new SignUpValidation(inputName,this));
        inputEmail.addTextChangedListener(new SignUpValidation(inputEmail,this));
        inputPassword.addTextChangedListener(new SignUpValidation(inputPassword,this));
        /*inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (inputName.getText().toString().trim().isEmpty()) {
                    fullNameWrapper.setError(LoginActivity.getContext().getString(R.string.error_msg_name));
                } else {
                    fullNameWrapper.setErrorEnabled(false);
                }
            }
        });*/

    }
    public static Context getContext() {
        return signupContext;
    }


}
