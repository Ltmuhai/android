package com.example.l1;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class mylist extends BaseAdapter {
    List<View> viewList;
    public mylist(List<View> list) {
        viewList = list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
