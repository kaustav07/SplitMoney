package com.ccorp.splitmoney;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
/*
    @BindView(R.id.usernameWrapper)  TextInputLayout usernameWrapper;
    @BindView(R.id.passwordWrapper)  TextInputLayout passwordWrapper;*/
    @BindView(R.id.username) EditText inputUsername;
    @BindView(R.id.password) EditText inputPassword;

    private static Context loginContext;
    ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginContext = getApplicationContext();
       /* usernameWrapper.setHint("UserName");
        passwordWrapper.setHint("Password");*/

        inputUsername.addTextChangedListener(new LoginValidation(inputUsername, this));
        inputPassword.addTextChangedListener(new LoginValidation(inputPassword,this));


    }

    public static Context getContext() {
        return loginContext;
    }

    public void SignupButton_click(View v) {
        Intent signup = new Intent(this,SignUp_Activity.class);
        startActivity(signup);
        /*DbOperations dbOperations = new DbOperations(getApplicationContext());
        EditText username = (EditText) findViewById(R.id.username);

        EditText password = (EditText) findViewById(R.id.password);
        long id = dbOperations.insertLogin(username.getText().toString(), password.getText().toString());
        if (id != 0) {
            Toast.makeText(getApplicationContext(), "UserId is" + id, Toast.LENGTH_SHORT).show();
        }*/
    }

    public void SigninButton(View v) {

        DbOperations dbOperations = new DbOperations(this);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        dbOperations.isValidUser(username.getText().toString(), password.getText().toString());
        /*if (isValid == -1)
            Toast.makeText(getApplicationContext(), "Some Database error", Toast.LENGTH_LONG).show();
        if (isValid == 1) {
            Toast.makeText(getApplicationContext(), "It is a valid user", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "It is not a valid user", Toast.LENGTH_LONG).show();
*/

    }
}
