package com.ccorp.splitmoney;

/**
 * Created by chatt on 7/16/2016.
 */
public class Table_Login_Details {

    public static String TABLE_NAME = "tbl_SplitMoney_Login";

    public static String COLUMN_ID = "_id";

    public static String COLUMN_USERNAME = "UserName";

    public static String COLUMN_PASSWORD = "Password";

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE , " + COLUMN_USERNAME + " TEXT NOT NULL UNIQUE , " + COLUMN_PASSWORD + " TEXT NOT NULL);";

    public static String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
}
