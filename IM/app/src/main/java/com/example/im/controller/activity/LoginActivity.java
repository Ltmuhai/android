package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.model.Model;
import com.example.im.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends Activity {
    Button login,regist;
    EditText name,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            regist();
            }
        });
    }

    private void login() {
        String loginname=name.getText().toString();
        String loginpwd=pwd.getText().toString();
        if (TextUtils.isEmpty(loginname)||TextUtils.isEmpty(loginpwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(loginname,loginpwd, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //成功
                        Model.getInstance().loginSuccess(new UserInfo(loginname));
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginname));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                    @Override
                    public void onError(int code, String error) {
                        //失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录失败"+error,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                        //登录过程处理
                    }
                });
            }
        });
    }

    private void regist() {
        String registname=name.getText().toString();
        String registpwd=pwd.getText().toString();
        if (TextUtils.isEmpty(registname)||TextUtils.isEmpty(registpwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(registname,registpwd);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        login=findViewById(R.id.bt_login);
        regist=findViewById(R.id.bt_regist);
        name=findViewById(R.id.et_login_name);
        pwd=findViewById(R.id.et_login_pwd);

    }
}