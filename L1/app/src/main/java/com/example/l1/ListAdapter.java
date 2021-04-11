package com.example.l1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public interface ListListener {
        void onSelectUser(int position, String name,String phone);
        void del(int position, String name,String phone);
    }
    ListListener listener;

    public List<User> userList;

    public ListAdapter(List<User> list) {
        userList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (userList != null) {
            User user = userList.get(position);
            holder.user.setText(user.name);
            holder.phone.setText(user.phone);
            holder.cha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onSelectUser(position, userList.get(position).name,userList.get(position).phone);
                    }
                }
            });
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.del(position, userList.get(position).name,userList.get(position).phone);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user,phone;
        Button cha,del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.txt1);
            phone = itemView.findViewById(R.id.txt2);
            cha = itemView.findViewById(R.id.btn1);
            del = itemView.findViewById(R.id.btn2);
        }
    }
}
