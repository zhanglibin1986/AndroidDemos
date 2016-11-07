package com.zlb.demos.androiddemos.html.mmjpg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.util.Log;
import com.zlb.demos.androiddemos.gank.bean.CommenImage;
import com.zlb.demos.androiddemos.utils.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/25 下午5:35
 */

public class MmDbManager {
    private static MmDbManager INSTANCE;
    private MmDbHelper mDbHelpter;

    private MmDbManager(@NonNull Context context) {
        Util.checkNotNull(context);
        mDbHelpter = new MmDbHelper(context);
    }

    public static MmDbManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MmDbManager(context);
        }

        return INSTANCE;
    }

    //增
    public boolean saveImage(CommenImage image) {
        Util.checkNotNull(image);
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        if(hasUrl(image.getUrl())) {
            log("already has image " + image.getUrl());
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(MmPersistenceContract.MmListEntry.TITLE, image.getName());
        values.put(MmPersistenceContract.MmListEntry.PIC_URL, image.getUrl());
        values.put(MmPersistenceContract.MmListEntry.IS_STAR, image.isStar());
        values.put(MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE, image.isWriteToFile());
        long result = db.insert(MmPersistenceContract.MmListEntry.TABLE_NAME, null, values);
        db.close();
        log("save ok ? " + (result != -1) + " : " + image);
        return result != -1;
    }

    //删
    public void deleteItem(String picUrl) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();
        String selection = MmPersistenceContract.MmListEntry.PIC_URL + " LIKE ?";
        String[] selectionArgs = { picUrl };

        db.delete(MmPersistenceContract.MmListEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    ////删
    //public void deleteImageUrl(String url) {
    //    SQLiteDatabase db = mDbHelpter.getWritableDatabase();
    //
    //    String selection = MmPersistenceContract.MmListEntry.COLUMN_NAME_URL + " LIKE ?";
    //    String[] selectionArgs = { url };
    //
    //    db.delete(MmPersistenceContract.MmListEntry.TABLE_NAME, selection, selectionArgs);
    //
    //    db.close();
    //}

    public void deleteAllImage() {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        db.delete(MmPersistenceContract.MmListEntry.TABLE_NAME, null, null);

        db.close();
    }

    //改
    public void updateImage(CommenImage image) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MmPersistenceContract.MmListEntry.TITLE, image.getName());
        values.put(MmPersistenceContract.MmListEntry.PIC_URL, image.getUrl());
        values.put(MmPersistenceContract.MmListEntry.IS_STAR, image.isStar());
        values.put(MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE, image.isWriteToFile());

        String selection = MmPersistenceContract.MmListEntry.PIC_URL + " LIKE ?";
        String[] selectionArgs = { image.getUrl() };

        db.update(MmPersistenceContract.MmListEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public boolean hasUrl(String url) {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                MmPersistenceContract.MmListEntry.PIC_URL
        };

        String selection = MmPersistenceContract.MmListEntry.PIC_URL + "=?";
        String[] selectionArgs = { url };

        Cursor cursor =
                db.query(MmPersistenceContract.MmListEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);
        boolean has = cursor != null && cursor.getCount() > 0;
        cursor.close();
        return has;
    }

    //查
    public List<CommenImage> queryStardImage(boolean isStar) {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                MmPersistenceContract.MmListEntry.PIC_URL, MmPersistenceContract.MmListEntry.TITLE,
                MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE, MmPersistenceContract.MmListEntry.IS_STAR
        };

        String selection = MmPersistenceContract.MmListEntry.IS_STAR + " LIKE ?";
        String[] selectionArgs = { isStar ? "1" : "0" };

        Cursor cursor =
                db.query(MmPersistenceContract.MmListEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);

        return getImageListFromCursor(cursor);
    }



    //查
    public List<CommenImage> queryAllImage() {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                MmPersistenceContract.MmListEntry.PIC_URL, MmPersistenceContract.MmListEntry.TITLE,
                MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE, MmPersistenceContract.MmListEntry.IS_STAR
        };


        Cursor cursor =
                db.query(MmPersistenceContract.MmListEntry.TABLE_NAME, projection, null,
                        null, null, null, null);

        return getImageListFromCursor(cursor);
    }

    //查
    public List<CommenImage> queryImages(int from, int count) {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                MmPersistenceContract.MmListEntry.PIC_URL, MmPersistenceContract.MmListEntry.TITLE,
                MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE, MmPersistenceContract.MmListEntry.IS_STAR
        };

        Cursor cursor =
                db.query(MmPersistenceContract.MmListEntry.TABLE_NAME, projection, null, null, null,
                        null, "" + BaseColumns._ID + " ASC", "" + from + "," + count);
        return getImageListFromCursor(cursor);

    }

    private List<CommenImage> getImageListFromCursor(Cursor cursor) {
        List<CommenImage> list = new ArrayList<>();
        CommenImage image = new CommenImage();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(
                        cursor.getColumnIndex(MmPersistenceContract.MmListEntry.TITLE));
                String picUrl = cursor.getString(
                        cursor.getColumnIndex(MmPersistenceContract.MmListEntry.PIC_URL));
                boolean star = cursor.getInt(
                        cursor.getColumnIndex(MmPersistenceContract.MmListEntry.IS_STAR)) == 1;
                boolean writeFile = cursor.getInt(
                        cursor.getColumnIndex(MmPersistenceContract.MmListEntry.IS_WRITE_TO_FILE))
                        == 1;

                image.setName(title);
                image.setUrl(picUrl);
                image.setStar(star);
                image.setWriteToFile(writeFile);
                list.add(image);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }

    private void log(String log) {
        Log.d("mmjpg", "------- " + log);

    }

}

/**
 *
 7
 down vote
 the limit clause should be "10,20" with no space between the coma and the 20.

 public Cursor getAllDiscounts() {
 return db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
 KEY_PORTALNAME, KEY_TITLE, KEY_TITLESHORT, KEY_DEALURL,
 KEY_ENDDATE, KEY_COORDS, KEY_CITY, KEY_IMAGEDEAL,
 KEY_CLICKPRICE, KEY_CONVERSIONPERCENTAGE, KEY_FINALPRICE,
 KEY_ORIGINALPRICE, KEY_SALES, KEY_KATEGORIJA, KEY_POPUST },
 null, null, null, null, null, "10,20");
 }
 *
 *
 *
 * SELECT
 keyword
 FROM
 keyword_rank
 WHERE
 advertiserid='59'
 order by
 keyword
 LIMIT 2 OFFSET 1;



 SELECT
 keyword
 FROM
 keyword_rank
 WHERE
 advertiserid='59'
 ORDER BY
 keyword
 LIMIT 2 ,1;
 *
 */
