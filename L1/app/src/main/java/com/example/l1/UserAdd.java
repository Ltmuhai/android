package com.example.l1;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserAdd {
    AlertDialog.Builder builder;
    AlertDialog dialog;
    Button btnCancel;
    Button btnAdd;
    EditText edit1;
    EditText edit2;
    public interface Callback {
        void onAddName(String name,String phone);
    }
    public Callback callback;
    public UserAdd(Activity activity) {
        builder = new AlertDialog.Builder(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.add, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.setCancelable(true);
    }

    public void show() {
        dialog.show();

        edit1 = dialog.findViewById(R.id.edit3);
        edit2 = dialog.findViewById(R.id.edit4);
        btnCancel  = dialog.findViewById(R.id.Bu_4);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnAdd = dialog.findViewById(R.id.Bu_3);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onAddName(edit1.getText().toString(),edit2.getText().toString());

                }
                dialog.dismiss();
            }
        });

    }
}
