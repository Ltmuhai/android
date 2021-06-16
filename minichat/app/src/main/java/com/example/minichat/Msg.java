package com.example.minichat;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT =1;
    private String content;
    private int type;
    private int send_id,receice_id;
    public Msg(String content,int type){                //todu:加入双方id
        this.content=content;
        this.type=type;
    }
    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
    public int getSend_id(){return send_id;}
}
