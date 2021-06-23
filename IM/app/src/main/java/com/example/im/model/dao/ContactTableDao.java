package com.example.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts;
import android.provider.ContactsContract;

import com.example.im.model.bean.UserInfo;
import com.example.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactTableDao {
    private DBHelper mHelper;
    public ContactTableDao(DBHelper helper){
        mHelper=helper;
    }
    public List<UserInfo> getContacts(){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql="select * from "+ContactTable.TAB_name;
        Cursor cursor=db.rawQuery(sql,null);
        List<UserInfo> users=new ArrayList<>();
        while (cursor.moveToNext()){
            UserInfo userInfo=new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            users.add(userInfo);
            }
        cursor.close();
        return users;
    }
    public UserInfo getContactBYHX(String hxid){
        if (hxid==null){
            return null;
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql="select * from "+ContactTable.TAB_name+" where "+ContactTable.COL_HXID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{hxid});
        UserInfo userInfo=null;
        if (cursor.moveToNext()){
            userInfo=new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            }
        cursor.close();
        return userInfo;
    }
    public  List<UserInfo> getContactsBYHX(List<String> hxids){
        if (hxids==null||hxids.size()<=0){
            return null;
        }
        List<UserInfo> contacts=new ArrayList<>();
        for (String hxid:hxids){
            UserInfo contact=getContactBYHX(hxid);
            contacts.add(contact);
        }return contacts;
    }
    public void saveContact(UserInfo user){
        if(user ==null){
            return;
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(ContactTable.COL_HXID,user.getHxid());
        values.put(ContactTable.COL_NAME,user.getName());
        values.put(ContactTable.COL_NICK,user.getNick());
        values.put(ContactTable.COL_PHOTO,user.getPhoto());

        db.replace(ContactTable.TAB_name,null,values);
    }
    public  void saveContacts(List<UserInfo> contacts){
        if (contacts==null||contacts.size()<=0){
            return;
        }
        for (UserInfo contact:contacts)
            saveContact(contact);
    }
    public void deleteContactBYHx(String hxid){
        if (hxid==null){
            return;
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        db.delete(ContactTable.TAB_name,ContactTable.COL_HXID+"=?",new String[]{hxid});
    }

}
