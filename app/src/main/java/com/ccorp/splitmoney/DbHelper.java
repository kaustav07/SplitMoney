package com.ccorp.splitmoney;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chatt on 7/16/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    Context myContext;

    public DbHelper(Context context){
        super(context, DbContract.DATABASE_NAME,null, DbContract.DATABASE_VERSION);
        myContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Table_Login_Details.CREATE_TABLE);
            ToastMessage.showMessage(myContext,"on create called");
        }
        catch (SQLException e){
            Log.e("DbError",e.getMessage());
            ToastMessage.showMessage(myContext,"Error occured in oncreate");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(Table_Login_Details.DROP_TABLE);
            onCreate(db);
        }
        catch (SQLException e){
            Log.e("DbError",e.getMessage());
            ToastMessage.showMessage(myContext,"Error occured in onUpgrade");
        }
    }
}
