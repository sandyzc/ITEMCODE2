package com.sandyz.itemcode;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.sandyz.itemcode.database.XlsConec;

import java.util.ArrayList;

/**
 * Created by santosh on 21-07-2017.
 */

public class SearchButton extends AppCompatActivity {


    ListView listView;
    XlsConec db= new XlsConec(this);
    SQLiteDatabase database;
    ArrayList<Beans> arrayList;
    EditText searck_keyword;
    SearchAdapter adapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        listView = (ListView)findViewById(R.id.searlist);
        searck_keyword= (EditText)findViewById(R.id.searchkeyword);


        db= new XlsConec(getApplicationContext());
        database = db.getReadableDatabase();
        arrayList= db.searchcode();
        adapter = new SearchAdapter(arrayList,this);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.updaateDb){

            Intent gotoDbUpdater = new Intent(this, Excel.class);
            startActivity(gotoDbUpdater);
        }

        return super.onOptionsItemSelected(item);
    }

    public void keyWordSearch(View view) {

            retreivedata();
    }

    private void retreivedata(){


    }
}
