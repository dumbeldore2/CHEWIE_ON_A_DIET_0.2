package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseAccounts extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "databaseaccounts.db";
    public static final String DATABASE_TABLE = "dc";
    public static final String COL_1 = "id";
    public static final String COL_2 = "usernaam";
    public static final String COL_3 = "password";
    public static final String COL_4 = "emailadres";

    public DatabaseAccounts(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, username text, " +
                "password text, emailadres text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public int IDMAKER() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from dc", null
        );
        return cursor.getCount();
    }

    public void insertData(String username , String password , String emailadress){
        if (username.trim().isEmpty() || username == null)throw new IllegalArgumentException();
        if (password.trim().isEmpty() || password == null)throw new IllegalArgumentException();
        if (emailadress.trim().isEmpty() || emailadress == null)throw new IllegalArgumentException();
        
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, IDMAKER());
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, emailadress);

        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
    }
}
