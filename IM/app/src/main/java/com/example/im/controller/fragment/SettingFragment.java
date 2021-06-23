package com.example.im.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.controller.activity.LoginActivity;
import com.example.im.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class SettingFragment extends Fragment {
    private Button logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_setting,null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        logout.setText("退出登录");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                Model.getInstance().getDbManager().close();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                });

                            }

                            @Override
                            public void onError(int code, String error) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"退出失败"+error,Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int progress, String status) {

                            }
                        });
                    }
                });
            }
        });
    }

    private void initView(View view) {
        logout=view.findViewById(R.id.logout);

    }
}
