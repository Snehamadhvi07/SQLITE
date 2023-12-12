package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String dbname="stdb";
    private static final String tbname="student";
    private static final int dbversion=1;

    public Dbhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context,dbname,factory,dbversion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbname+"(uname VARCHAR(10),pw VARCHAR(10))"+";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int i) {
db.execSQL("DROP TABLE IF EXISTS "+tbname);
onCreate(db);
    }

    public long adduser(String name,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uname",name);
        cv.put("pw",pass);
        long result=db.insert(tbname,null,cv);
        db.close();
        return result;
    }

    public void update(String name,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+tbname+"SET pw="+pass+""+"WHERE uname="+name+";");
        db.close();
    }

    public void delete(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+tbname+"WHERE uname="+name+";");
        db.close();
    }

    public String display(Context ctx){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT *FROM "+tbname,null);
        String finalres="";
        while(cursor.moveToNext()){
            finalres+=cursor.getString(0)+":"+cursor.getString(1);
        }
                return finalres;
    }
}

