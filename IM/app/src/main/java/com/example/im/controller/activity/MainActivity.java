package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.im.R;
import com.example.im.controller.fragment.ChatFragment;
import com.example.im.controller.fragment.ContactFragment;
import com.example.im.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup radioGroup;
    private ChatFragment chatFragment;
    private ContactFragment contactFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment=null;
                switch (checkedId){
                    case R.id.rg_main_chat:
                        fragment=chatFragment;
                        break;
                    case R.id.rg_main_contact:
                        fragment=contactFragment;
                        break;
                    case R.id.rg_main_setting:
                        fragment=settingFragment;
                        break;
                }
                switchFragment(fragment);
            }
        });
        radioGroup.check(R.id.rg_main_chat);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    private void initData() {
        chatFragment = new ChatFragment();
        contactFragment = new ContactFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        radioGroup=findViewById(R.id.rg_main);
    }
}