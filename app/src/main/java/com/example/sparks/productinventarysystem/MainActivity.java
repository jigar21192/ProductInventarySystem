package com.example.sparks.productinventarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener  {

    EditText name,price,description,quantity;
    Spinner spinner;
    Button btnSubmit,btnshowdata;
    String[] categories = { "Electronics section","clothing section", "kids section", "Accessories section", "General section"};
    String str;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.edt_name);
        price = (EditText)findViewById(R.id.edt_price);
        description = (EditText)findViewById(R.id.edt_des);
        quantity = (EditText)findViewById(R.id.edt_quantity);

        spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        btnSubmit = (Button)findViewById(R.id.btn_submit);
        btnshowdata = (Button)findViewById(R.id.btn_submit2);

        db=new Database(MainActivity.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String na = name.getText().toString();
                String pri = price.getText().toString();
                String des=description.getText().toString();
                String qun = quantity.getText().toString();

               data_models dm = new data_models();
               dm.setName(na);
               dm.setCategories(str);
               dm.setPrice(pri);
               dm.setDescription(des);
               dm.setQuantity(qun);

               db.insert(dm);

               Toast.makeText(getApplicationContext(),"Data Submited Successfully",Toast.LENGTH_SHORT).show();

            }
        });

        btnshowdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Alldata.class);
                startActivity(intent);
            }
        });





    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    str=categories[position];
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
