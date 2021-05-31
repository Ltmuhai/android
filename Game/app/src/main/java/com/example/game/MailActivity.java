package com.example.game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MailActivity extends AppCompatActivity {
    RecyclerView listView;
    ListAdapter listAdapter;
    List<User> userList = new ArrayList<>();
    int headboy,headgirl;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        headboy=R.drawable.headboy;
        headgirl=R.drawable.headgirl;
        listView = findViewById(R.id.listView);
        userList.add(new User("LWH",headboy,""));
        userList.add(new User("LWS",headboy,""));
        userList.add(new User("DTY",headgirl,""));
        listAdapter=new ListAdapter(userList);
        RecyclerView.LayoutManager layoutMgr = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        listView.setLayoutManager(layoutMgr);
        listAdapter.listener=new ListAdapter.ListListener() {
            @Override
            public void onSelectUser(int position, String username, String last_chat, int head_portrait) {
                intent=new Intent(MailActivity.this,ChatActivity.class);
                bundle=new Bundle();
                bundle.putInt("pos",position);
                bundle.putString("username",username);
                bundle.putString("last_chat",last_chat);
                bundle.putInt("head_portrait",head_portrait);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        };
        listView.setAdapter(listAdapter);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userList.get(data.getExtras().getInt("pos")).last_chat=data.getExtras().getString("last_chat");
        listAdapter.notifyDataSetChanged();
    }
}