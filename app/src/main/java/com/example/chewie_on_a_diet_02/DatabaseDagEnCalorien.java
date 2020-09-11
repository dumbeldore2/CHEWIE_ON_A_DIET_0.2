package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.nio.IntBuffer;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseDagEnCalorien extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasedagencalorien.db";
    public static final String DATABASE_TABLE = "dc";
    public static final String COL_1 = "id";
    public static final String COL_2 = "datum";
    public static final String COL_3 = "aantalcalorien";

    public DatabaseDagEnCalorien(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, datum text , aantalcalorien INTEGER )");
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

    public void insertDagObject() {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);
        if (ErIsNogNiksVanDieDatum(deDatum)){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, IDMAKER());
            contentValues.put(COL_2, deDatum);
            contentValues.put(COL_3, 0);
            sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
        }

    }

    public int getId() {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select id from dc where datum ==" + "'" +deDatum + "'", null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return Integer.parseInt(stringBuffer.toString().trim());
    }

    public String getCalHuidig() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select aantalcalorien from dc where datum ==" + "'" + deDatum + "'", null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString() + " cal";
    }


    public void updateDatabase(double plusCalorien) {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, getId());
        contentValues.put(COL_2, deDatum);
        contentValues.put(COL_3, plusCalorien);
        sqLiteDatabase.update(DATABASE_TABLE, contentValues, "id = ?", new String[]{""+getId()});
    }

    public boolean ErIsNogNiksVanDieDatum(String dedatum) {
        boolean leeg = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from dc where datum == " + "'"+dedatum+"'", null);
        if (cursor.getCount() == 0) {
            leeg = true;
        }
        return leeg;
    }
    public boolean erZijnAlDatas(){
        Boolean uit = false;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from dc",null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }


    public String berekenGemiddeldefinal(){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        if (erZijnAlDatas()){
            Cursor cursor = sqLiteDatabase.rawQuery("select sum(aantalcalorien) from dc order by id desc limit 7",null);
            Cursor cursor1 = sqLiteDatabase.rawQuery("select aantalcalorien from dc order by id " +
                    "desc limit 7",null);

            if (cursor.moveToFirst()){
                stringBuffer.append(cursor.getString(0));
            }
            uit = Double.parseDouble(stringBuffer.toString())/ cursor1.getCount() + "";
        }
        return uit;
    }

}
