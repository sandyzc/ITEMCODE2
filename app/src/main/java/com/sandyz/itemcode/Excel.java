package com.sandyz.itemcode;


import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sandyz.itemcode.database.XlsConec;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Excel extends ListActivity {

    TextView lbl;
    XlsConec controller = new XlsConec(this);
    Button btnimport;
    ListView lv;
    public static final int requestcode = 1;
    static String tableName;

    public static final String Tablename = "ItemCode";
    public static final String id = "_id";// 0 integer
    public static final String CODE = "code";
    public static final String Description = "description";
    public static final String EQUIPMENT_NAME= "equipment_namr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnimport = (Button) findViewById(R.id.btnupload);

        lbl = (TextView) findViewById(R.id.txtresulttext);
        lv = getListView();
        tableName = "info";

        btnimport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("gagt/sdf");
                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }

            }
        });
        ArrayList<HashMap<String, String>> myList = controller.getCodes();
        if (myList.size() != 0) {
            lv = getListView();
            ListAdapter adapter = new SimpleAdapter(Excel.this, myList,
                    R.layout.v, new String[]{CODE,Description,EQUIPMENT_NAME},
                    new int[]{R.id.txtcode, R.id.txtdescprip,
                            R.id.txtequip});
            setListAdapter(adapter);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case requestcode:
                String FilePath = data.getData().getPath();
                try {
                    if (resultCode == RESULT_OK) {
                        AssetManager am = this.getAssets();
                        InputStream inStream;
                        Workbook wb = null;
                        try {
                            inStream = new FileInputStream(FilePath);
                            wb = new HSSFWorkbook(inStream);
                            inStream.close();
                        } catch (IOException e) {
                            lbl.setText("First "+e.getMessage().toString());
                            e.printStackTrace();
                        }

                        XlsConec dbAdapter = new XlsConec(this);
                        Sheet sheet1 = null;
                        if (wb != null) {
                            sheet1 = wb.getSheetAt(0);
                        }

                        Sheet sheet2 = null;
                        if (wb != null) {
                            sheet2 = wb.getSheetAt(1);
                        }
                        if (sheet1 == null) {
                            return;
                        }
                        if (sheet2 == null) {
                            return;
                        }

                        dbAdapter.open();
                        //dbAdapter.delete();
                        ///////////dbAdapter.close();
                       // dbAdapter.open();
                        Excel2SQLiteHelper.insertExcelToSqlite(dbAdapter, sheet1);

                        dbAdapter.close();

                        dbAdapter.open();
                        Excel2SQLiteHelper.insertExcelToSqlite(dbAdapter, sheet2);
                        dbAdapter.close();

                    }
                } catch (Exception ex) {
                    lbl.setText(ex.getMessage().toString() + "Second");
                }

                ArrayList<HashMap<String, String>> myList = controller
                        .getCodes();
                if (myList.size() != 0) {
                    ListView lv = getListView();
                    ListAdapter adapter = new SimpleAdapter(Excel.this, myList,
                            R.layout.v, new String[]{CODE,Description,EQUIPMENT_NAME},
                            new int[]{R.id.txtcode, R.id.txtdescprip,
                                    R.id.txtequip});
                    setListAdapter(adapter);
                }
        }
    }
}