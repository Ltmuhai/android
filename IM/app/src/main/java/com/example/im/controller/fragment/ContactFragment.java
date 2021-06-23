package com.example.im.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.im.R;
import com.example.im.controller.activity.AddContactActivity;
import com.hyphenate.easeui.widget.EaseTitleBar;


public class ContactFragment extends Fragment {
    EaseTitleBar title;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragment_contact,null);
        View headerview=View.inflate(getActivity(), R.layout.header_fragment_contact,null);
        title=view.findViewById(R.id.title_bar);
        title.setRightImageResource(R.drawable.add);
        listView=view.findViewById(R.id.list_view);
        listView.addHeaderView(headerview);
        title.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
