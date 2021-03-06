package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.model.Model;
import com.example.im.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends Activity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            if(isFinishing()){
                return;
            }

            toMainorlogin();
        }
    };

    private void toMainorlogin() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if(EMClient.getInstance().isLoggedInBefore()){
                    //to main
                    UserInfo account=Model.getInstance().getUserAccountDao().getAccountBYHId(EMClient.getInstance().getCurrentUser());
                    if(account==null){

                        ///////记得修改回Login
                        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Model.getInstance().loginSuccess(account);
                        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.sendMessageDelayed(Message.obtain(),2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}