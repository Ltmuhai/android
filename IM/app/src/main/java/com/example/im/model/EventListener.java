package com.example.im.model;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.im.model.bean.InvationInfo;
import com.example.im.model.bean.UserInfo;
import com.example.im.utils.Constant;
import com.example.im.utils.SPUtils;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

//全局事件监听类
public class EventListener {
    private Context mcontext;
    private final LocalBroadcastManager mLBM;
    public EventListener(Context context){
        mcontext=context;
        mLBM= LocalBroadcastManager.getInstance(mcontext);
        //注册联系人变化的监听
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }
    private final EMContactListener emContactListener=new EMContactListener() {
        //联系人增加后
        @Override
        public void onContactAdded(String hxid) {
            Model.getInstance().getDbManager().getContactTableDao().saveContact(new UserInfo(hxid));
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }
        //联系人删除后
        @Override
        public void onContactDeleted(String hxid) {
            Model.getInstance().getDbManager().getContactTableDao().deleteContactBYHx(hxid);
            Model.getInstance().getDbManager().getInviteTableDao().removeInvation(hxid);
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));//发送广播
        }
        //联系人邀请后
        @Override
        public void onContactInvited(String hxid, String reason) {
            //数据库更新
            InvationInfo invationInfo=new InvationInfo();
            invationInfo.setUser(new UserInfo(hxid));
            invationInfo.setStatus(InvationInfo.InvationStatus.New_INVITE);//新邀请
            Model.getInstance().getDbManager().getInviteTableDao().addInvition(invationInfo);
            //红点的处理
            SPUtils.getInstance().save(SPUtils.IS_NEW_INVITE,true);
            //发送邀请信息的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));
        }
        //联系人接受后
        @Override
        public void onFriendRequestAccepted(String hxid) {
            InvationInfo invationInfo=new InvationInfo();
            invationInfo.setUser(new UserInfo(hxid));
            invationInfo.setStatus(InvationInfo.InvationStatus.INVITE_ACCEPT_BY_PEER);
            Model.getInstance().getDbManager().getInviteTableDao().addInvition(invationInfo);
            SPUtils.getInstance().save(SPUtils.IS_NEW_INVITE,true);
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));
        }
        //联系人拒绝后
        @Override
        public void onFriendRequestDeclined(String hxid) {
            SPUtils.getInstance().save(SPUtils.IS_NEW_INVITE,true);
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHANGED));
        }
    };
}
