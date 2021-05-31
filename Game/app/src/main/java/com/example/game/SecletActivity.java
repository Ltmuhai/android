package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class SecletActivity extends AppCompatActivity {
    RecyclerView result;
    resultAdapter resultAdapter;
    DatabaseHelper dbHelper;
    List<hobby> hobbyList=new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seclet);
        result=findViewById(R.id.sec_res);
        dbHelper=new DatabaseHelper(this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        Cursor cursor=db.query("hobby",null,null,null,null,null,null);
        if (cursor.moveToNext()){
            do {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String hobby=cursor.getString(cursor.getColumnIndex("hobby"));
                hobbyList.add(new hobby(name,hobby));
            }while (cursor.moveToNext());
        }cursor.close();

        resultAdapter=new resultAdapter(hobbyList);
        RecyclerView.LayoutManager layoutMgr=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        result.setLayoutManager(layoutMgr);
        result.setAdapter(resultAdapter);
        resultAdapter.listener=new resultAdapter.ListListener() {
            @Override
            public void onSelectUser(int pos,String name,String hobby) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(SecletActivity.this);
                dialog.setTitle("确定吗").setMessage("删除后不可回复").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.delete("hobby","name=? and hobby=?",new String[]{name,hobby});
                        hobbyList.remove(pos);
                        resultAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        };
        result.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        resultAdapter.notifyDataSetChanged();
    }
}