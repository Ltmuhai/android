package com.example.l1;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserEdit {
    AlertDialog.Builder builder;
    AlertDialog dialog;
    Button btnCancel;
    Button btnEdit;
    EditText edit1;
    EditText edit2;
    public interface Callback {
        void onEditName(String name,String phone);
    }
    public Callback callback;
    public UserEdit(Activity activity) {
        builder = new AlertDialog.Builder(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.edit, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.setCancelable(true);
    }

    public void show() {
        dialog.show();

        edit1 = dialog.findViewById(R.id.edit1);
        edit2 = dialog.findViewById(R.id.edit2);
        btnCancel  = dialog.findViewById(R.id.Bu_2);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnEdit = dialog.findViewById(R.id.Bu_1);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onEditName(edit1.getText().toString(),edit2.getText().toString());
                }
                dialog.dismiss();
            }
        });

    }
}
