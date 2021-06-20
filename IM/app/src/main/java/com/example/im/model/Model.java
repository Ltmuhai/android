package com.example.im.model;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {

    private static Model model=new Model();
    private Context mcontext;
    private ExecutorService executorService= Executors.newCachedThreadPool();
    private Model(){

    }
    public static Model getInstance(){
        return model;
    }
    public void init(Context context){
        mcontext=context;
    }
    //获取全局线程池
    public  ExecutorService getGlobalThreadPool(){
        return executorService;
    }
}
