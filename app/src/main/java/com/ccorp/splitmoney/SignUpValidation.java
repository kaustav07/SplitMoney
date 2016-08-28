package com.ccorp.splitmoney;

import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

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
    @BindView(R.id.btn_signup) Button inputButton;


    static boolean flag_fullName =false,flag_username=false,flag_email=false,flag_phone=false,flag_password=false, flag_confirmPassword=false;

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


        enableSubmitIfReady();
    }

    private boolean validateName() {


        if(inputName.getText().length()<3){
            inputName.setError("Min length should be always 3");
            inputName.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_fullName =false;

        }

        if(inputName.getText().toString().trim().isEmpty())
        {
            inputName.setError(" ");
            inputName.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_fullName =false;

        }

        if(inputName.getText().length()>=3)
        {
            inputName.setError(null);
            flag_fullName =true;

        }
        return flag_fullName;
    }

    public boolean validateUsername() {
        String username = inputUsername.getText().toString().trim();

        if (!isValidUsername(username)) {
            inputUsername.setError(SignUp_Activity.getContext().getString(R.string.error_invalid_username));
            inputUsername.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_username = false;
        }

        if(username.isEmpty())
        {
            inputUsername.setError(" ");
            inputUsername.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_username=false;
        }

        if(isValidUsername(username))
        {
            inputUsername.setError(null);
            flag_username=true;
        }
        return flag_username;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (!isValidEmail(email)) {
            inputEmail.setError(SignUp_Activity.getContext().getString(R.string.error_invalid_email));
            inputEmail.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            //requestFocus(email);
            flag_email=false;
        }

        if(email.isEmpty())
        {
            inputEmail.setError(" ");
            inputEmail.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_email=false;
            //return false;
        }
        if(isValidEmail(email))
        {
            inputEmail.setError(null);
            flag_email=true;
        }



        return flag_email;    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();



        if (!isValidPhone(phone)) {
            inputPhone.setError(SignUp_Activity.getContext().getString(R.string.error_invalid_phone));
            inputPhone.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_phone=false;

        }
        if(phone.isEmpty())
        {
            inputPhone.setError(" ");
            inputPhone.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_phone=false;
            // return false;
        }

        if(isValidPhone(phone))
        {
            inputPhone.setError(null);
            flag_phone=true;
        }



        return flag_phone;    }

    private boolean validatePassword() {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();


        if (password.length()<6 ) {
            inputPassword.setError(SignUp_Activity.getContext().getString(R.string.error_invalid_password));
            inputPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            //requestFocus(password);
            flag_password=false;

        }
        if(password.isEmpty())
        {
            inputPassword.setError(" ");
            inputPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_password=false;
            //return false;
        }


        if(password.length()>=6)
        {
            inputPassword.setError(null);
            flag_password=true;
        }




        return flag_password;
    }

    private boolean validateConfirmPassword() {
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputConfirmPassword.getText().toString().trim();


        if (inputConfirmPassword.length()<6  || !isPasswordMatching(password,confirmPassword)) {
            inputConfirmPassword.setError("Error in confirm password");
            inputConfirmPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.errorcolor)));
            flag_confirmPassword=false;
            //requestFocus(confirmPassword);

            }

        if(confirmPassword.isEmpty())
        {
            inputConfirmPassword.setError(" ");
            inputConfirmPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            flag_confirmPassword=false;
            //return false;
        }

        if((inputConfirmPassword.length()>=6) && isPasswordMatching(password,confirmPassword))
        {
            inputConfirmPassword.setError(null);
            flag_confirmPassword=true;
        }




            return flag_confirmPassword;

    }

    public void enableSubmitIfReady() {

        if (!SignUpValidation.flag_fullName ||!SignUpValidation.flag_username||!SignUpValidation.flag_email||!SignUpValidation.flag_phone||!SignUpValidation.flag_password||!SignUpValidation.flag_confirmPassword) {
            inputButton.setEnabled(false);

        }

        if(SignUpValidation.flag_fullName && SignUpValidation.flag_username && SignUpValidation.flag_email && SignUpValidation.flag_phone && SignUpValidation.flag_password && SignUpValidation.flag_confirmPassword) {
            inputButton.setEnabled(true);

        }


    }

    private static boolean isValidUsername(String username) {
        if(username.length()<3){
            return false;
        }

        else
            return true;
    }

    private static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            //Toast.makeText(getApplicationContext(),"Confirm password is not matching with password", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
