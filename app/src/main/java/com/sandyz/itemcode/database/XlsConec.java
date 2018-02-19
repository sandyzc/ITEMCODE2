package com.sandyz.itemcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;



public class XlsConec extends SQLiteOpenHelper {



    private static final String TableName = "ItemCode";
    public static final String id = "_id";// 0 integer
    private static final String CODE = "code";
    private static final String Description = "description";
    private static final String EQUIPMENT_NAME = "equipment_namr";
    private static final String Dept_code = "dept_code";
    public static final int version = 1;
    private static final String DbNAme = "ItemCode.db";

    private SQLiteDatabase database = null;

    public XlsConec(Context context) {
        super(context, DbNAme, null, version);
        Context ctx = context;

    }

    public void open() {
        if (this.database == null) {
            try {
                this.database = this.getWritableDatabase();
            } catch (SQLException ignored) {

            }
        }
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_sql = "CREATE TABLE IF NOT EXISTS " + TableName + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CODE + " TEXT NULL ," + Description + " TEXT NULL,"
                + EQUIPMENT_NAME + " TEXT NULL, " + Dept_code + ")";

        sqLiteDatabase.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
    }

    public int insert(String table, ContentValues values) {
        return (int) this.database.insert(table, null, values);
    }

    public ArrayList<HashMap<String, String>> getCodes() {
        ArrayList<HashMap<String, String>> codeList;

        codeList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TableName;

        database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(CODE, cursor.getString(1));
                map.put(Description, cursor.getString(2));
                map.put(EQUIPMENT_NAME, cursor.getString(3));
                codeList.add(map);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return codeList;
    }

}





