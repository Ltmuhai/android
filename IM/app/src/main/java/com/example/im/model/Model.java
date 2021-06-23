package com.example.im.model;

import android.content.Context;

import com.example.im.model.bean.UserInfo;
import com.example.im.model.dao.UserAccountDao;
import com.example.im.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {

    private static Model model=new Model();
    private Context mcontext;
    private ExecutorService executorService= Executors.newCachedThreadPool();
    private UserAccountDao userAccountDao;
    DBManager dbManager;

    private Model(){
    }
    public static Model getInstance(){
        return model;
    }
    public void init(Context context){
        mcontext=context;
        userAccountDao = new UserAccountDao(mcontext);
    }
    //获取全局线程池
    public  ExecutorService getGlobalThreadPool(){
        return executorService;
    }

    public void loginSuccess(UserInfo account) {
        if (account==null){
            return;
        }
        if (dbManager!=null){
           dbManager.close();
        }
        dbManager=new DBManager(mcontext, account.getName());
    }
    public  DBManager getDbManager(){
        return dbManager;
    }
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }
}
