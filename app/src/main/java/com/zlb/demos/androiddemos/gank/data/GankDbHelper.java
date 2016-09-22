package com.zlb.demos.androiddemos.gank.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Gank 本地数据库
 *
 * @author zhanglibin
 * @since 16/9/19 上午10:08
 */
public class GankDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gank.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String SQL_CREATE_TENTRIES =
            "CREATE TABLE " + GankPersistenceContract.GankEntry.TABLE_NAME + " (" +
                    GankPersistenceContract.GankEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + "," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_TITLE + TEXT_TYPE + "," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + "," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_IS_STAR + BOOLEAN_TYPE + "," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_URL + TEXT_TYPE + "," +
                    GankPersistenceContract.GankEntry.COLUMN_NAME_LOCAL_URL + TEXT_TYPE +
                    " )";
    public GankDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public GankDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public GankDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
