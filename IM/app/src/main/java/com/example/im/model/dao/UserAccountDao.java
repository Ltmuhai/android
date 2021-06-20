package com.example.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
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
        ContentValues values=new ;
        db.replace(UserAccountTable.TAB_Name,null,values);
    }
}
