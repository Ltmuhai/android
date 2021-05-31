package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class finger_guessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_guessing);
        RadioGroup G1=findViewById(R.id.Radio_Grp1);
        RadioGroup G2=findViewById(R.id.Radio_Grp2);
        Button detemine=findViewById(R.id.detemine);
        TextView TV=findViewById(R.id.result);
        final int[] A = new int[2];
        final int[] result = new int[1];
        G1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int check=group.getCheckedRadioButtonId();
                if(check==R.id.A_store){
                    A[0] =1;
                }
                if(check==R.id.A_scissors){
                    A[0] =2;
                }
                if(check==R.id.A_cloth){
                    A[0] =3;
                }
            }
        });
        G2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int check=group.getCheckedRadioButtonId();
                if(check==R.id.B_store){
                    A[1] =1;
                }
                if(check==R.id.B_scissors){
                    A[1] =2;
                }
                if(check==R.id.B_cloth){
                    A[1] =3;
                }
            }
        });
        detemine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (A[0]!=0&&A[1]!=0) {
                    result[0] =compare(A[0],A[1]);
                }
                //输出结果，result为1则A赢，2为B赢，3为平局
                switch (result[0]){
                    case 1:{TV.setText("A赢了");break;}
                    case 2:{TV.setText("B赢了");break;}
                    case 3:{TV.setText("平局");break;}
                }
            }
        });


    }
    //比较函数
    public int compare(int A,int B){
        if(A==B){
            return 3;
        }else if(A==1){
            if(B==2){
                return 1;
            }else {
                return 2;
            }
        }else if (A==2){
            if (B==1){
                return 2;
            }else {return 1;}
        }else {
            if(B==1){
                return 1;
            }else{
                return 2;
            }
        }
    }
}