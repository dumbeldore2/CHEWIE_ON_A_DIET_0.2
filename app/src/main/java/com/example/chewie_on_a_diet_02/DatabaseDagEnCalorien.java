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
    public static final String DATABASE_TABLE = "databasedagencalorien";
    public static final String COL_1 = "id";
    public static final String COL_2 = "datum";
    public static final String COL_3 = "aantalcalorien";
    public static final String COL_4 = "idaccount";

    public DatabaseDagEnCalorien(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, datum text , aantalcalorien INTEGER ,idaccount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public int IDMAKER() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasedagencalorien", null
        );
        return cursor.getCount();
    }

    public void insertDagObject(int idaccount) {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);
        if (ErIsNogNiksVanDieDatum(deDatum,idaccount)){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, IDMAKER());
            contentValues.put(COL_2, deDatum);
            contentValues.put(COL_3, 0);
            contentValues.put(COL_4,idaccount);
            sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
        }

    }

    public int getId() {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select id from databasedagencalorien where datum ==" + "'" +deDatum + "'", null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return Integer.parseInt(stringBuffer.toString().trim());
    }

    public String getCalHuidig(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select aantalcalorien from databasedagencalorien where datum ==" + "'" + deDatum + "'" + "and idaccount ==" + id, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString() + " cal";
    }


    public void updateDatabase(double plusCalorien, int idAccount) {

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, getId());
        contentValues.put(COL_2, deDatum);
        contentValues.put(COL_3, plusCalorien);
        contentValues.put(COL_4, idAccount);
        sqLiteDatabase.update(DATABASE_TABLE, contentValues, "id = ?", new String[]{""+getId()});
    }

    public boolean ErIsNogNiksVanDieDatum(String dedatum , int idaccount) {
        boolean leeg = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasedagencalorien where datum == " + "'"+dedatum+"'" + "and idaccount ==" + idaccount, null);
        if (cursor.getCount() == 0) {
            leeg = true;
        }
        return leeg;
    }
    public boolean erZijnAlDatas(int idaccount){
        Boolean uit = false;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from databasedagencalorien where idaccount ==" + idaccount,null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }


    public String berekenGemiddeldefinal(int idaccount){
        String uit = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        if (erZijnAlDatas(idaccount)){
            Cursor cursor = sqLiteDatabase.rawQuery("select sum(aantalcalorien) from databasedagencalorien where idaccount == " +idaccount+ " order by id desc limit 7",null);
            Cursor cursor1 = sqLiteDatabase.rawQuery("select aantalcalorien from databasedagencalorien where idaccount == " +idaccount+ " order by id desc limit 7",null);

            if (cursor.moveToFirst()){
                stringBuffer.append(cursor.getString(0));
            }
            uit = Double.parseDouble(stringBuffer.toString())/ cursor1.getCount() + "";
        }
        return uit;
    }

}
