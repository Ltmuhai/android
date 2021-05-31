package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class pro_infoActivity extends AppCompatActivity {
    ImageView user_image;
    TextView user_name;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_info);
        user_image=findViewById(R.id.userimage);
        user_name=findViewById(R.id.username);
        bundle=getIntent().getExtras();
        user_image.setImageResource(bundle.getInt("head_portrait"));
        user_name.setText(bundle.getString("username"));

        //将上个页面申请来的用户信息在这里展示。
    }
}