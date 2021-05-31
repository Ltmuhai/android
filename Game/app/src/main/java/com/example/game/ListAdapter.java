package com.example.game;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public List<User> userList;
    public ListAdapter(List<User> list) {
        userList = list;
    }
    public interface ListListener {
        void onSelectUser(int position, String username,String last_chat,int head_portrait);
    }
    ListListener listener;
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        if (userList!=null){
            User user = userList.get(position);
            holder.username.setText(user.username);
            holder.head_portrait.setImageResource(user.head_portrait);
            if(user.last_chat!=""){
                holder.last_chat.setText(user.last_chat);
            }else{
                holder.last_chat.setText("暂无聊天记录");
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSelectUser(position, userList.get(position).username,userList.get(position).last_chat,userList.get(position).head_portrait);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,last_chat;
        ImageView head_portrait;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.name);
            head_portrait=itemView.findViewById(R.id.image);
            //head_portrait.setImageResource(R.drawable.headboy);
            last_chat=itemView.findViewById(R.id.last_chat);
        }
    }
}
