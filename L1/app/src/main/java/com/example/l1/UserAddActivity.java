package com.example.l1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserAddActivity extends AppCompatActivity {
    Button btnCancel;
    Button btnAdd;
    EditText edit1;
    EditText edit2;


//
//    public interface Callback {
//        void onAddName(String name,String phone);
//    }
//    public Callback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        edit1 = findViewById(R.id.edit3);
        edit2 = findViewById(R.id.edit4);
        btnAdd = findViewById(R.id.Bu_3);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("user",edit1.getText().toString());
                bundle1.putString("phone",edit2.getText().toString());
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();
//                if (callback != null) {
//                    callback.onAddName(edit1.getText().toString(),edit2.getText().toString());
//                }
            }
        });

        btnCancel = findViewById(R.id.Bu_4);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("user",null);
                bundle1.putString("phone",null);
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}