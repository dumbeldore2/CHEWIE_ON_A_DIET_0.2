package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//database over eten
public class DatabaseFood extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "databasefood.db";
    public static final String DATABASE_TABLE = "databasefood";
    public static final String COL_1 = "id";
    public static final String COL_2 = "naam";
    public static final String COL_3 = "merk";
    public static final String COL_4 = "groote";
    public static final String COL_5 = "calorien";
    public static final String COL_6 = "idaccount";

    public DatabaseFood(@Nullable Context context) {
        super(context, DATABASE_NAME , null ,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + " (id INTEGER primary key, naam text , merk text , groote INTEGER" +
                " , calorien INTEGER, idaccount INTEGER) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public  int IDMAKER(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefood",null
        );
        return cursor.getCount();
    }


    public void insertFoodObject(ObjectFood foodObject,int idaccount){
        if (foodObject != null){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,IDMAKER());
            contentValues.put(COL_2,foodObject.getNaam());
            contentValues.put(COL_3,foodObject.getMerk());
            contentValues.put(COL_4,100);
            contentValues.put(COL_5,foodObject.getCalorien());
            contentValues.put(COL_6,idaccount);
            sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
        }
    }


    public String getinfo(int idN,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefood where id =="+idN+"" + " and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(1));
            stringBuffer.append("  ||  ");
            stringBuffer.append(cursor.getString(3));
            stringBuffer.append("gr ||  ");
            stringBuffer.append(cursor.getString(4));
            stringBuffer.append("cal");
        }
        return stringBuffer.toString();
    }

    public ArrayList info(int idaccount){
        ArrayList<String>objectFoods;
        objectFoods = new ArrayList<>();
        for (int i = 0 ; i < IDMAKER(); i++){
            objectFoods.add(getinfo(i,idaccount));
        }
        return objectFoods;
    }

    public String getCol2(int i,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select naam from databasefood where id ==" + i+ " and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public String getCol3(int i,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select merk from databasefood where id ==" + i+ " and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public String getCol4(int i,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select groote from databasefood where id ==" + i+ " and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public String getCol5(int i,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select calorien from databasefood where id ==" + i+ " and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }

    public ArrayList get(int i,int idaccount){

        ArrayList<String>foodObjecten;
        foodObjecten = new ArrayList<>();

        foodObjecten.add(i+"");
        foodObjecten.add(getCol2(i,idaccount));
        foodObjecten.add(getCol3(i,idaccount));
        foodObjecten.add(getCol4(i,idaccount));
        foodObjecten.add(getCol5(i,idaccount));

        return foodObjecten;
    }
}

