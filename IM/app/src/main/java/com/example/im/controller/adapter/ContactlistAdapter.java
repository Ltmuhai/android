package com.example.im.controller.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im.R;
import com.example.im.model.bean.InvationInfo;
import com.example.im.model.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactlistAdapter extends RecyclerView.Adapter<ContactlistAdapter.ViewHolder> {
    private List<UserInfo> muserInfos= new ArrayList<>();
    public ContactlistAdapter(List<UserInfo> userInfos){
        muserInfos=userInfos;
    }
    public interface OnSelectListener {
        void OnSelect(UserInfo userInfo);
    }
    public OnSelectListener onSelectListener;
    @Override
    public ContactlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contactlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactlistAdapter.ViewHolder holder, int position) {
        UserInfo userInfo=muserInfos.get(position);
        holder.name.setText(userInfo.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectListener.OnSelect(userInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("M1", String.valueOf(muserInfos.size()));
        return muserInfos.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.friend_name);

        }
    }
}
