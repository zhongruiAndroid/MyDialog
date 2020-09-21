package com.test.mydialog;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;

import com.github.mydialog.BaseTheDialogFragment;

public class TestDialogFragment extends BaseTheDialogFragment {
    @Override
    public int getContentView() {
        return R.layout.dialog;
    }
    @Override
    public void initView() {
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setAlpha(0.9f).setCanMoveDialog(true)
                .setDimAmount(0.3f)
                .setHeight(800)
                .setWidth(800).setAnimation(0)
//                        .setFullWidth()
//                        .setFullHeight()
                .setBackgroundColor(ContextCompat.getColor(mView.getContext(), R.color.white))
                .setTranslucentStatus(false)
                .setPadding(0)
                .setGravity(Gravity.BOTTOM)
                .setRadius(50);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
