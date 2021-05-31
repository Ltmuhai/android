package com.example.game;
import android.widget.ImageView;
public class User {
    public String username;
    public String last_chat;
    public int head_portrait;

    public User(String username,int head_portrait ,String last_chat) {
        this.username = username;
        this.head_portrait= head_portrait;
        this.last_chat=last_chat;
    }
}
