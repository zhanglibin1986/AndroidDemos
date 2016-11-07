package com.zlb.demos.androiddemos.html.mmjpg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author zhanglibin
 * @since 16/10/25 下午5:30
 */

public class MmDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mmjpg.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String SQL_CREATE_TENTRIES =
            "CREATE TABLE " + MmPersistenceContract.MmListEntry.TABLE_NAME + " (" +
                    MmPersistenceContract.MmListEntry._ID + TEXT_TYPE + " PRIMARY KEY," +

                    MmPersistenceContract.MmListEntry.PIC_URL + TEXT_TYPE + "," +
                    MmPersistenceContract.MmListEntry.TITLE + TEXT_TYPE + "," +
                    MmPersistenceContract.MmListEntry.IS_STAR + BOOLEAN_TYPE + "," +
                    MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE + BOOLEAN_TYPE +
                    ")";

    public MmDbHelper(Context context) {
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
