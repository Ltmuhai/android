package com.example.im.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im.R;
import com.example.im.model.bean.InvationInfo;

import java.util.ArrayList;
import java.util.List;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.ViewHolder> {
    public ButtonListener buttonListener;
    private Context mcontext;
    private List<InvationInfo> mInvationInfos= new ArrayList<>();
    public InviteAdapter(List<InvationInfo> InvationInfos){
        mInvationInfos=InvationInfos;
    }
    public interface ButtonListener {
        void onAccept(InvationInfo invationInfo);
        void onreject(InvationInfo invationInfo);
    }

    @Override
    public InviteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InviteAdapter.ViewHolder holder, int position) {
        InvationInfo invationInfo=mInvationInfos.get(position);
        holder.name.setText(invationInfo.getUser().getName());
        holder.accept.setVisibility(View.GONE);
        holder.reject.setVisibility(View.GONE);
        if(invationInfo.getStatus()== InvationInfo.InvationStatus.New_INVITE){
            holder.reason.setText("添加好友");
            holder.accept.setVisibility(View.VISIBLE);
            holder.reject.setVisibility(View.VISIBLE);
        }else if(invationInfo.getStatus()== InvationInfo.InvationStatus.INVITE_ACCEPT){
            holder.reason.setText("接受邀请");
        }else if(invationInfo.getStatus()== InvationInfo.InvationStatus.INVITE_ACCEPT_BY_PEER){
            holder.reason.setText("邀请被接受");
        }
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onAccept(invationInfo);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onreject(invationInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInvationInfos.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,reason;
        Button accept,reject;
        public ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.invite_name);
            reason=view.findViewById(R.id.invite_reason);
            accept=view.findViewById(R.id.accept_invite);
            reject=view.findViewById(R.id.reject_invite);
        }
    }
}
