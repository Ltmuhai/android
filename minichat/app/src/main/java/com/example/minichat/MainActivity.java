package com.example.minichat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView chatlist;
    ChatAdapter chatAdapter;
    Button search;
    EditText find;
    ImageButton add;
    List<User> userList = new ArrayList<>();
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatlist=findViewById(R.id.chatlist);
        add=findViewById(R.id.add);
        search=findViewById(R.id.search);
        find=findViewById(R.id.find);

        chatAdapter=new ChatAdapter(userList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chatlist.setLayoutManager(layoutManager);
        chatAdapter.listener=new ChatAdapter.ListListener() {
            @Override
            public void onSelectUser(int position, String username, int gender,int id) {
                intent=new Intent(MainActivity.this,ChatActivity.class);
                bundle=new Bundle();
                bundle.putInt("pos",position);
                bundle.putString("username",username);
                bundle.putInt("gender",gender);
                bundle.putInt("receice_id",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        chatlist.setAdapter(chatAdapter);
        chatlist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        chatAdapter.notifyDataSetChanged();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加联系人
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this, SearchActivity.class);
                bundle=new Bundle();
                bundle.putString("search",find.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}