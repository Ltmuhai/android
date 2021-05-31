package com.example.game;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class Dao {
    DatabaseHelper dbhobby;
    public Dao(Context context){
        dbhobby=new DatabaseHelper(context);
    }
    public void insert(char name,char hobby){
        SQLiteDatabase db=dbhobby.getWritableDatabase();
        String sql="insert into hobby.db(name,hobby) values(?,?)";
        db.execSQL(sql,new Object[]{name,hobby});
        db.close();
    }
    public void delete(char name){
        SQLiteDatabase db=dbhobby.getWritableDatabase();
        String sql="delete from hobby.db where name="+name+"";
        db.execSQL(sql);
        db.close();
    }
    public void updata(char name,char hobby){
        SQLiteDatabase db=dbhobby.getWritableDatabase();
        String sql="updata hobby.db set hobby="+hobby+" where name="+name+"";
        db.execSQL(sql);
        db.close();
    }
    public void query(){
        SQLiteDatabase db=dbhobby.getWritableDatabase();
        String sql="select * from hobby.db ";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            String hobby=cursor.getString(2);
        }
        db.close();
    }
}
