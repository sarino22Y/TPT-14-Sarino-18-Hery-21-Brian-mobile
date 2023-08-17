package com.mbds.tpt_sarino_brian_hery.model.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {



    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_MAIL = "mail";
    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_LASTNAME + " TEXT,"
            + COLUMN_FIRSTNAME + " TEXT,"
            + COLUMN_MAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
