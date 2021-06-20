package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.im.R;
import com.example.im.model.Model;
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
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                if(EMClient.getInstance().isLoggedInBefore()){
//                    //to main
//                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                }
//                finish();
//            }
//        }.start();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if(EMClient.getInstance().isLoggedInBefore()){
                    //to main
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
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