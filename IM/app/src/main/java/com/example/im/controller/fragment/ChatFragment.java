package com.example.im.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.im.controller.activity.ChatActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseIM;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.interfaces.OnItemClickListener;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.hyphenate.easeui.modules.conversation.model.EaseConversationInfo;

import java.util.List;

public class ChatFragment extends EaseConversationListFragment {
    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        EMConversation info = (EMConversation)conversationListLayout.getItem(position).getInfo();
        //添加点击事件实现逻辑
        Intent intent=new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID,info.conversationId() );
        startActivity(intent);
    }
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        EMMessageListener emMessageListener=new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                EaseIM.getInstance().getNotifier().notify(messages);
                conversationListLayout.refreshList();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {

            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> messages) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {

            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);
    }
}
