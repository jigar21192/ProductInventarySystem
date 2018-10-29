package com.example.sparks.productinventarysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int db_version=1;
    private static final String db_name="ProductInventarySystem";
    private static final String Table_name="Product";
    private static final String Id="id";
    private static final String Name="name";
    private static final String Categories="categories";
    private static final String Price="price";
    private static final String Description="des";
    private static final String Quantity="quantity";


    Database(Context context)
    {
        super(context,db_name,null,db_version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String st= " CREATE TABLE " + Table_name + " ( " + Id + " INTEGER PRIMARY KEY, "+ Name + " TEXT, "
                + Categories + " TEXT, "+ Price + " TEXT," +Description +" TEXT,"+ Quantity + " TEXT"+")";
        db.execSQL(st);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public int insert(data_models dm) {

        SQLiteDatabase d=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,dm.getName());
        values.put(Categories,dm.getCategories());
        values.put(Price,dm.getPrice());
        values.put(Description,dm.getDescription());
        values.put(Quantity,dm.getQuantity());


        int i=(int) d.insert(Table_name,null,values);
        Log.e("Row",">>>"+i);
        d.close();

        return i;

    }


    public List<data_models> getAllDatabase() {
        List<data_models>data_models=new ArrayList<data_models>();
        String selectquiry = "SELECT * FROM " + Table_name;
        SQLiteDatabase d=this.getReadableDatabase();
        Cursor cursor =d.rawQuery(selectquiry,null);

        if(cursor.getCount()>0) {
            if (cursor.moveToFirst()) {
                do {
                    data_models dm = new data_models();
                    dm.setId(Integer.parseInt(cursor.getString(0)));
                    dm.setName(cursor.getString(1));
                    dm.setCategories(cursor.getString(2));
                    dm.setPrice(cursor.getString(3));
                    dm.setDescription(cursor.getString(4));
                    dm.setQuantity(cursor.getString(5));

                    data_models.add(dm);
                } while (cursor.moveToNext());

            }
        }

        return data_models;
    }

    public void deletdata(int id) {
        SQLiteDatabase d=this.getWritableDatabase();
        d.delete(Table_name,Id+" = ?",new String[]{String.valueOf(id)});
        d.close();
    }

    public int update(data_models dm) {

        SQLiteDatabase d=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put(Name,dm.getName());
        v.put(Categories,dm.getCategories());
        v.put(Price,dm.getPrice());
        v.put(Description,dm.getDescription());
        v.put(Quantity,dm.getQuantity());

        return d.update(Table_name,v,Id + " = ?",new String[]{String.valueOf(dm.getId())});


    }
}
