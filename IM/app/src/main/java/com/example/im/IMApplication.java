package com.example.im;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

import com.example.im.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseIM;

public class IMApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化环信的ui
        EMOptions option=new EMOptions();
        option.setAcceptInvitationAlways(false);
        option.setAutoAcceptGroupInvitation(false);
        EMClient.getInstance().init(getApplicationContext(), option);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        Model.getInstance().init(this);
        mContext=this;
        //EaseIM初始化
        if(EaseIM.getInstance().init(getApplicationContext(), option)){
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);
            //EaseIM初始化成功之后再去调用注册消息监听的代码 ...
        }
    }
    //获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}
