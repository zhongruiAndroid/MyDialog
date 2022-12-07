package com.test.mydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mydialog.IBaseDialog;

import java.util.ArrayList;
import java.util.List;

public class TestDialog2 extends IBaseDialog {

    private List<String> list=new ArrayList<>();
    public TestDialog2(@NonNull  Context context) {
        super(context);
        Log.i("=====","=====TestDialog2"+(list==null));
    }

    public TestDialog2(@NonNull  Context context, int themeResId) {
        super(context, themeResId);
        Log.i("=====","=====TestDialog2"+(list==null));
    }

    public TestDialog2(@NonNull  Context context, boolean cancelable, @Nullable  DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        Log.i("=====","=====TestDialog2"+(list==null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("=====","=====onCreate"+(list==null));

    }

    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public View getContentLayout() {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setText("getLayoutView");
        return textView;
    }

    @Override
    public void initView(View view) {
        Log.i("=====","=====initView"+(list==null));
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("initView");
            }
        },2000);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadShow();
            }
        },4000);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                loadDismiss();
            }
        },6000);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {

    }

    @Override
    public boolean onBackNoDismiss() {
        return false;
    }


}
