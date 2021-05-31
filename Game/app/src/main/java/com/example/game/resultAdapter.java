package com.example.game;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class resultAdapter extends RecyclerView.Adapter<resultAdapter.ViewHolder> {
    public List<hobby> hobbylist;
    public resultAdapter(List<hobby> list){hobbylist=list;}
    public interface ListListener {
        void onSelectUser( int pos,String username,String hobby);
    }
    resultAdapter.ListListener listener;
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.resultlist,parent,false);
        ViewHolder Holder=new ViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull resultAdapter.ViewHolder holder, int position) {
        if(hobbylist!=null){
            hobby hobby= hobbylist.get(position);
            holder.name.setText(hobby.name);
            holder.hobby.setText(hobby.hobby);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectUser(position,hobby.name,hobby.hobby);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hobbylist.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,hobby;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            hobby=itemView.findViewById(R.id.hobby);
        }
    }
}
