package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.im.R;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.modules.chat.EaseChatFragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class ChatActivity extends FragmentActivity {
    String mhxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        EaseChatFragment easeChatFragment = new EaseChatFragment();
        mhxid=getIntent().getStringExtra(EaseConstant.EXTRA_CONVERSATION_ID);
        easeChatFragment.setArguments(getIntent().getExtras());
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_main,easeChatFragment).commit();
    }
}