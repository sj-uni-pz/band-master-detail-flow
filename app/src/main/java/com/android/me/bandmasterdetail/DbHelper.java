package com.android.me.bandmasterdetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ME on 1/16/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public DbHelper(Context context) {
        super(context, Items.DB_NAME, null,
                Items.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format(
                "create table %s (%s integer primary key, %s text, %s text, %s text)",
                Items.TABLE,
                Items.C_ID,
                Items.C_NAME,
                Items.C_GENRE,
                Items.C_DESC);
        Log.d(TAG, "onCreate with SQL: "+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Items.TABLE);
        Log.d(TAG, "onUpgrade ");
        onCreate(db);
    }

    public void insert(Band band) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Items.C_NAME,band.getName());
        values.put(Items.C_GENRE,band.getGenre());
        values.put(Items.C_DESC,band.getDescription());
        db.insert(Items.TABLE,null,values );
    }

    public Cursor getAll(){
        SQLiteDatabase db = getReadableDatabase();
        //query(String table, String[] columns, String selection,
        // String[] selectionArgs, String gruopBy,String having,String orderBy)
        Cursor cursor = db.query(Items.TABLE,null,null,null,null,null,
                Items.C_NAME + " asc");
        return cursor;
    }

    public Band getBand(long id){
        SQLiteDatabase db = getReadableDatabase();
        String[] whereArgs = new String[] {id + ""};
        Cursor cursor = db.query(Items.TABLE,null,"_id = ?",whereArgs,null,null,null);
        if(cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(Items.C_NAME));
            String genre = cursor.getString(cursor.getColumnIndex(Items.C_GENRE));
            String desc = cursor.getString(cursor.getColumnIndex(Items.C_DESC));
            return new Band(name, genre, desc);
        }
        return null;
    }
}
