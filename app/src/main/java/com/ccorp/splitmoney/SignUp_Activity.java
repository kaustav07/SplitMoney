package com.ccorp.splitmoney;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUp_Activity extends AppCompatActivity {

    /*@BindView(R.id.fullNameWrapper)  TextInputLayout fullNameWrapper;
    @BindView(R.id.usernameWrapper)  TextInputLayout usernameWrapper;
    @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @BindView(R.id.phoneWrapper) TextInputLayout phoneWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;*/
/*    @BindView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;*/
    @BindView(R.id.fullName)
    EditText fullName;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.Email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.confirmPassword)
    EditText confirmPassword;
    @BindView(R.id.btn_signup)
    Button signUpButton;
    private static Context signupContext;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupWindowAnimations();
        ButterKnife.bind(this);
        signupContext = getApplicationContext();
        /*fullNameWrapper.setHint("Full Name");
        usernameWrapper.setHint("Username");
        emailWrapper.setHint("Email");
        phoneWrapper.setHint("Mobile No");
        passwordWrapper.setHint("Password");*/
        // confirmPasswordWrapper.setHint("Confirm Password");
        fullName.addTextChangedListener(new SignUpValidation(fullName, this));
        username.addTextChangedListener(new SignUpValidation(username, this));
        email.addTextChangedListener(new SignUpValidation(email, this));
        phone.addTextChangedListener(new SignUpValidation(phone, this));
        password.addTextChangedListener(new SignUpValidation(password, this));
        confirmPassword.addTextChangedListener(new SignUpValidation(confirmPassword, this));


    }

    private void setupWindowAnimations() {
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade_in);
        getWindow().setEnterTransition(fade);
    }

    public static Context getContext() {
        return signupContext;
    }

    public void btnSignUp_Click(View v) {

        if (!SignUpValidation.flag_fullName ||!SignUpValidation.flag_username||!SignUpValidation.flag_email||!SignUpValidation.flag_phone||!SignUpValidation.flag_password||!SignUpValidation.flag_confirmPassword) {
            ToastMessage.showMessage(this, "Please Fix the input");

        }
        if(SignUpValidation.flag_fullName && SignUpValidation.flag_username && SignUpValidation.flag_email && SignUpValidation.flag_phone && SignUpValidation.flag_password && SignUpValidation.flag_confirmPassword) {

//            ToastMessage.showMessage(SignUp_Activity.this, "Hurrah!! You are ready to go...");
            String fullname,username,email,phone,password = null;
            fullname = fullName.getText().toString();
            username = this.username.getText().toString();
            email = this.email.getText().toString();
            phone = this.phone.getText().toString();
            try {
                password = HashUtility.getSaltedHash(this.password.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            DbOperations db = new DbOperations(this);
            db.inserToDB(fullname,username,email,phone,password);
        }
    }


}
