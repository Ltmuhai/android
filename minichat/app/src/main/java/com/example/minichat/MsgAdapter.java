package com.example.minichat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{
    private List<Msg> mMsgList;
    public MsgAdapter(List<Msg> msgList){
        mMsgList=msgList;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg,left_name;
        TextView rightMsg,right_name;
        ImageView left_image,right_image;
        RelativeLayout left_gra,right_gra;

        public ViewHolder(View view) {
            super(view);
            leftLayout=view.findViewById(R.id.left_layout);
            rightLayout=view.findViewById(R.id.right_layout);
            leftMsg=view.findViewById(R.id.left_msg);
            rightMsg=view.findViewById(R.id.right_msg);
            left_gra=view.findViewById(R.id.left_gra);
            right_gra=view.findViewById(R.id.right_gra);
            left_name=view.findViewById(R.id.left_name);
            right_name=view.findViewById(R.id.right_name);
            left_image=view.findViewById(R.id.left_image);
            right_image=view.findViewById(R.id.right_image);
        }
    }
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Msg msg=mMsgList.get(position);
        if (msg.getType()==Msg.TYPE_RECEIVED){
            //如果是接受消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.left_gra.setVisibility(View.VISIBLE);
            holder.right_gra.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            holder.left_image.setImageResource(msg.getSend_id());
            //holder.left_name.setText();
        }else if (msg.getType()==Msg.TYPE_SENT){
            //如果是发生消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.right_gra.setVisibility(View.VISIBLE);
            holder.left_gra.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            holder.right_image.setImageResource(msg.getReceice_id());
            //holder.right_name.setText();
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
