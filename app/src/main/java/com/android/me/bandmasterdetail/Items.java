package com.android.me.bandmasterdetail;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by ME on 1/5/2017.
 */

public class Items {

    public static final String DB_NAME = "bands.db";
    public static final int DB_VERSION = 2;
    public static final String TABLE = "band";
    public static final String C_ID = BaseColumns._ID;
    public static final String C_DESC = "band_desc";
    public static final String C_NAME = "band_name";
    public static final String C_GENRE = "band_genre";

    //private ArrayList<Band>  items;
    private DbHelper dbHelper;
    private static Items instance;

    private Items(Context context){
        dbHelper = new DbHelper(context);

    }

    public void add(Band band){
        dbHelper.insert(band);
    }

    public static Items getInstance(Context context) {
        if (instance == null) {
            instance = new Items(context);
        }
        return instance;
    }

    public Cursor getBands() {
        return dbHelper.getAll();
    }

    public Band getBand(long id) {
        return dbHelper.getBand(id);
    }
}
