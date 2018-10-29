package com.example.sparks.productinventarysystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class Base_Adapter extends BaseAdapter {
    List<data_models> data_models;
    LayoutInflater inflater;
    Database d;
    Button gupdate;
    Button delete;
    Context context;

    public Base_Adapter(Context context, List<data_models> data_models) {

        this.context=context;
        this.data_models=data_models;
        inflater=LayoutInflater.from(context);
        d=new Database(context);


    }

    @Override
    public int getCount() {
        return data_models.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.data_item,null);
        TextView name = (TextView)convertView.findViewById(R.id.txt_name);
        TextView cate = (TextView)convertView.findViewById(R.id.txt2);
        TextView price = (TextView)convertView.findViewById(R.id.txt3);
        TextView des = (TextView)convertView.findViewById(R.id.txt);
        TextView qua = (TextView)convertView.findViewById(R.id.txt4);


        gupdate = (Button)convertView.findViewById(R.id.btnupdate);
        delete = (Button)convertView.findViewById(R.id.btndelete);


        gupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hhhhh", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(context,update.class);
                i.putExtra("id",data_models.get(position).getId());
                i.putExtra("name",data_models.get(position).getName());
               // i.putExtra("categories",data_models.get(position).getCategories());
                i.putExtra("price",data_models.get(position).getPrice());
                i.putExtra("Description",data_models.get(position).getDescription());
                i.putExtra("Quantity",data_models.get(position).getQuantity());

                context.startActivity(i);


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.deletdata(data_models.get(position).getId());
                Intent in=new Intent(context,Alldata.class);
                context.startActivity(in);
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
            }
        });


        data_models dm=data_models.get(position);
        name.setText(dm.getName());
        cate.setText(dm.getCategories());
        price.setText(dm.getPrice());
        des.setText(dm.getDescription());
        qua.setText(dm.getQuantity());



        return convertView;
    }
}
