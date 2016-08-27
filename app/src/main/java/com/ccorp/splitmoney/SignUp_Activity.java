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
    EditText inputUsername;
    @BindView(R.id.Email)
    EditText inputEmail;
    @BindView(R.id.password)
    EditText inputPassword;
    @BindView(R.id.phone)
    EditText inputPhone;
    @BindView(R.id.confirmPassword)
    EditText inputConfirmPassword;
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
        inputUsername.addTextChangedListener(new SignUpValidation(inputUsername, this));
        inputEmail.addTextChangedListener(new SignUpValidation(inputEmail, this));
        inputPhone.addTextChangedListener(new SignUpValidation(inputPhone, this));
        inputPassword.addTextChangedListener(new SignUpValidation(inputPassword, this));
        inputConfirmPassword.addTextChangedListener(new SignUpValidation(inputConfirmPassword, this));


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
            username = inputUsername.getText().toString();
            email = inputEmail.getText().toString();
            phone = inputPhone.getText().toString();
            try {
                password = HashUtility.getSaltedHash(inputPassword.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new CreateUser().execute(fullname,username,email,phone,password);
        }
    }

    private class CreateUser extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog = new ProgressDialog(SignUp_Activity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            progressDialog.setMessage("\tLoading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                // Enter URL address where your php file resides
                url = new URL("http://splitmoney.comxa.com/DB_scripts/test.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);
                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("fullname", params[0])
                        .appendQueryParameter("username", params[1])
                        .appendQueryParameter("email", params[2])
                        .appendQueryParameter("phone", params[3])
                        .appendQueryParameter("password", params[4]);
                String query = builder.build().getEncodedQuery();
                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }
            try {
                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    if ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                        return(result.toString());
                }else{
                    return("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if(result.equalsIgnoreCase("true")){
                ToastMessage.showMessage(SignUp_Activity.this, "Hurrah!! You are ready to go...");
            }
            else{
                ToastMessage.showMessage(SignUp_Activity.this, "Something went wrong");
            }
        }
    }
}
