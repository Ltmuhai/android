package com.example.minichat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public List<User> userList;
    public ChatAdapter(List<User> list) {
        userList = list;
    }
    public interface ListListener {
        void onSelectUser(int position, String username,int gender,int id);
    }
    ListListener listener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pro_chat, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        if (userList!=null){
            User user=userList.get(position);
            holder.username.setText(user.username);
            if (user.gender==0){
                //男头像
                holder.head.setImageResource(R.drawable.boy);
            }
            else {
                //女头像
                holder.head.setImageResource(R.drawable.girl);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onSelectUser(position,userList.get(position).username,userList.get(position).gender,userList.get(position).id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,last_chat;
        ImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            head=itemView.findViewById(R.id.image);
            //last_chat=itemView.findViewById(R.id.last_chat);
        }
    }
}

