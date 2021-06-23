package com.example.im.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.im.IMApplication;

public class SPUtils {
    private static SPUtils instance=new SPUtils();
    private static SharedPreferences mSp;
    private  SPUtils(){

    }
    private static SPUtils getInstance(){
        if (mSp!=null){
            mSp=IMApplication.getGlobalApplication().getSharedPreferences("im", Context.MODE_PRIVATE);
        }


        return instance;
    }
    //保存
    public void save (String key,Object value){
        if (value instanceof String){
            mSp.edit().putString(key,(String)value).commit();
        }else if (value instanceof Boolean){
            mSp.edit().putBoolean(key,(Boolean) value).commit();
        }else if (value instanceof Integer){
            mSp.edit().putInt(key,(Integer) value).commit();
        }
    }
    //获取数据的方法
    public String getString(String key,String defvalue){
        return mSp.getString(key,defvalue);
    }
    public Boolean getBoolean(String key,Boolean defvalue){
        return mSp.getBoolean(key,defvalue);
    }public Integer getInteger(String key,Integer defvalue){
        return mSp.getInt(key,defvalue);
    }
}
