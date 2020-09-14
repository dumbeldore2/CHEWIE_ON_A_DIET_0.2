package com.example.chewie_on_a_diet_02;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseLog extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databaselog.db";
    public static final String DATABASE_TABLE = "databaselog";
    public static final String COL_1 = "id";
    public static final String COL_2 = "datum";
    public static final String COL_3 = "tijd";
    public static final String COL_4 = "idaccount";
    public static final String COL_5 = "onderwerp";
    public static final String COL_6 = "beschrijving";

    public DatabaseLog(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, datum text,tijd " +
                "text, idaccount text, onderwerp text, beschrijving text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public int IDMAKER(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaselog",null
        );
        return cursor.getCount();
    }
}
