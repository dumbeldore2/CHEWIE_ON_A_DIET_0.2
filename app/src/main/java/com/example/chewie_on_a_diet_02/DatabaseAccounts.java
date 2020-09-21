package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

class DatabaseAccounts extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "databaseaccounts.db";
    public static final String DATABASE_TABLE = "databaseaccounts";
    public static final String COL_1 = "id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "emailadres";
    public static final String COL_5 = "laatste";

    public DatabaseAccounts(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, username text, " +
                "password text, emailadres text, laatste text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public int IDMAKER() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseaccounts", null
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
        contentValues.put(COL_5, "true");

        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
    }
    public boolean erIsAlDatas(){
        Boolean uit = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from databaseaccounts",null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }

    public String getLaatsteUsername(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("select username from databaseaccounts where " +
                "laatste ==" + "'" + "true" + "'" + " order by id desc limit 1",null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public String getLaatstemail(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("select emailadres from databaseaccounts where laatste == " + "'" + "true" + "'" + " order by id desc limit 1",null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public int getLaatsteId(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("select id from databaseaccounts where laatste == " + "'" + "true" + "'" + " order by id desc limit 1",null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return Integer.parseInt(stringBuffer.toString());
    }
    public String getEmail(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery("select emailadres from databaseaccounts where id == " + id,
                null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public String getUsername(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor =
                sqLiteDatabase.rawQuery("select username from databaseaccounts where id == " + id,
                null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }

    public String getPassword(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor =
                sqLiteDatabase.rawQuery("select password from databaseaccounts where id == " + id,
                null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }

    public void updateAllesNaarFals(){
        for (int i = 0 ;i < IDMAKER() ; i++ ){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, i);
            contentValues.put(COL_2, getUsername(i));
            contentValues.put(COL_3, getPassword(i));
            contentValues.put(COL_4, getEmail(i));
            contentValues.put(COL_5, "false");
            sqLiteDatabase.update(DATABASE_TABLE, contentValues, "id = ?", new String[]{""+i});}
    }

    public void updateTrueTrue(int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, getUsername(id));
        contentValues.put(COL_3, getPassword(id));
        contentValues.put(COL_4, getEmail(id));
        contentValues.put(COL_5, "true");
        sqLiteDatabase.update(DATABASE_TABLE, contentValues, "id = ?", new String[]{""+id});
    }

    public int getLaatste(){
        return IDMAKER() -1;
    }

    public boolean checkOfErIemandBestaatMetDezeNaam(String naam){
        boolean uit = false;
        for (int i = 0 ; i < IDMAKER()  && uit == false; i++){
            if (naam.trim().equals(getUsername(i))){
                uit = true;
            }
        }
        return uit;
    }


    public int nummerCheckOfErIemandBestaatMetDezeNaam(String naam){
        int uit = -1;
        for (int i = 0 ; i < IDMAKER() ; i++){
            if (naam.equals(getUsername(i))){
                uit = i;
                System.out.println(i);
            }
        }
        System.out.println(uit);
        return uit;
    }
}
