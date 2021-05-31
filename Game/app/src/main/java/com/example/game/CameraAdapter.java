package com.example.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.ViewHolder>{
    List<photo> photoList;
    public CameraAdapter(List<photo> List){
        photoList=List;
    }
    @Override
    public CameraAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cameralist,parent,false);
        CameraAdapter.ViewHolder Holder=new  ViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder( CameraAdapter.ViewHolder holder, int position) {
        if (photoList!=null){
            photo photo=photoList.get(position);
            Bitmap bm = BitmapFactory.decodeFile(photo.photo);

            holder.photo.setImageBitmap(bm);
            holder.time.setText(photo.time);
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.photo);
            time=itemView.findViewById(R.id.time);
        }
    }
}
