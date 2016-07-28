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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class LoginActivity extends AppCompatActivity {



    private static Context applicationContext;
    ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("UserName");
        passwordWrapper.setHint("Password");
        applicationContext = getApplicationContext();

    }

    public static Context getContext() {
        return applicationContext;
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

        DbOperations dbOperations = new DbOperations(getApplicationContext());
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        int isValid = dbOperations.isValidUser(username.getText().toString(), password.getText().toString());
        if (isValid == -1)
            Toast.makeText(getApplicationContext(), "Some Database error", Toast.LENGTH_LONG).show();
        if (isValid == 1) {
            Toast.makeText(getApplicationContext(), "It is a valid user", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "It is not a valid user", Toast.LENGTH_LONG).show();


    }
}
