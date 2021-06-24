package com.example.im.controller.fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.controller.activity.AddContactActivity;
import com.example.im.controller.activity.ChatActivity;
import com.example.im.controller.activity.InviteActivity;
import com.example.im.controller.adapter.ContactlistAdapter;
import com.example.im.model.Model;
import com.example.im.model.bean.UserInfo;
import com.example.im.utils.Constant;
import com.example.im.utils.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.widget.EaseTitleBar;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {
    EaseTitleBar title;
    RecyclerView listView;
    ImageView red_hole;
    LocalBroadcastManager mLBM;
    LinearLayout friend_invite;
    ContactlistAdapter contactlistAdapter;
    private BroadcastReceiver ContactInviteChangeReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //更新红点
            red_hole.setVisibility(View.VISIBLE);
            SPUtils.getInstance().save(SPUtils.IS_NEW_INVITE,true);
        }
    };
    private BroadcastReceiver ContactChangeReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshContact();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragment_contact,null);
        //View headerview=View.inflate(getActivity(), R.layout.header_fragment_contact,null);

        title=view.findViewById(R.id.title_bar);
        listView=view.findViewById(R.id.list_view);
        red_hole=view.findViewById(R.id.red_hole);
        friend_invite=view.findViewById(R.id.friend_invite);

        title.setRightImageResource(R.drawable.add);
        //listView.addHeaderView(headerview);
        title.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        boolean isnewinvite= SPUtils.getInstance().getBoolean(SPUtils.IS_NEW_INVITE,false);
        red_hole.setVisibility(isnewinvite?View.VISIBLE:View.GONE);
        friend_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red_hole.setVisibility(View.GONE);
                SPUtils.getInstance().save(SPUtils.IS_NEW_INVITE,false);
                Intent intent=new Intent(getActivity(), InviteActivity.class);
                startActivity(intent);
            }
        });

        mLBM=LocalBroadcastManager.getInstance(getActivity());
        mLBM.registerReceiver(ContactInviteChangeReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHANGED));
        mLBM.registerReceiver(ContactChangeReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHANGED));
        //环信服务器获取联系人信息
        getContactFromHxServer();
        return view;
    }
    private void getContactFromHxServer(){
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> hxids= EMClient.getInstance().contactManager().getAllContactsFromServer();
                    if (hxids!=null&&hxids.size()>=0){
                        List<UserInfo> contacts=new ArrayList<>();
                        for (String hxid:hxids){
                            UserInfo userInfo=new UserInfo(hxid);
                            contacts.add(userInfo);
                        }
                        Model.getInstance().getDbManager().getContactTableDao().saveContacts(contacts);
                        if (getActivity()==null){return;}
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshContact();
                            }
                        });
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshContact() {
        List<UserInfo> contacts=Model.getInstance().getDbManager().getContactTableDao().getContacts();
        if (contacts!=null&&contacts.size()>=0){
            Log.w("M", String.valueOf(contacts.size()));
            contactlistAdapter=new ContactlistAdapter(contacts);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            listView.setLayoutManager(layoutManager);
            contactlistAdapter.onSelectListener=new ContactlistAdapter.OnSelectListener() {
                @Override
                public void OnSelect(UserInfo userInfo) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("你想干嘛？").setMessage("会话或者删除").setPositiveButton("会话", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getActivity(), ChatActivity.class);
                            intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID,userInfo.getHxid());
                            startActivity(intent);
                        }
                    }).setNegativeButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        EMClient.getInstance().contactManager().deleteContact(userInfo.getHxid());
                                        Model.getInstance().getDbManager().getContactTableDao().deleteContactBYHx(userInfo.getHxid());
                                        if (getActivity()==null){return;}
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(),"删除"+userInfo.getHxid(),Toast.LENGTH_SHORT).show();
                                                refreshContact();
                                            }
                                        });
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                        if (getActivity()==null){return;}
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(),"删除"+e,Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }).show();

                }
            };
            listView.setAdapter(contactlistAdapter);
            listView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            contactlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLBM.unregisterReceiver(ContactInviteChangeReceiver);
        mLBM.unregisterReceiver(ContactChangeReceiver);
    }
}
