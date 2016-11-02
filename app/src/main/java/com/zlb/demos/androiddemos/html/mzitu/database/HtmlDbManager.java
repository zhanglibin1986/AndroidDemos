package com.zlb.demos.androiddemos.html.mzitu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.zlb.demos.androiddemos.html.mzitu.MzHomeItem;
import com.zlb.demos.androiddemos.utils.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/25 下午5:35
 */

public class HtmlDbManager {
    private static HtmlDbManager INSTANCE;
    private HtmlDbHelper mDbHelpter;

    private HtmlDbManager(@NonNull Context context) {
        Util.checkNotNull(context);
        mDbHelpter = new HtmlDbHelper(context);
    }

    public static HtmlDbManager getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new HtmlDbManager(context);
        }

        return INSTANCE;
    }

    //增
    public void saveImage(MzHomeItem image) {
        Util.checkNotNull(image);
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HtmlPersistenceContract.HtmlListEntry.DETAIL_URL, image.getDetailUrl());
        values.put(HtmlPersistenceContract.HtmlListEntry.TITLE, image.getTitle());
        values.put(HtmlPersistenceContract.HtmlListEntry.PIC_URL, image.getPicUrl());
        values.put(HtmlPersistenceContract.HtmlListEntry.IS_STAR, image.isStar());

        db.insert(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, null, values);

        db.close();
    }

    //删
    public void deleteItem(String detailUrl) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        String selection = HtmlPersistenceContract.HtmlListEntry.DETAIL_URL + " LIKE ?";
        String[] selectionArgs = { detailUrl };

        db.delete(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    ////删
    //public void deleteImageUrl(String url) {
    //    SQLiteDatabase db = mDbHelpter.getWritableDatabase();
    //
    //    String selection = HtmlPersistenceContract.HtmlListEntry.COLUMN_NAME_URL + " LIKE ?";
    //    String[] selectionArgs = { url };
    //
    //    db.delete(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, selection, selectionArgs);
    //
    //    db.close();
    //}

    public void deleteAllImage() {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        db.delete(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, null, null);

        db.close();
    }

    //改
    public void updateImage(MzHomeItem image) {
        SQLiteDatabase db = mDbHelpter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HtmlPersistenceContract.HtmlListEntry.DETAIL_URL, image.getDetailUrl());
        values.put(HtmlPersistenceContract.HtmlListEntry.TITLE, image.getTitle());
        values.put(HtmlPersistenceContract.HtmlListEntry.PIC_URL, image.getPicUrl());
        values.put(HtmlPersistenceContract.HtmlListEntry.IS_STAR, image.isStar());

        String selection = HtmlPersistenceContract.HtmlListEntry.DETAIL_URL + " LIKE ?";
        String[] selectionArgs = { image.getDetailUrl() };

        db.update(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, values, selection,
                selectionArgs);
        db.close();
    }

    //查
    public List<MzHomeItem> queryImage(boolean isStar) {
        SQLiteDatabase db = mDbHelpter.getReadableDatabase();

        String[] projection = {
                HtmlPersistenceContract.HtmlListEntry.DETAIL_URL,
                HtmlPersistenceContract.HtmlListEntry.TITLE,
                HtmlPersistenceContract.HtmlListEntry.PIC_URL,
                HtmlPersistenceContract.HtmlListEntry.IS_STAR
        };

        String selection = HtmlPersistenceContract.HtmlListEntry.IS_STAR + " LIKE ?";
        String[] selectionArgs = { isStar ? "1" : "0" };

        Cursor cursor =
                db.query(HtmlPersistenceContract.HtmlListEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);

        List<MzHomeItem> list = new ArrayList<>();
        MzHomeItem image = new MzHomeItem();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String detail = cursor.getString(
                        cursor.getColumnIndex(HtmlPersistenceContract.HtmlListEntry.DETAIL_URL));
                String title = cursor.getString(
                        cursor.getColumnIndex(HtmlPersistenceContract.HtmlListEntry.TITLE));
                String picUrl = cursor.getString(
                        cursor.getColumnIndex(HtmlPersistenceContract.HtmlListEntry.PIC_URL));
                boolean star = cursor.getInt(
                        cursor.getColumnIndex(HtmlPersistenceContract.HtmlListEntry.IS_STAR)) == 1;

                image.setDetailUrl(detail);
                image.setTitle(title);
                image.setPicUrl(picUrl);
                image.setStar(star);
                list.add(image);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }
}
