package com.example.chewie_on_a_diet_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseActivity extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "databaseactivity.db";
    public static final String DATABASE_TABLE = "databaseactivity";
    public static final String COL_1 = "id";
    public static final String COL_2 = "datum";
    public static final String COL_3 = "duur";
    public static final String COL_4 = "aantalcalorien";
    public static final String COL_5 = "extra";
    public static final String COL_6 = "idaccount";


    public DatabaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, datum text , duur" +
                " INTEGER, aantalcalorien INTEGER, extra text, idaccount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public int IDMAKER() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseactivity", null
        );
        return cursor.getCount();
    }

    public void insertData(int duur , int cal , String extra, int idaccount){
        if (duur < 0)throw new IllegalArgumentException();
        if (cal < 0)throw new IllegalArgumentException();
        if (extra.trim().isEmpty())throw new IllegalArgumentException();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, IDMAKER());
        contentValues.put(COL_2, deDatum);
        contentValues.put(COL_3, duur);
        contentValues.put(COL_4, cal);
        contentValues.put(COL_5, extra);
        contentValues.put(COL_6, idaccount);

        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
    }

    public boolean erZijnAlDatas(int idaccount){

        Boolean uit = false;

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("select * from databaseactivity where datum ==" + "'" + deDatum + "'" + "and idaccount ==" + idaccount,null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }

    public ArrayList info(int idaccount){
        ArrayList<String>activitys;
        activitys = new ArrayList<>();
        for (int i = IDMAKER() -1 ; i >= 0; i--){
            activitys.add(getinfo(i,idaccount));
        }
        return activitys;
    }

    public String getinfo(int idN ,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseactivity where id =="+idN+"" + "and idaccount ==" + idaccount, null);
        if (cursor.moveToPosition(0)){
            stringBuffer.append(cursor.getString(1));
            stringBuffer.append("  ||  ");
            stringBuffer.append(cursor.getString(2));
            stringBuffer.append("min  ||  ");
            stringBuffer.append(cursor.getString(3));
            stringBuffer.append("cal  ||  ");
            stringBuffer.append(cursor.getString(4));
        }
        return stringBuffer.toString();
    }

    public int getGrooteVanVandaag(int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseactivity where datum ==" + "'" + deDatum + "'" + "and idaccount ==" + idaccount,null
        );
        return cursor.getCount();
    }
    public String getID(int i, int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseactivity where datum =="+"'"+deDatum+"'" + "and idaccount ==" + idaccount,null);
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
    public String getinfoVanDieDag(int idN , int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();

        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseactivity where id =="+idN+" and datum ==" + "'" + deDatum + "'" + "and idaccount ==" + idaccount, null);
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

    public ArrayList infotwee(int idaccount) {
        ArrayList<String> today;
        today = new ArrayList<>();
        ArrayList<String> nummers;
        nummers = new ArrayList<>();
        nummers = getIds(idaccount);
        for (int i = getGrooteVanVandaag(idaccount) - 1; i >= 0; i--) {
            today.add(getinfoVanDieDag(Integer.parseInt(nummers.get(i)),idaccount));
        }
        return today;
    }
}
