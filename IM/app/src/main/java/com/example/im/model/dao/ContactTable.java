package com.example.im.model.dao;

public class ContactTable {
    public static final String TAB_name="tab_contact";
    public static final String COL_HXID="hxid";
    public static final String COL_NAME="name";
    public static final String COL_NICK="nick";
    public static final String COL_PHOTO="photo";
    public static final String CREATE_TAB="create table "
            +TAB_name+" ("
            +COL_HXID+" taxt primary key,"
            +COL_NAME+" text,"
            +COL_NICK+" text,"
            +COL_PHOTO+" text,);";
}
