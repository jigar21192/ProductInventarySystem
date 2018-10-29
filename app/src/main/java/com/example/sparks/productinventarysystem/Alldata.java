package com.example.sparks.productinventarysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class Alldata extends AppCompatActivity {

    ListView lv;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldata);

        database = new Database(Alldata.this);
        lv = (ListView)findViewById(R.id.listview);

        List<data_models> data_models = database.getAllDatabase();

        Base_Adapter base_adapter = new Base_Adapter(Alldata.this,data_models);
        lv.setAdapter(base_adapter);




    }
}
