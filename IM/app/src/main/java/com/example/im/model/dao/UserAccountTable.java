package com.example.im.model.dao;

import com.google.android.material.tabs.TabLayout;

public class UserAccountTable {
    public static final String TAB_Name ="tab_account";
    public static final String COL_NAME="name";
    public static final String COL_HXID="hxid";
    public static final String COL_NICK="nick";
    public static final String COL_PHOTO="photo";

    public static final String CREATE_TAB="create table "
            + TAB_Name+" ("
            + COL_HXID+" text primary key,"
            + COL_NAME+" text,"
            + COL_NICK+" text,"
            + COL_PHOTO+" text);";

}
