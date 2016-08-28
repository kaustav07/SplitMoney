package com.ccorp.splitmoney;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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


/**
 * Created by chatt on 7/16/2016.
 */
public class DbOperations {

    boolean isValid = false;
    String userpassword = null;
    Context cnt;
    public DbOperations(Context cnt){
        this.cnt = cnt;
    }
    public void isValidUser(String username, String password) {
        this.userpassword = password;
        new AuthenticateUser(cnt).execute(username);
    }

    public void inserToDB(String fullname,String username,String email,String phone,String password){
        new CreateUser(cnt).execute(fullname,username,email,phone,password);
    }

    private class CreateUser extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog;
        HttpURLConnection conn;
        URL url = null;
        Context cntxt;
        public CreateUser(Context cntxt){
            this.cntxt = cntxt;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            progressDialog = new ProgressDialog(cntxt);
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
                ToastMessage.showMessage(cntxt, "Hurrah!! You are ready to go...");
            }
            else{
                ToastMessage.showMessage(cntxt, "Something went wrong");
            }
        }
    }

    private class AuthenticateUser extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        HttpURLConnection conn;
        URL url = null;
        Context cntxt;
        public AuthenticateUser(Context cntxt){
            this.cntxt = cntxt;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            progressDialog = new ProgressDialog(cntxt);
            progressDialog.setMessage("\tAuthenticating...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                // Enter URL address where your php file resides
                url = new URL("http://splitmoney.comxa.com/DB_scripts/Authenticate.php");

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
                        .appendQueryParameter("username", params[0]);
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
                    return(result.toString().trim());
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
            if(!result.equalsIgnoreCase("error") && !result.equalsIgnoreCase("false")){
                try {
                    if(HashUtility.check(DbOperations.this.userpassword,result)){
                        ToastMessage.showMessage(cntxt, "valid user");
                    }
                    else {
                        ToastMessage.showMessage(cntxt, "wrong password");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(result.equalsIgnoreCase("false")){
                ToastMessage.showMessage(cntxt, "not a registered user,Please sign up");
            }
            else if(result.equalsIgnoreCase("error")){
                ToastMessage.showMessage(cntxt, "Something went wrong");
            }
        }
    }


}
