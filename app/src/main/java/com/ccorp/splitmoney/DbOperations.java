package com.ccorp.splitmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by chatt on 7/16/2016.
 */
public class DbOperations {

    private static SQLiteDatabase mydb;

    public DbOperations(Context context) {
        mydb = new DbHelper(context).getWritableDatabase();
    }

    public long insertLogin(String username, String password) {
        long value = 0;
        ContentValues values = new ContentValues();
        values.put(Table_Login_Details.COLUMN_USERNAME, username);
        values.put(Table_Login_Details.COLUMN_PASSWORD, password);
        try {
            value = mydb.insertOrThrow(Table_Login_Details.TABLE_NAME, null, values);
        }
        catch (SQLException e){
            Log.d("DbError",e.getMessage());
            if(e.getMessage().contains("UserName"))
                ToastMessage.showMessage(LoginActivity.getContext(),"Username already exists, Please try to login again");


        }
        return value;
    }

    public static int isValidUser(String username, String password) {
        int key;
        Cursor cur = null;
        try {
            cur = mydb.query(Table_Login_Details.TABLE_NAME, new String[]{Table_Login_Details.COLUMN_PASSWORD}, Table_Login_Details.COLUMN_USERNAME + "='"+username+"'", null, null, null, null);
        }
        catch (Exception e){
            Log.e("DbErrormsg",e.getMessage());
            Log.e("DbStackTrace",e.getStackTrace().toString());
            e.printStackTrace();
        }
        if (cur.getCount() == 0) {
            key = -1;

        } else {
            cur.moveToFirst();
            Boolean isValidPassword = cur.getString(cur.getColumnIndex(Table_Login_Details.COLUMN_PASSWORD)).equals(password);
            if (isValidPassword)
                key = 1;
            else
                key = 0;
        }
        return key;
    }


}
