package com.example.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class webActivity extends AppCompatActivity {
    ImageView web_image;
    Button web_Bu;
    Handler mHandler;
    ProgressDialog mdownDialog = null;
    //String mFileName;
    private Bitmap mBitmap;
    InputStream stream;
    //Thread downloadPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web_image=findViewById(R.id.web_image);
        web_Bu=findViewById(R.id.web_bu);
        mHandler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //弹出对话框

                Log.d("webActivity","弹窗");
                switch (msg.what){
                    case 1:
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                web_image.setImageBitmap(mBitmap);
                                mdownDialog.dismiss();
                            }
                        },3000);

                }
            }
        };
        web_Bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdownDialog = ProgressDialog.show(webActivity.this, "下载图片", "图片正在下载，请稍后...", true);
                mdownDialog.show();
                new  Thread(){
                    @Override
                    public void run(){
                        super.run();
                        //下载图片并加载
                        String path ="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.juimg.com%2Ftuku%2Fyulantu%2F140703%2F330746-140f301555752.jpg&refer=http%3A%2F%2Fimg.juimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623381090&t=4ed18467787bb6708692eba4e72c7582";
                        try {
                            URL url = new URL(path);//创建URL连接
                            URLConnection connection = url.openConnection();//打开连接
                            stream = connection.getInputStream();//获取输输入流
                            mBitmap = BitmapFactory.decodeStream(stream);
                            Message mMessage=new Message();
                            mMessage.what=1;
                            mHandler.sendMessage(mMessage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}