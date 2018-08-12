package com.sandyz.itemcode;

import android.content.ContentValues;
import android.util.Log;

import com.sandyz.itemcode.database.XlsConec;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

/**
 * Created by santosh on 26-07-2017.
 */

public class Excel2SQLiteHelper {

    public static final String Tablename = "ItemCode";
    public static final String id = "_id";// 0 integer
    public static final String CODE = "code";
    public static final String Description = "description";
    private static final String pip_stock = "pip_stock";
    private static final String fdy_stock = "fdy_stock";
    private static final String pip_location = "pip_location";
    private static final String fdy_location = "fdy_location";


    public static void insertExcelToSqlite(XlsConec dbAdapter, Sheet sheet) {

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues contentValues = new ContentValues();
            row.getCell(0, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5, Row.CREATE_NULL_AS_BLANK).setCellType(Cell.CELL_TYPE_STRING);

            contentValues.put(CODE, row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(Description, row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(pip_stock, row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(fdy_stock, row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(pip_location, row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(fdy_location, row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

            dbAdapter.open();
            try {
                if (dbAdapter.insert(Tablename, contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }
}