package com.example.sparks.productinventarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class update extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    Database database;

    int id;
    String name;
    String str;
    String price;
    String des;
    String quan;

    String[] categories = { "Electronics section","clothing section", "kids section", "Accessories section", "General section"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(update.this);


        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        //categories = getIntent().getStringExtra("categories");
        price = getIntent().getStringExtra("price");
        des = getIntent().getStringExtra("Description");
        quan = getIntent().getStringExtra("Quantity");


        final EditText nam = (EditText) findViewById(R.id.edt_name);
        final Spinner cate = (Spinner) findViewById(R.id.spinner);
        final EditText pris = (EditText) findViewById(R.id.edt_price);
        final EditText desc = (EditText) findViewById(R.id.edt_des);
        final EditText quantity = (EditText) findViewById(R.id.edt_quantity);

       final Button bupdate = (Button) findViewById(R.id.btn_submit);



        cate.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        cate.setAdapter(aa);

        nam.setText(name);
        pris.setText(price);
        desc.setText(des);
        quantity.setText(quan);


        bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_models dm = new data_models();
                dm.setId(id);
                dm.setName(nam.getText().toString());
                dm.setCategories(str);
                dm.setPrice(pris.getText().toString());
                dm.setDescription(desc.getText().toString());
                dm.setQuantity(quantity.getText().toString());
                database.update(dm);

                Intent inte = new Intent(update.this, Alldata.class);
                startActivity(inte);
                Toast.makeText(update.this, "Data Update Successfully", Toast.LENGTH_SHORT).show();

            }

        });

    }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = categories[position];
                Toast.makeText(com.example.sparks.productinventarysystem.update.this, str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                 
            }
}