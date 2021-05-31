package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int PERMISSION_REQ_ID = 21;
    public static final String[] REQUEST_PERMISSION = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    Button life;
    Button finger_guessing;
    Button mail_list;
    Button intent_ACT;
    Button web;
    Button SQLite;
    Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        life=findViewById(R.id.Life);
        finger_guessing=findViewById(R.id.finger_guessing);
        mail_list=findViewById(R.id.mail_list);
        intent_ACT=findViewById(R.id.intent_ACT);
        web=findViewById(R.id.web);
        SQLite=findViewById(R.id.SQLite);
        camera=findViewById(R.id.Camera);
        setListeners();

        if (checkSelfPermission(REQUEST_PERMISSION[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUEST_PERMISSION[1], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUEST_PERMISSION[2], PERMISSION_REQ_ID)) {
        }
    }
    private void setListeners(){
        OnClick onClick = new OnClick();
        //life.setOnClickListener(onClick);
        mail_list.setOnClickListener(onClick);
        finger_guessing.setOnClickListener(onClick);
        intent_ACT.setOnClickListener(onClick);
        web.setOnClickListener(onClick);
        SQLite.setOnClickListener(onClick);
        camera.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{
        Intent intent=null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.finger_guessing:
                    //跳转到猜拳游戏
                    intent= new Intent(MainActivity.this,finger_guessingActivity.class);
                    break;
                case R.id.mail_list:
                    //跳转到通讯录
                    intent= new Intent(MainActivity.this,MailActivity.class);
                    break;
                case R.id.intent_ACT:
                    //跳转到intent1画面
                    intent=new Intent(MainActivity.this,IntentFirstActivity.class);
                    break;
                case R.id.web:
                    //跳转到网络编程页面
                    intent=new Intent(MainActivity.this,webActivity.class);
                    break;
                case R.id.SQLite:
                    //跳转到数据库界面
                    intent=new Intent(MainActivity.this,SQLiteActivity.class);
                    break;
                case R.id.Camera:
                    //跳转到摄像界面
                    intent=new Intent(MainActivity.this,CameralistActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
    private boolean checkSelfPermission(String permission, int request_code) {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, REQUEST_PERMISSION, request_code);
        }
        return true;
    }
}