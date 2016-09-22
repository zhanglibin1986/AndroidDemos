package com.zlb.demos.androiddemos.gank.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.annotation.NonNull;

import com.zlb.demos.androiddemos.gank.bean.CommenImage;
import com.zlb.demos.androiddemos.utils.Util;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/19 上午10:25
 */
public class GankDbManager {
    private static GankDbManager INSTANCE;
    private GankDbHelper mDbHelpter;

    private GankDbManager(@NonNull Context context) {
        Util.checkNotNull(context);
        mDbHelpter = new GankDbHelper(context);
    }

    public static GankDbManager getInstance(@NonNull Context context) {
        if(INSTANCE == null) {
            INSTANCE = new GankDbManager(context);
        }

        return INSTANCE;
    }

    //增
    public void saveImage(CommenImage image) {
        Util.checkNotNull(image);
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID, image.getId());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_TITLE, image.getName());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_DESCRIPTION, image.getDescription());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_URL, image.getUrl());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_LOCAL_URL, image.getLocalUrl());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_IS_STAR, image.isStar());

        db.insert(GankPersistenceContract.GankEntry.TABLE_NAME, null, values);

        db.close();
    }
    //删
    public void deleteImage(String id) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        String selection = GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {id};

        db.delete(GankPersistenceContract.GankEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    //删
    public void deleteImageUrl(String url) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        String selection = GankPersistenceContract.GankEntry.COLUMN_NAME_URL + " LIKE ?";
        String[] selectionArgs = {url};

        db.delete(GankPersistenceContract.GankEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    public void deleteAllImage() {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        db.delete(GankPersistenceContract.GankEntry.TABLE_NAME, null, null);

        db.close();
    }

    //改
    public void updateImage(CommenImage image) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID, image.getId());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_TITLE, image.getName());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_DESCRIPTION, image.getDescription());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_URL, image.getUrl());
        values.put(GankPersistenceContract.GankEntry.COLUMN_NAME_LOCAL_URL, image.getLocalUrl());

        String selection = GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { image.getId() };

        db.update(GankPersistenceContract.GankEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    //查
    public CommenImage queryImage(String url) {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID,
                GankPersistenceContract.GankEntry.COLUMN_NAME_TITLE ,
                GankPersistenceContract.GankEntry.COLUMN_NAME_DESCRIPTION ,
                GankPersistenceContract.GankEntry.COLUMN_NAME_URL ,
                GankPersistenceContract.GankEntry.COLUMN_NAME_LOCAL_URL ,
                GankPersistenceContract.GankEntry.COLUMN_NAME_IS_STAR
        };

        String selection = GankPersistenceContract.GankEntry.COLUMN_NAME_URL + " LIKE ?";
        String[] selectionArgs = { url };

        Cursor cursor = db.query(GankPersistenceContract.GankEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        CommenImage image = new CommenImage();

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_ENTRY_ID));
            String name = cursor.getString(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_TITLE));
            String des = cursor.getString(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_DESCRIPTION));
            String imageUrl = cursor.getString(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_URL));
            String imageLocalUrl = cursor.getString(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_LOCAL_URL));
            boolean star = cursor.getInt(cursor.getColumnIndex(GankPersistenceContract.GankEntry.COLUMN_NAME_IS_STAR)) == 1;

            image.setId(id);
            image.setName(name);
            image.setDescription(des);
            image.setUrl(imageUrl);
            image.setLocalUrl(imageLocalUrl);
            image.setStar(star);
        }

        return image;
    }










}
