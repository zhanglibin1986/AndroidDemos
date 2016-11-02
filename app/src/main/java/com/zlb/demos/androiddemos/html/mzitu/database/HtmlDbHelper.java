package com.zlb.demos.androiddemos.html.mzitu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author zhanglibin
 * @since 16/10/25 下午5:30
 */

public class HtmlDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mzitu.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String SQL_CREATE_TENTRIES =
            "CREATE TABLE " + HtmlPersistenceContract.HtmlListEntry.TABLE_NAME + " (" +
                    HtmlPersistenceContract.HtmlListEntry._ID + TEXT_TYPE + " PRIMARY KEY," +

                    HtmlPersistenceContract.HtmlListEntry.DETAIL_URL + TEXT_TYPE + "," +
                    HtmlPersistenceContract.HtmlListEntry.PIC_URL + TEXT_TYPE + "," +
                    HtmlPersistenceContract.HtmlListEntry.TITLE + TEXT_TYPE + "," +
                    HtmlPersistenceContract.HtmlListEntry.IS_STAR + BOOLEAN_TYPE +
                    ")";

    public HtmlDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
