package com.sandyz.itemcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class FullscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainactivity);

    }

    public void searchButton(View view) {

        Intent letsSearch = new Intent(this, SearchButton.class);
        startActivity(letsSearch);

    }
}
