package com.example.game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentFirstActivity extends AppCompatActivity {
    Button BU_First,BU_3;
    Intent intent;
    Bundle bundle;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BU_First.setText(data.getExtras().getString("data"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_first);
        BU_First=findViewById(R.id.BU_1);
        BU_3=findViewById(R.id.BU_3);
        BU_First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(IntentFirstActivity.this,IntentSecondActivity.class);
                bundle=new Bundle();
                bundle.putString("data","这是我传递的信息");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
        BU_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:+8618933820820");
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body", "The SMS text");
                startActivity(intent);
            }
        });
    }
}