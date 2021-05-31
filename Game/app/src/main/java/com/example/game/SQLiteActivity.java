package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLiteActivity extends AppCompatActivity {
    EditText name,hobby;
    Button add,seclet;
    Intent intent;
    DatabaseHelper dbhobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dbhobby=new DatabaseHelper(this);
        add=findViewById(R.id.Bu_add);
        seclet=findViewById(R.id.Bu_seclet);
        name=findViewById(R.id.name);
        hobby=findViewById(R.id.hobby);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbhobby.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name",name.getText().toString());
                values.put("hobby",hobby.getText().toString());
                db.insert("hobby",null,values);
                name.setText("");
                hobby.setText("");
            }
        });
        seclet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到结果页面
                intent=new Intent(SQLiteActivity.this,SecletActivity.class);
                startActivity(intent);
            }
        });

    }
}