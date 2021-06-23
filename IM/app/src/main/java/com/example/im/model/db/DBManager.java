package com.example.im.model.db;

import android.content.Context;

import com.example.im.model.dao.ContactTableDao;
import com.example.im.model.dao.InviteTableDao;

public class DBManager {
    private final DBHelper dbHelper;
    private final ContactTableDao contactTableDao;
    private final InviteTableDao inviteTableDao;
    public DBManager(Context context,String name){
        dbHelper = new DBHelper(context, name);
        contactTableDao=new ContactTableDao(dbHelper);
        inviteTableDao=new InviteTableDao(dbHelper);
    }
    public ContactTableDao getContactTableDao(){
        return contactTableDao;
    }
    public InviteTableDao getInviteTableDao(){
        return inviteTableDao;
    }
    public void close(){
        dbHelper.close();
    }
}
