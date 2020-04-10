package com.github.mydialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class IBaseDialog extends TheDialog implements View.OnClickListener {

    private Long clickTimeFlag;
    public IBaseDialog(@NonNull Context context) {
        super(context);
    }
    public IBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    public IBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public abstract void initView();
    public long getNoDoubleClickInterval() {
        return 900;
    }
    public abstract void onNoDoubleClick(View v);
    public void onViewClick(View v) {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickTimeFlag = new Long(hashCode());
    }

    @Override
    public final void onClick(View v) {
        onViewClick(v);
        boolean fastClick = ClickTools.get().isFastClick(clickTimeFlag, v, getNoDoubleClickInterval());
        if (!fastClick) {
            onNoDoubleClick(v);
        }
    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ClickTools.get().removeLastClickTime(clickTimeFlag);
    }
}
