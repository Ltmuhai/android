package com.example.l1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    ListAdapter listAdapter;
    List<User> userList= new ArrayList<>();
    EditText exit1, exit2;
    String user, phone;
    ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        userList.add(new User("LWH","18933888888"));
        userList.add(new User("LWS","13266405377"));
        listAdapter = new ListAdapter(userList);
        RecyclerView.LayoutManager layoutMgr = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        listView.setLayoutManager(layoutMgr);


        listAdapter.listener = new ListAdapter.ListListener() {
            @Override
            public void onSelectUser(final int pos, String name,String phone) {
                UserEdit dialog = new UserEdit(MainActivity.this);
                dialog.callback = new UserEdit.Callback() {
                    @Override
                    public void onEditName(String name,String phone) {
                        userList.get(pos).name = name;
                        userList.get(pos).phone = phone;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                };
                dialog.show();
            }
            public void del(final int pos, String name,String phone){
                AlertDialog.Builder builder1 =new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("确定吗？").setMessage("删除后不可恢复").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userList.remove(pos);
                        listAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
//                userList.remove(pos);
//                listAdapter.notifyDataSetChanged();
            }
        };
        listView.setAdapter(listAdapter);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listAdapter.notifyDataSetChanged();

        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAdd dialog2 = new UserAdd(MainActivity.this);
                dialog2.callback = new UserAdd.Callback() {
                    @Override
                    public void onAddName(String name,String phone) {
                        userList.add(new User(name, phone));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                };dialog2.show();
            }

        });
    }




//    public void addonclick (View view){
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        final View v = getLayoutInflater().inflate(R.layout.add, null);
//        builder.setView(v);
//        builder.show();
//    }
//    public void add(View view){
//        exit1 =findViewById(R.id.edit1);
//        exit2 =findViewById(R.id.edit2);
////        user=exit1.getText().toString();
////        phone=exit2.getText().toString();
//        userList.add(new User(exit1.getText().toString(),exit2.getText().toString()));
//        listAdapter.notifyDataSetChanged();
//    }
//    public void Cancel(AlertDialog dialog){
//        dialog.dismiss();
//    }
}