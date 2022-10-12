package com.github.mydialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Calendar;

public abstract class IBaseDialog extends TheDialog implements View.OnClickListener {
    private View view;
    private static final int MIN_CLICK_DELAY_TIME = 980;

    public IBaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public IBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public IBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return findViewById(id, false);
    }

    public <T extends View> T findViewById(@IdRes int id, boolean setClick) {
        View targetView;
        if (view == null) {
            targetView = super.findViewById(id);
            if (targetView != null && setClick) {
                targetView.setOnClickListener(this);
            }
            return (T) targetView;
        }
        targetView = view.findViewById(id);
        if (targetView != null && setClick) {
            targetView.setOnClickListener(this);
        }
        return (T) targetView;
    }

    private void init() {
        view = getContentView();
        if (view == null) {
            view = getLayoutInflater().inflate(getContentViewId(), null);
        }
        setContentView(view);
        initData();
    }

    public abstract int getContentViewId();

    public View getContentView() {
        return null;
    }

    public abstract void initData();

    public long getNoDoubleClickInterval() {
        return MIN_CLICK_DELAY_TIME;
    }

    public abstract void onNoDoubleClick(View v);

    public void onViewClick(View v) {
    }


    @Override
    public final void onClick(View v) {
        onViewClick(v);

        Object preTimeTag = v.getTag(R.id.doubleClickTimeFlag);
        if (preTimeTag instanceof Long) {
            long preTime = (long) preTimeTag;
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - preTime < getNoDoubleClickInterval()) {
                return;
            }
        }
        onNoDoubleClick(v);
    }

}
