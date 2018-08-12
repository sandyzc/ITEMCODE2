package com.sandyz.itemcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appaholics.updatechecker.UpdateChecker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


public class FullscreenActivity extends AppCompatActivity {


    String code;
    EditText searchData;
    Button searc_Button;
    TextView version_info;

    private static final String TAG = "Main Screen";


    private StorageReference mStorageRef;

    private UpdateChecker checker;

    private int version = BuildConfig.VERSION_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);


        checker = new UpdateChecker(this, true);


//displaying app version
        version_info = (TextView) findViewById(R.id.versi);
        String version_name = BuildConfig.VERSION_NAME;
        version_info.setText("V " + version_name);

        searchData = (EditText) findViewById(R.id.query_data);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        //use firebase database weather db updae available ot not
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Db_Version");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getValue(Long.class);
                //check for update
                try {
                    if (value > version) {
                        Toast.makeText(getApplicationContext(), "Update available", Toast.LENGTH_LONG).show();
                        download();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searc_Button = (Button) findViewById(R.id.searchButton);
        searc_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchData.getText().length() <= 0) {
                    searchData.setError("Enter KeyWOrd");
                } else {

                    Intent letsSearch = new Intent(FullscreenActivity.this, SearchButton.class);
                    Bundle data = new Bundle();
                    code = searchData.getText().toString();
                    data.putString("Code", code);
                    letsSearch.putExtras(data);
                    startActivity(letsSearch);
                }
            }
        });
        searc_Button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent upload_data = new Intent(FullscreenActivity.this, Excel.class);
                startActivity(upload_data);
                return false;
            }
        });


    }

    private void download() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference updaeRef = database.getReference("Update_url");
        updaeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value != null) {
                    checker.download(value);
                    checker.install();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
