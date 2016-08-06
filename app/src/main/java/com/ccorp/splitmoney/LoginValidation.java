package com.ccorp.splitmoney;

/**
 * Created by Jojo on 7/31/2016.
 */

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


public class LoginValidation implements TextWatcher{
    @BindView(R.id.username) EditText inputUsername;
    @BindView(R.id.password) EditText inputPassword;
   /* @BindView(R.id.usernameWrapper) TextInputLayout usernameWrapper;
    @BindView(R.id.passwordWrapper) TextInputLayout passwordWrapper;*/


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
                validateUsername((EditText)view);
                break;
            case R.id.password:
                validatePassword((EditText)view);
                break;

        }
    }

    private boolean validateUsername(EditText inputUsername) {
        String username = inputUsername.getText().toString().trim();

        if (username.isEmpty() || !isValidUsername(username)) {
            inputUsername.setError(LoginActivity.getContext().getString(R.string.error_invalid_username));
            requestFocus(inputUsername);
            return false;
        }

        return true;
    }


    private boolean validatePassword(EditText inputPassword) {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputPassword.setError(LoginActivity.getContext().getString(R.string.error_invalid_password));
            requestFocus(inputPassword);
            return false;
        }


        return true;
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
