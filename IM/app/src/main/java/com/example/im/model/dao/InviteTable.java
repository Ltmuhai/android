package com.example.im.model.dao;

public class InviteTable {
    public static final String TAB_NAME="tab_invite";
    public static final String COL_USER_HXID="user_hxid";
    public static final String COL_USER_NAME="user_name";
    public static final String COL_STATUS="status";
    public static final String CREATE_TAB="create table "
            +TAB_NAME+" ("
            +COL_USER_HXID+" text primary,"
            +COL_USER_NAME+" text,"
            +COL_STATUS+" integer);";
}
