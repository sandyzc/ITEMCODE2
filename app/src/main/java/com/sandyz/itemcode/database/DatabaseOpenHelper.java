package com.sandyz.itemcode.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.sandyz.itemcode.Beans;

import java.util.ArrayList;


public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static String DATABASE_NAME = "ItemCode.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TableName = "ItemCode";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public ArrayList<Beans> mouldLine(String code ) {

        String Description = "description";
        code= "%"+code+"%";
        String wheer= Description+" LIKE ?";

        String[] whereArgs = new String[]{code};

        ArrayList<Beans> search_result = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(TableName,null,wheer,whereArgs,null,null,null);

        if (cursor.moveToFirst()) {

            do {
                Beans search_Data = new Beans();
                search_Data.setCode(cursor.getString(1));
                search_Data.setDescp(cursor.getString(2));
                search_Data.setEquip(cursor.getString(3));
                search_result.add(search_Data);
            } while (cursor.moveToNext());
        }
        database.close();
        cursor.close();

        return search_result;

    }


    public ArrayList<Beans> codeSearch(String code ) {

        String Description = "code";
        code= "%"+code+"%";
        String wheer= Description+" LIKE ?";

        String[] whereArgs = new String[]{code};

        ArrayList<Beans> search_result = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(TableName,null,wheer,whereArgs,null,null,null);

        if (cursor.moveToFirst()) {

            do {
                Beans search_Data = new Beans();
                search_Data.setCode(cursor.getString(1));
                search_Data.setDescp(cursor.getString(2));
                search_Data.setEquip(cursor.getString(3));
                search_result.add(search_Data);
            } while (cursor.moveToNext());
        }
        database.close();
        cursor.close();

        return search_result;

    }

}
