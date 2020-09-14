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

public class DatabaseWeight extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "databaseweight.db";
    public static final String DATABASE_TABLE = "databaseweight";
    public static final String COL_1 = "id";
    public static final String COL_2 = "datum";
    public static final String COL_3 = "uur";
    public static final String COL_4 = "gewicht";
    public static final String COL_5 = "idaccount";

    public DatabaseWeight(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(id INTEGER primary key, datum text , uur " +
                "text, gewicht text, idaccount INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public int IDMAKER() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseweight", null
        );
        return cursor.getCount();
    }

    public void insertData(String i,int idaccount){
        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);
        String hetUur = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, IDMAKER());
        contentValues.put(COL_2, deDatum);
        contentValues.put(COL_3, hetUur);
        contentValues.put(COL_4, i);
        contentValues.put(COL_5, idaccount);

        sqLiteDatabase.insert(DATABASE_TABLE,null,contentValues);
    }
    public String getLaatste(int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        i += IDMAKER() -1;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select gewicht from databaseweight where id ==" + i + "and idaccount ==" + idaccount, null);
        if (cursor.moveToFirst()) {
            stringBuffer.append(cursor.getString(0));
        }
        return stringBuffer.toString();
    }
    public boolean erZijnAlDatas(int idaccount){
        Boolean uit = false;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("select * from databaseweight where idaccount ==" + idaccount,null);
        if (cursor.getCount() > 0){
            uit = true;
        }
        return uit;
    }
    public ArrayList info(int idaccount){
        ArrayList<String>weights;
        weights = new ArrayList<>();
        for (int i = IDMAKER() -1 ; i >= 0; i--){
            weights.add(getinfo(i, idaccount));
        }
        return weights;
    }
    public String getinfo(int idN,int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select * from databaseweight where id =="+idN+"" + " and idaccount ==" + idaccount, null);
        if (cursor.moveToPosition(0)){
            stringBuffer.append(cursor.getString(1));
            stringBuffer.append("  ||  ");
            stringBuffer.append(cursor.getString(2));
            stringBuffer.append("  ||  ");
            stringBuffer.append(cursor.getString(3));
            stringBuffer.append("kg");
        }
        return stringBuffer.toString();
    }

    public String getLaatsteGewicht(int idaccount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        StringBuffer stringBuffer = new StringBuffer();
        String uit = "";
        Cursor cursor = sqLiteDatabase.rawQuery(
                "select gewicht from databaseweight where idaccount == "+idaccount+" order by id DESC limit 1",
                null);
        if (cursor.moveToFirst()){
            stringBuffer.append(cursor.getString(0));
        }
        uit += stringBuffer.toString();
        return uit;
    }
}
