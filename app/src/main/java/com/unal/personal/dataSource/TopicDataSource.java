package com.unal.personal.dataSource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.unal.personal.structures.Category;
import com.unal.personal.structures.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanka on 08/07/2016.
 */

public class TopicDataSource implements BaseColumns {

    private static final String TABLE_NAME = "Topic";
    private static final String COLUMN_CATEGORY = "category";

    public static ArrayList<Topic> getRandomTopic(Context context) {
        ArrayList<Topic> topics = new ArrayList<>();
        TWDataBase dataBase = new TWDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        String randomID = "(SELECT " + _ID + " FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1)";
        Cursor cursor = db.query(TopicDataSource.TABLE_NAME, null, _ID + " IN " + randomID, null, null, null, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                topics.add(new Topic(cursor.getString(cursor.getColumnIndex("content"))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        dataBase.close();
        return topics;
    }

    public static ArrayList<Topic> getTopicList(Category category, Context context) {
        ArrayList<Topic> topics = new ArrayList<>();
        TWDataBase dataBase = new TWDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), null, COLUMN_CATEGORY+ " =? ", new String[]{Float.toString(category.getId())}, null, null, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                topics.add(new Topic(cursor.getString(cursor.getColumnIndex("content"))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        dataBase.close();
        return topics;
    }

    public static String getTableName() {
        return TABLE_NAME + " INNER JOIN " + CategoryDataSource.TABLE_NAME + " ON " + TABLE_NAME + "." + COLUMN_CATEGORY + "=" + CategoryDataSource.TABLE_NAME + "." + _ID;
    }
}
