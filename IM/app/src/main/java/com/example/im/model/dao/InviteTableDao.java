package com.example.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.model.bean.InvationInfo;
import com.example.im.model.bean.UserInfo;
import com.example.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class InviteTableDao {
    private DBHelper mHelper;

    public InviteTableDao(DBHelper dbHelper) {mHelper=dbHelper;
    }

    public void addInvition(InvationInfo invationInfo){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(InviteTable.COL_STATUS,invationInfo.getStatus().ordinal());
        values.put(InviteTable.COL_USER_HXID,invationInfo.getUser().getHxid());
        values.put(InviteTable.COL_USER_NAME,invationInfo.getUser().getName());
        db.replace(InviteTable.TAB_NAME,null,values);
    }
    public List<InvationInfo> getInvations(){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql="select * from "+InviteTable.TAB_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        List<InvationInfo> invationInfos=new ArrayList<>();
        while (cursor.moveToNext()){
            InvationInfo invationInfo=new InvationInfo();
            invationInfo.setStatus(int2invationStatus(cursor.getInt(cursor.getColumnIndex(InviteTable.COL_STATUS))));
            UserInfo userInfo=new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));
            invationInfo.setUser(userInfo);
            invationInfos.add(invationInfo);
        }
        cursor.close();
        return invationInfos;
    }
    private InvationInfo.InvationStatus int2invationStatus(int intStatus){
        if (intStatus==InvationInfo.InvationStatus.New_INVITE.ordinal()){
            return InvationInfo.InvationStatus.New_INVITE;
        }
        if (intStatus==InvationInfo.InvationStatus.INVITE_ACCEPT.ordinal()){
            return InvationInfo.InvationStatus.INVITE_ACCEPT;
        }
        if (intStatus==InvationInfo.InvationStatus.INVITE_ACCEPT_BY_PEER.ordinal()){
            return InvationInfo.InvationStatus.INVITE_ACCEPT_BY_PEER;
        }return null;
    }
    public void removeInvation(String Hxid){
        if (Hxid==null){
            return;
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        db.delete(InviteTable.TAB_NAME,InviteTable.COL_USER_HXID+"=?",new String[]{Hxid});
    }
    public void updateInvationStatus(InvationInfo.InvationStatus invationStatus,String hxid){
        if (hxid==null){
            return;
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(InviteTable.COL_STATUS,invationStatus.ordinal());

        db.update(InviteTable.TAB_NAME,values,InviteTable.COL_USER_HXID+"=?",new String[]{hxid});
    }
}
