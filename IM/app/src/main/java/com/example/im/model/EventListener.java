package com.example.im.model;

import android.content.Context;

import com.example.im.model.bean.UserInfo;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

//全局事件监听类
public class EventListener {
    private Context mcontext;
    LocalBroadcastManager localBroadcastManager
    private EventListener(Context context){
        mcontext=context;
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }
    private final EMContactListener emContactListener=new EMContactListener() {
        //联系人增加后
        @Override
        public void onContactAdded(String hxid) {
            Model.getInstance().getDbManager().getContactTableDao().saveContact(new UserInfo(hxid));
        }
        //联系人删除后
        @Override
        public void onContactDeleted(String username) {

        }
        //联系人邀请后
        @Override
        public void onContactInvited(String username, String reason) {

        }
        //联系人接受后
        @Override
        public void onFriendRequestAccepted(String username) {

        }
        //联系人拒绝后
        @Override
        public void onFriendRequestDeclined(String username) {

        }
    };
}
