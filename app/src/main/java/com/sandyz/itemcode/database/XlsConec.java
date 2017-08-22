package com.sandyz.itemcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sandyz.itemcode.Beans;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by santosh on 21-07-2017.
 */

public class XlsConec extends SQLiteOpenHelper{

    String TAG = "DBAdapter";

    public static final String TableName = "ItemCode";
    public static final String id = "_id";// 0 integer
    public static final String CODE = "code";
    public static final String Description = "description";
    public static final String EQUIPMENT_NAME= "equipment_namr";
    public static final int version = 1;
    public static final String DbNAme = "ItemCode.db";

    private Context ctx;
    private SQLiteDatabase database;

    public XlsConec(Context context) {
        super(context,DbNAme,null,version);
        ctx= context;

    }



    public void open(){
        if (this.database==null){
            try {
               this.database=this.getWritableDatabase();
            }catch (SQLException exceptiom){

            }
        }
    }

    public void close(){
        if (this.database!=null){
            this.database.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_sql=  "CREATE TABLE IF NOT EXISTS " + TableName + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CODE + " TEXT NULL ," + Description + " TEXT NULL,"
                + EQUIPMENT_NAME + " TEXT NULL" + ")";

        sqLiteDatabase.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
    }

    public int insert(String table, ContentValues values){
        return (int)this.database.insert(table,null,values);
    }

    public void delete(){
        this.database.execSQL(" delete from "+TableName);
    }

    public Cursor getAllRows(String table){
        return this.database.query(table,null,null,null,null,null,id);
    }



    public ArrayList<HashMap<String,String>> getCodes(){
        ArrayList<HashMap<String,String>> codeList;

        codeList = new ArrayList<HashMap<String,String>>();
        String selectQuery = "SELECT  * FROM " + TableName;

        database =this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(CODE, cursor.getString(1));
                map.put(Description, cursor.getString(2));
                map.put(EQUIPMENT_NAME, cursor.getString(3));
                codeList.add(map);
            } while (cursor.moveToNext());

        }
        return codeList;
    }


    //search keyword from db
    public ArrayList<Beans>searchcode(){

        ArrayList<Beans> search_result= new ArrayList<>();

        database=this.getReadableDatabase();
        String selectQuery = "select * from " + TableName+" WHERE"+Description ;

        Cursor cursor = database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Beans search_Data = new Beans();
                search_Data.setId(cursor.getInt(0));
                search_Data.setCode(cursor.getString(1));
                search_Data.setDescp(cursor.getString(2));
                search_Data.setEquip(cursor.getString(3));
            }while (cursor.moveToNext());
        }
       database.close();
        return search_result;

    }

}

