package com.emegra.mera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Praveen on 7/22/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){

        super(context, "chat", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="Create table chat_history (id INTEGER  PRIMARY KEY AUTOINCREMENT,user TEXT,message TEXT,tou TEXT) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(ContentValues cv){
        SQLiteDatabase db= this.getWritableDatabase();
        db.insert("chat_history","",cv);
        db.close();
    }
    public String getValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = new String[] { "message","user","tou"};
        Cursor c = db.query("chat_history", coloumns, null, null, null, null, null);
        // basically reading a database need cursor
        String result = " "; // since string is to be returned
        System.out.println("count of cursor=====>>"+c.getCount());
        // calling each row values
        int iRowName = c.getColumnIndex("message");
        int iRowNickName = c.getColumnIndex("user");
        if (c.moveToFirst()) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                result = result
                        + c.getString(iRowName) + " ,"
                        + c.getString(iRowNickName) + "\n";
                System.out.println(result + " <> " + iRowNickName + " <> " +
                        iRowName);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return result;
    }
    public String[] getValueByRows()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] coloumns = new String[] { "message","user","tou"};
        Cursor c = db.query("chat_history", coloumns, null, null, null, null, null);
        // basically reading a database need cursor
        String[] result=new String[100] ; // since string is to be returned
        System.out.println("count of cursor=====>>"+c.getCount());
        // calling each row values
        int iRowName = c.getColumnIndex("message");
        int iRowNickName = c.getColumnIndex("user");
        if (c.moveToFirst()) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
//                result = result
//                        + c.getString(iRowName) + " ,"
//                        + c.getString(iRowNickName) + "\n";
//                System.out.println(result + " <> " + iRowNickName + " <> " +
//                        iRowName);
                result[i]=c.getString(iRowName);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return result;
    }
}
