package com.example.sarvesh.mytime;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TimeSQLHelper extends SQLiteOpenHelper{
String query;
    final static String DATABASE_NAME = "my_time";
    final static String FAV_TABLE_NAME = "time";
    final static String FAV_TABLE_TITLE = "title";
    final static String ATTENDENCE = "attendence";
    final static String TOTAL = "total";
    final static String att="percentage";
//    final static String FAV_TABLE_DATE_ADDED = "date_added";
    final static String _ID = "_id";


    public TimeSQLHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + FAV_TABLE_NAME +" ("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FAV_TABLE_TITLE +" TEXT,"+ ATTENDENCE + " TEXT,"  + TOTAL + " TEXT,"+ att + " TEXT"+");";
        db.execSQL(query);
    }

//    public static Cursor getId(SQLiteDatabase db,String where){
//        return db.query(FAV_TABLE_NAME, new String[] {_ID},where,null,null,null,null);
//    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
