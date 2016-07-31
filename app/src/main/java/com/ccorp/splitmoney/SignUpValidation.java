package com.ccorp.splitmoney;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaustav on 7/30/2016.
 */
public class SignUpValidation implements TextWatcher{

    @BindView(R.id.fullName) EditText inputName;
    @BindView(R.id.username) EditText inputUsername;
    @BindView(R.id.Email) EditText inputEmail;
    @BindView(R.id.password) EditText inputPassword;
    @BindView(R.id.phone) EditText inputPhone;
    @BindView(R.id.confirmPassword) EditText inputConfirmPassword;
    @BindView(R.id.fullNameWrapper) TextInputLayout fullNameWrapper;
    @BindView(R.id.usernameWrapper) TextInputLayout usernameWrapper;
    @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @BindView(R.id.phoneWrapper) TextInputLayout phoneWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;
    @BindView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;



    private View view;
    Activity activity;

    SignUpValidation(View view,Activity _activity) {
        this.view = view;
        this.activity = _activity;
        ButterKnife.bind(this,this.activity);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
        switch (view.getId()) {
            case R.id.fullName:
                validateName((EditText)view);
                break;
            case R.id.username:
                validateUsername((EditText)view);
                break;
            case R.id.Email:
                validateEmail((EditText)view);
                break;
            case R.id.phone:
                validatePhone((EditText)view);
            case R.id.password:
                validatePassword((EditText)view);
                break;
            case R.id.confirmPassword:
                validateConfirmPassword((EditText)view);
                break;

        }
    }

    private boolean validateName(EditText inputName) {
        if (inputName.getText().toString().trim().isEmpty()) {
            fullNameWrapper.setError(LoginActivity.getContext().getString(R.string.error_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            fullNameWrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateUsername(EditText inputUsername) {
        String username = inputUsername.getText().toString().trim();

        if (username.isEmpty() || !isValidUsername(username)) {
            usernameWrapper.setError(LoginActivity.getContext().getString(R.string.error_invalid_username));
            requestFocus(inputUsername);
            return false;
        } else {
            usernameWrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail(EditText inputEmail) {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailWrapper.setError(LoginActivity.getContext().getString(R.string.error_invalid_email));
            requestFocus(inputEmail);
            return false;
        } else {
            emailWrapper.setErrorEnabled(false);
        }

        return true;    }




    private boolean validatePhone(EditText inputPhone) {
        String phone = inputPhone.getText().toString().trim();

        if (phone.isEmpty() || !isValidPhone(phone)) {
            phoneWrapper.setError(LoginActivity.getContext().getString(R.string.error_invalid_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            phoneWrapper.setErrorEnabled(false);
        }

        return true;    }

    private boolean validatePassword(EditText inputPassword) {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            passwordWrapper.setError(LoginActivity.getContext().getString(R.string.error_invalid_password));
            requestFocus(inputPassword);
            return false;
        }
        else {
            passwordWrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPassword(EditText inputConfirmPassword) {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();
        if (inputConfirmPassword.getText().toString().trim().isEmpty()|| (password != confirmPassword)) {
            confirmPasswordWrapper.setError(LoginActivity.getContext().getString(R.string.error_incorrect_confirmPassword));
            requestFocus(inputConfirmPassword);
            return false;
        }
        else {
            confirmPasswordWrapper.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) ;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && android.util.Patterns.PHONE.matcher(phone).matches();
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



}
