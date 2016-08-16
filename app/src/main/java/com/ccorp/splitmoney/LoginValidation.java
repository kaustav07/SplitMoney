package com.ccorp.splitmoney;

/**
 * Created by Jojo on 7/31/2016.
 */

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginValidation implements TextWatcher{
    @BindView(R.id.username) MaterialEditText inputUsername;
    @BindView(R.id.password) MaterialEditText inputPassword;
   /* @BindView(R.id.usernameWrapper) TextInputLayout usernameWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;*/
    static boolean flag_Login_username=false, flag_Login_Password=false;

    private View view;
    Activity activity;

    LoginValidation(View view,Activity _activity) {
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
            case R.id.username:
                validateUsername();
                break;
            case R.id.password:
                validatePassword();
                break;

        }
    }

    private boolean validateUsername() {
        String username = inputUsername.getText().toString().trim();

        if (username.isEmpty()) {
            inputUsername.setError(" ");
            inputUsername.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            //requestFocus(inputUsername);
            flag_Login_Password=false;
        }

        return flag_Login_username;
    }


    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputPassword.setError(" ");
            inputPassword.setErrorColor(Color.parseColor(this.activity.getString(R.string.basecolor)));
            //requestFocus(inputPassword);
            flag_Login_Password=false;
        }


        return flag_Login_Password;
    }

    private static boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) ;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
