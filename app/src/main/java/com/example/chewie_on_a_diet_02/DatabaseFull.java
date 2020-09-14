package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseFull extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasefull.db";
    public static final String DATABASE_TABLE = "databasefull";
    public static final String COL_1 = "id";
    public static final String COL_2 = "naam";
    public static final String COL_3 = "merk";
    public static final String COL_4 = "groote";
    public static final String COL_5 = "calorien";
    public static final String COL_6 = "datum";
    public static final String COL_7 = "soort";
    public static final String COL_8 = "idaccount";

    public DatabaseFull(@Nullable Context context) {
        super(context, DATABASE_NAME , null ,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + " (id INTEGER primary key, naam text , merk text , groote INTEGER" +
                " , calorien float , datum text, soort text , idaccount INTEGER) " );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public  int IDMAKER(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefull",null
        );
        return cursor.getCount();
    }
    public void insert(String a,String b, int c,double d,String f,int idaccount){
        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,IDMAKER());
        contentValues.put(COL_2,a);
        contentValues.put(COL_3,b);
        contentValues.put(COL_4,c);
        contentValues.put(COL_5,d);
        contentValues.put(COL_6,deDatum);
        contentValues.put(COL_7,f);
        contentValues.put(COL_8,idaccount);
        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);

    }


    public int getGrooteVanVandaag(int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefull where datum ==" + "'" + deDatum + "'" + "and idaccount ==" + idaccount,null
        );
        return cursor.getCount();
    }
    public String getID(int i,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefull where datum =="+"'"+deDatum+"'" + "and idaccount ==" + idaccount,null);
        if (cursor.moveToPosition(i)){
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public ArrayList getIds(int idaccount){
        ArrayList<String> nummers;
        nummers = new ArrayList<>();
        for (int i = 0; i < getGrooteVanVandaag(idaccount) ; i++){
           nummers.add(getID(i,idaccount));
        }
        return nummers;
    }
    public String getinfoVanDieDag(int idN,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databasefull where id =="+idN+" and datum ==" + "'" + deDatum + "'", null);
        if(cursor.moveToPosition(0)){
            stringBuffer.append(cursor.getString(1));
            stringBuffer.append("  ||  ");
            stringBuffer.append(cursor.getString(3));
            stringBuffer.append("gr  ||  ");
            stringBuffer.append(cursor.getString(4));
            stringBuffer.append("cal");
        }
        return stringBuffer.toString();
    }

    public ArrayList info(int idaccount) {
        ArrayList<String> today;
        today = new ArrayList<>();
        ArrayList<String> nummers;
        nummers = new ArrayList<>();
        nummers = getIds(idaccount);
        for (int i = getGrooteVanVandaag(idaccount) - 1; i >= 0; i--) {
            System.out.println(getinfoVanDieDag(Integer.parseInt(nummers.get(i)),idaccount));
            today.add(getinfoVanDieDag(Integer.parseInt(nummers.get(i)),idaccount));
        }
        return today;
    }



    public double berekenDagelijkseCal(String deDatum,int idaccount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        if (erZijnAlDatas(idaccount)){
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "select sum(calorien) from databasefull where datum ==" + "'" + deDatum + "'" + "and idaccount ==" + idaccount,null);
            if (cursor.moveToFirst()){
                stringBuffer.append(cursor.getString(0));
            }
        }
        return Double.parseDouble(stringBuffer.toString());
    }

    public boolean erZijnAlDatas(int idaccount){
        Boolean uit = false;

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("select * from databasefull where datum ==" + "'" + deDatum +"'" + "and idaccount ==" + idaccount,null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }
}
