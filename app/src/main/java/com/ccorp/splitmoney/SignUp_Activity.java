package com.ccorp.splitmoney;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUp_Activity extends AppCompatActivity {

    /*@BindView(R.id.fullNameWrapper)  TextInputLayout fullNameWrapper;
    @BindView(R.id.usernameWrapper)  TextInputLayout usernameWrapper;
    @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @BindView(R.id.phoneWrapper) TextInputLayout phoneWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;*/
/*    @BindView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;*/
    @BindView(R.id.fullName) EditText inputName;
    @BindView(R.id.username) EditText inputUsername;
    @BindView(R.id.Email) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.phone) EditText inputPhone;
    @BindView(R.id.confirmPassword) EditText inputConfirmPassword;
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
        inputName.addTextChangedListener(new SignUpValidation(inputName,this));
        inputUsername.addTextChangedListener(new SignUpValidation(inputUsername,this));
        inputEmail.addTextChangedListener(new SignUpValidation(inputEmail,this));
        inputPhone.addTextChangedListener(new SignUpValidation(inputPhone,this));
        inputPassword.addTextChangedListener(new SignUpValidation(inputPassword,this));
        inputConfirmPassword.addTextChangedListener(new SignUpValidation(inputConfirmPassword,this));
        addListenerOnButton();


    }

    private void setupWindowAnimations() {
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade_in);
        getWindow().setEnterTransition(fade);
    }
    public static Context getContext() {
        return signupContext;
    }



    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_signup:

                        if(SignUpValidation.flag_name==true || SignUpValidation.flag_username==true || SignUpValidation.flag_email==true || SignUpValidation.flag_phone==true || SignUpValidation.flag_password==true || SignUpValidation.flag_confirmPassword==true)
                        {
                            ToastMessage tm = new ToastMessage();
                            tm.showMessage(SignUp_Activity.this, "Please Fix the input");
                        }

                        else
                        {

                            ToastMessage tm = new ToastMessage();
                            tm.showMessage(SignUp_Activity.this, "Hurrah!! You are ready to go...");


                        }
                        break;

                }

            }

        });

    }
}
