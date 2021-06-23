package com.example.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.model.bean.UserInfo;
import com.example.im.model.db.UserAccountDB;

public class UserAccountDao {
    private final UserAccountDB mHelper;

    public UserAccountDao(Context context) {
        mHelper = new UserAccountDB(context);
    }
    public void addAccount(UserInfo user){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        //执行添加操作
        ContentValues values=new ContentValues();
        values.put(UserAccountTable.COL_HXID,user.getHxid());
        values.put(UserAccountTable.COL_NAME,user.getName());
        values.put(UserAccountTable.COL_NICK,user.getNick());
        values.put(UserAccountTable.COL_PHOTO,user.getPhoto());

        db.replace(UserAccountTable.TAB_Name,null,values);
    }
    public UserInfo getAccountBYHId(String hxId){
        //获取数据库并查询
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql="select * from "+UserAccountTable.TAB_Name+" where "+UserAccountTable.COL_HXID+"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        //逐一取出并封装
        UserInfo userInfo=null;
        if (cursor.moveToNext()){
            userInfo=new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }
        //关闭资源并返回数据
        cursor.close();
        return userInfo;
    }
}
