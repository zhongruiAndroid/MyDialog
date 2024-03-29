package com.test.mydialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.mydialog.IBaseDialog;

public class TestDialog extends IBaseDialog {


    public TestDialog(@NonNull Context context) {
        super(context);
    }

    public TestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public TestDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView(View view) {

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
