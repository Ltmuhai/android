package com.example.minichat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    Button username,send;
    RecyclerView msg_list;
    ImageButton take_picture;
    EditText input;
    Bundle bundle;
    List<Msg> msgList=new ArrayList<>();
    MsgAdapter msgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username=findViewById(R.id.username);
        send=findViewById(R.id.send);
        msg_list=findViewById(R.id.msg_list_view);
        take_picture=findViewById(R.id.take_picture);
        input=findViewById(R.id.input_text);
        bundle=getIntent().getExtras();
        username.setText(bundle.getString("username"));

        msgAdapter=new MsgAdapter(msgList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msg_list.setLayoutManager(layoutManager);
        msg_list.setAdapter(msgAdapter);
        msg_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        msgAdapter.notifyDataSetChanged();

        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到拍摄功能并回传照片；
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=input.getText().toString();
                if (!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);                                         //todu：传入数据库
                    msgAdapter.notifyItemInserted(msgList.size()-1);
                    msg_list.scrollToPosition(msgList.size()-1);
                    input.setText("");
                }
            }
        });
    }
}