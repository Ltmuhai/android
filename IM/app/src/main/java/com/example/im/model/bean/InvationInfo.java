package com.example.im.model.bean;

import androidx.annotation.NonNull;

public class InvationInfo {
    private UserInfo user;

    private InvationStatus status;
    public enum InvationStatus{
        New_INVITE,//新邀请
        INVITE_ACCEPT,//接受邀请
        INVITE_ACCEPT_BY_PEER,//邀请被接受
    }
    public InvationInfo(){
    }
    public InvationInfo(UserInfo user, InvationStatus status){
        this.user=user;
        this.status=status;
    }

    public InvationStatus getStatus() {
        return status;
    }

    public void setStatus(InvationStatus status) {
        this.status = status;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "InvationInfo{" +
                "user=" + user +
                ", status=" + status +
                '}';
    }
}
