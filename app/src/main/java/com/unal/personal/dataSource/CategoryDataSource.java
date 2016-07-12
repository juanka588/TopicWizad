package com.unal.personal.dataSource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.unal.personal.R;
import com.unal.personal.structures.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanka on 08/07/2016.
 */

public class CategoryDataSource implements BaseColumns {
    public static final String TABLE_NAME = "Category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_COLOR = "color";

    public static List<Category> getCategories(Context context) {
        List<Category> categories = new ArrayList<>();
        TWDataBase dataBase = new TWDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        float id;
        String name;
        String image;
        String color;

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                id=cursor.getFloat(cursor.getColumnIndex(_ID));
                name=cursor.getString(cursor.getColumnIndex(COLUMN_NAME));;
                image=cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));;
                color=cursor.getString(cursor.getColumnIndex(COLUMN_COLOR));
                categories.add(new Category(id,name, R.drawable.ic_fingerprint_white_24dp,color));
                cursor.moveToNext();
            }
        }
        cursor.close();
        dataBase.close();
        return categories;
    }

}
