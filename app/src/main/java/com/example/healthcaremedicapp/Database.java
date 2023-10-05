package com.example.healthcaremedicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username, String email,String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("users",null,contentValues);
        database.close();
    }
    //Login functionality code two parameter is passed username, password here..
    public int login(String username,String password){
        int result=0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if (c.moveToFirst()){
            result=1;
        }
        return result;
    }
    //Add to Cart database functionality implemented here..

    public void addCart(String username, String product, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();

    }
    //Existing Medicine to check is there in Cart or Not..

    public int checkCart(String username, String product){
        int result=0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?",str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;

    }
    //Remove Medicine from the cart..

    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }

    //Function to get user getCart data in CartLabActivity..

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;

        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?",str);
        if (c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    //here we have create add order fuction in database

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){

        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("otype",otype);
        cv.put("amount",price);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?",str);
        if (c.moveToFirst()){
            do {
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    /*
    So basically here in checkAppointmentExists db we see that existing appointment of user is there or not
    if there then it will not take the appointment..
     */
    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time){
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if (c.moveToFirst()){
            result=1;

        }
        db.close();
        return result;

    }

}
