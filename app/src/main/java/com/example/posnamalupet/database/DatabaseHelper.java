package com.example.posnamalupet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.posnamalupet.model.Product;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String NAME="database.db";
    private static final int VERSION=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE product_inventory(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," +
                "name TEXT UNIQUE," +
                "image INT DEFAULT 0," +
                "price DOUBLE," +
                "quantity INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS product_inventory");
        onCreate(db);
    }
    public void addProduct(String name,int image, double price, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name",name);
        value.put("image",image);
        value.put("price",price);
        value.put("quantity",quantity);
        db.insert("product_inventory",null,value);

        Product product = new Product(name, image, price, quantity);
        Product.addProductList(product);
    }
    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("product_invetory","id=?",new String[]{String.valueOf(id)});
    }


}
