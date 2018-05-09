package com.test.mydialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.github.mydialog.MyDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_mydialog_top).setOnClickListener(this);
        findViewById(R.id.tv_mydialog_center).setOnClickListener(this);
        findViewById(R.id.tv_mydialog_bottom).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_mydialog_top:
                dialog= new MyDialog(this);
                dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog,null));
                dialog.setGravity(Gravity.TOP);
                dialog.setFullWidth();
                dialog.getWindow().setWindowAnimations(R.style.MyDialogAnimationTop);
                dialog.show();
            break;
            case R.id.tv_mydialog_center:
                showDialog();
            break;
            case R.id.tv_mydialog_bottom:
                dialog = new MyDialog(this);
                dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog,null));
                dialog.setGravity(Gravity.BOTTOM);
                dialog.setFullWidth();
                dialog.getWindow().setWindowAnimations(R.style.MyDialogAnimationBottom);
                dialog.show();
            break;
        }
    }

    public void showDialog(){
        MyDialog dialog=new MyDialog(this);
        dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog,null));
        dialog.show();
    }
}
