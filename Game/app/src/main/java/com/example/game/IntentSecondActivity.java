package com.example.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntentSecondActivity extends AppCompatActivity {
    Button BU_Second;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_second);
        BU_Second=findViewById(R.id.BU_2);
        intent=new Intent();
        bundle=getIntent().getExtras();
        BU_Second.setText(bundle.getString("data"));
        BU_Second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("data","我是回传的信息");
                setResult(Activity.RESULT_FIRST_USER,intent);
                finish();
            }
        });
    }
}