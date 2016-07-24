package com.ccorp.splitmoney;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chatt on 7/17/2016.
 */
public class ToastMessage {
    public static void showMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
