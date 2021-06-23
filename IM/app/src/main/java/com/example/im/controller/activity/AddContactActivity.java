package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.model.Model;
import com.example.im.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class AddContactActivity extends Activity {
    private TextView add_name,add_nick;
    private Button add_add;
    private EditText add_search;
    RelativeLayout add_rec;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initView();
        initListener();
    }

    private void initListener() {
        add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        add_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });
    }



    private void initView() {
        add_name=findViewById(R.id.add_name);
        add_add=findViewById(R.id.add_add);
        add_nick=findViewById(R.id.add_nick);
        add_search=findViewById(R.id.add_search);
        add_rec=findViewById(R.id.add_rec);
    }
    private void add() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(userInfo.getName(),"添加好友");
                    //Toast.makeText(AddContactActivity.this,"成功",Toast.LENGTH_SHORT).show();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                   // Toast.makeText(AddContactActivity.this,"失败"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void find() {
        String s=add_name.getText().toString();
        if (TextUtils.isEmpty(s)){
            Toast.makeText(AddContactActivity.this,"查无此人",Toast.LENGTH_SHORT).show();
            return;
        }
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                userInfo =new UserInfo(s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        add_rec.setVisibility(View.VISIBLE);
                        add_nick.setText(userInfo.getName());
                    }
                });
            }
        });
    }
}