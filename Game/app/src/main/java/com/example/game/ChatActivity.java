package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    List<Msg> msgList=new ArrayList<>();
    Button username,send;
    EditText input;
    Intent intent,intent1;
    Bundle bundle,bundle1;
    RecyclerView msgRecyclerView;
    MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMsgs();
        username=findViewById(R.id.username);
        send=findViewById(R.id.send);
        input=findViewById(R.id.input_text);
        msgRecyclerView=findViewById(R.id.msg_list_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        bundle1=getIntent().getExtras();
        username.setText(bundle1.getString("username"));
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(ChatActivity.this,pro_infoActivity.class);

                bundle=new Bundle();
                bundle.putString("username",(bundle1.getString("username")));//用户名
                bundle.putInt("head_portrait",(bundle1.getInt("head_portrait")));//头像
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //输出文本框的内容到页面上
                String content=input.getText().toString();
                if (!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    input.setText("");
                }
            }
        });
    }
    private void initMsgs(){
        Msg msg1=new Msg("Hello 老哥.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("来了老弟",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("好好学习",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
    @Override
    public void onBackPressed(){
        //回传最后聊天记录；
        intent1=new Intent();
        bundle1.putString("last_chat",msgList.get(msgList.size()-1).getContent());
        intent1.putExtras(bundle1);
        setResult(Activity.RESULT_FIRST_USER,intent1);
        finish();
    }
}