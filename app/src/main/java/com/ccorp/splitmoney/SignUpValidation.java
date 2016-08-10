package com.ccorp.splitmoney;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaustav on 7/30/2016.
 */
public class SignUpValidation implements TextWatcher{

    @BindView(R.id.fullName) MaterialEditText inputName;
    @BindView(R.id.username) MaterialEditText inputUsername;
    @BindView(R.id.Email) MaterialEditText inputEmail;
    @BindView(R.id.password) MaterialEditText inputPassword;
    @BindView(R.id.phone) MaterialEditText inputPhone;
    @BindView(R.id.confirmPassword) MaterialEditText inputConfirmPassword;
   /* @BindView(R.id.fullNameWrapper) TextInputLayout fullNameWrapper;*/
//    @BindView(R.id.usernameWrapper) TextInputLayout usernameWrapper;
   /* @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
    @BindView(R.id.phoneWrapper) TextInputLayout phoneWrapper;*/
//    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;
//    @BindView(R.id.confirmPasswordWrapper) TextInputLayout confirmPasswordWrapper;

    static boolean flag_name=false,flag_username=false,flag_email=false,flag_phone=false,flag_password=false, flag_confirmPassword=false;

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
                validateName();
                break;
            case R.id.username:
                validateUsername();
                break;
            case R.id.Email:
                validateEmail();
                break;
            case R.id.phone:
                validatePhone();
            case R.id.password:
                validatePassword();
                break;
            case R.id.confirmPassword:
                validateConfirmPassword();
                break;

        }
    }

    private boolean validateName() {
        /*if (inputName.getText().toString().trim().isEmpty()) {
            inputName.setError(LoginActivity.getContext().getString(R.string.error_msg_name));
            inputName.setErrorColor(R.color.errorColor);
            return false;
        }*/
        if(inputName.getText().length()<3){
            inputName.setError("Min length should be always 3");
            inputName.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_name=true;

        }

        if(inputName.getText().toString().trim().isEmpty())
        {
            inputName.setError(" ");
            inputName.setErrorColor(Color.parseColor("#0056d3"));
            flag_name=true;
            //return false;

        }

        if(!(inputName.getText().length()<3)&& !(inputName.getText().toString().trim().isEmpty()))
        {
            flag_name=false;
        }

        return flag_name;
    }

    public boolean validateUsername() {
        String username = inputUsername.getText().toString().trim();

        if (!isValidUsername(username)) {
            inputUsername.setError(LoginActivity.getContext().getString(R.string.error_invalid_username));
            inputUsername.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_username = true;
            //requestFocus(inputUsername);

        }

        if(username.isEmpty())
        {
            inputUsername.setError(" ");
            inputUsername.setErrorColor(Color.parseColor("#0056d3"));
            flag_username=true;
            //return false;

        }

        if(isValidUsername(username) && !(username.isEmpty()))
        {
            flag_username=false;
        }

        return flag_username;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (!isValidEmail(email)) {
            inputEmail.setError(LoginActivity.getContext().getString(R.string.error_invalid_email));
            inputEmail.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            //requestFocus(inputEmail);
            flag_email=true;

        }

        if(email.isEmpty())
        {
            inputEmail.setError(" ");
            inputEmail.setErrorColor(Color.parseColor("#0056d3"));
            flag_email=true;
            //return false;
        }

        if(isValidEmail(email))
        {
            flag_email=false;
        }

        return flag_email;    }




    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (!isValidPhone(phone)) {
            inputPhone.setError(LoginActivity.getContext().getString(R.string.error_invalid_phone));
            inputPhone.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_phone=true;

        }

        if(phone.isEmpty())
        {
            inputPhone.setError(" ");
            inputPhone.setErrorColor(Color.parseColor("#0056d3"));
            flag_phone=true;
           // return false;
        }
        if(isValidPhone(phone))
        {
            flag_phone=false;
        }

        return flag_phone;    }

    private boolean validatePassword() {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();
        if (password.length()<6 ) {
            inputPassword.setError(LoginActivity.getContext().getString(R.string.error_invalid_password));
            inputPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            //requestFocus(inputPassword);
            flag_password=true;

        }

        if(password.isEmpty())
        {
            inputPassword.setError(" ");
            inputPassword.setErrorColor(Color.parseColor("#0056d3"));
            flag_password=true;
            //return false;
        }

        if(!(password.length()<6) && !(password.isEmpty()))
        {
            flag_password=false;
        }


        return flag_password;
    }

    private boolean validateConfirmPassword() {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();
        if (inputConfirmPassword.length()<6  || !isPasswordMatching(password,confirmPassword)) {
            inputConfirmPassword.setError("Error in confirm password");
            inputConfirmPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_confirmPassword=true;
            //requestFocus(inputConfirmPassword);

            }
        if(confirmPassword.isEmpty())
        {
            inputConfirmPassword.setError(" ");
            inputConfirmPassword.setErrorColor(Color.parseColor("#0056d3"));
            flag_confirmPassword=true;
            //return false;
        }

        if(!(inputConfirmPassword.length()<6) && isPasswordMatching(password,confirmPassword) && !(confirmPassword.isEmpty()))
        {
            flag_confirmPassword=false;
        }


            return flag_confirmPassword;

    }

    private static boolean isValidUsername(String username) {
        if(username.length()<3){
            return false;
        }

        else
            return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            //Toast.makeText(getApplicationContext(),"Confirm password is not matching with password", Toast.LENGTH_LONG).show();
            ToastMessage tm = new ToastMessage();
            tm.showMessage(activity, "Confirm password is not matching with Password!");

            return false;
        }

        return true;
    }





    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



}
