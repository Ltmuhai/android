package com.example.game;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CameralistActivity extends AppCompatActivity {
    RecyclerView Cameralist;
    CameraAdapter cameraAdapter;
    List<photo> photoList=new ArrayList<>();
    Button Camera;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameralist);
        Camera=findViewById(R.id.Camera);
        Cameralist=findViewById(R.id.Camera_list);
        cameraAdapter=new CameraAdapter(photoList);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(CameralistActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addpicture();
        cameraAdapter=new CameraAdapter(photoList);
        RecyclerView.LayoutManager layoutMgr = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        Cameralist.setLayoutManager(layoutMgr);
        Cameralist.setAdapter(cameraAdapter);
        Cameralist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        cameraAdapter.notifyDataSetChanged();
    }

    // @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //ImageBinder imageBinder = (ImageBinder) data.getExtras().getBinder("bitmap");
//        Bitmap bitmap = data.getExtras().getParcelable("bitmap");
//        photoList.add(new photo(bitmap,data.getExtras().getString("time")));
//        cameraAdapter.notifyDataSetChanged();
//    }
    protected void addpicture(){
        String filePath = Environment.getExternalStorageDirectory() + "/DCIM/myPicture/";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//获取当前时间，进一步转化为字符串
            Date date = new Date(file.lastModified());

            photoList.add(new photo(file.getAbsolutePath(),format.format(date)));

        }
    }

}