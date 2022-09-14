package com.test.mydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.mydialog.BaseDialog;

public class TestDialog2 extends BaseDialog {


    public TestDialog2(@NonNull  Context context) {
        super(context);
    }

    public TestDialog2(@NonNull  Context context, int themeResId) {
        super(context, themeResId);
    }

    public TestDialog2(@NonNull  Context context, boolean cancelable, @Nullable  DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public View getLayoutView() {
        TextView textView = new TextView(getContext());
        textView.setText("getLayoutView");
        return textView;
    }

    @Override
    public void initView(View view) {
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
    public void onNoDoubleClick(View v) {

    }

    @Override
    public boolean noBack() {
        return false;
    }
}
