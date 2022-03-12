package com.example.bankingsystem.sideCode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandlerT extends SQLiteOpenHelper {
    public DbHandlerT(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.DB_TRANSACTION +
                "(" + Params.CID + " INTEGER PRIMARY KEY, " + Params.C_NAME
                + " TEXT, " + Params.C_EMAIL + " TEXT, " + Params.C_PHONE + " TEXT, " + Params.C_BALANCE +
                " Text" + ")";

        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
