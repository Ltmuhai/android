package com.example.l1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserEditActivity extends AppCompatActivity {
    Button btnCancel;
    Button btnEdit;
    EditText edit1;
    EditText edit2;
//    public interface Callback {
//        void onEditName(String name,String phone);
//    }
//    public UserEdit.Callback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnCancel  = findViewById(R.id.Bu_2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit = findViewById(R.id.Bu_1);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                Bundle bundle2=getIntent().getExtras();
                bundle1.putString("user",edit1.getText().toString());
                bundle1.putString("phone",edit2.getText().toString());
                bundle1.putInt("id", (bundle2.getInt("id")));
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();
//                if (callback != null) {
//                    callback.onEditName(edit1.getText().toString(),edit2.getText().toString());
//                }
            }
        });

    }
}