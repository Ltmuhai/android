package com.example.im.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.example.im.R;
import com.example.im.controller.adapter.InviteAdapter;
import com.example.im.model.Model;
import com.example.im.model.bean.InvationInfo;
import com.example.im.utils.Constant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.security.spec.ECField;
import java.util.List;

public class InviteActivity extends Activity {
    RecyclerView invite;
    InviteAdapter inviteAdapter;
    LocalBroadcastManager mLBM;
    private BroadcastReceiver ContactInviteChangedReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            invite.setAdapter(inviteAdapter);
            invite.addItemDecoration(new DividerItemDecoration(InviteActivity.this, DividerItemDecoration.VERTICAL));
            inviteAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        initView();
        initData();
    }


    private void initData() {
        List<InvationInfo> invationInfos= Model.getInstance().getDbManager().getInviteTableDao().getInvations();
        inviteAdapter=new InviteAdapter(invationInfos);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        invite.setLayoutManager(layoutManager);
        inviteAdapter.buttonListener=new InviteAdapter.ButtonListener() {
            @Override
            public void onAccept(InvationInfo invationInfo) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().acceptInvitation(invationInfo.getUser().getHxid());
                            Model.getInstance().getDbManager().getInviteTableDao().updateInvationStatus(InvationInfo.InvationStatus.INVITE_ACCEPT,invationInfo.getUser().getHxid());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InviteActivity.this,"接受了邀请",Toast.LENGTH_SHORT).show();
                                    invite.setAdapter(inviteAdapter);
                                    invite.addItemDecoration(new DividerItemDecoration(InviteActivity.this, DividerItemDecoration.VERTICAL));
                                    inviteAdapter.notifyDataSetChanged();
                                }
                            });

                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InviteActivity.this,"接受邀请失败",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
            }

            @Override
            public void onreject(InvationInfo invationInfo) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().declineInvitation(invationInfo.getUser().getHxid());
                            Model.getInstance().getDbManager().getInviteTableDao().removeInvation(invationInfo.getUser().getHxid());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InviteActivity.this,"拒绝了邀请",Toast.LENGTH_SHORT).show();
                                    invite.setAdapter(inviteAdapter);
                                    invite.addItemDecoration(new DividerItemDecoration(InviteActivity.this, DividerItemDecoration.VERTICAL));
                                    inviteAdapter.notifyDataSetChanged();
                                }
                            });
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InviteActivity.this,"拒绝邀请失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }
        };
        invite.setAdapter(inviteAdapter);
        invite.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        inviteAdapter.notifyDataSetChanged();

        mLBM=LocalBroadcastManager.getInstance(this);
        mLBM.registerReceiver(ContactInviteChangedReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHANGED));
    }

    private void initView() {
        invite=findViewById(R.id.lv_invite);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLBM.unregisterReceiver(ContactInviteChangedReceiver);
    }
}