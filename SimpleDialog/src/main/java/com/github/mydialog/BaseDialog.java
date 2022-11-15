package com.github.mydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import java.util.Calendar;


public abstract class BaseDialog extends TheDialog implements View.OnClickListener {
    private View view;
    private static final int MIN_CLICK_DELAY_TIME = 980;

    public BaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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
        view = getContentLayout();
        if (view == null) {
            view = getLayoutInflater().inflate(getContentView(), null);
        }
        setContentView(view);
        initView(view);
        initData();
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return onBackNoDismiss();
                }
                return onKeyListener(dialog, keyCode, event);
            }
        });
        /*防止用户设置为true,noBack优先级更高*/
        if (onBackNoDismiss()) {
            setCanceledOnTouchOutside(false);
        }
    }

    public abstract int getContentView();

    public View getContentLayout() {
        return null;
    }

    public abstract void initView(View view);

    public abstract void initData();

    public long getNoDoubleClickInterval() {
        return MIN_CLICK_DELAY_TIME;
    }

    public abstract void onNoDoubleClick(View v);

    public void onViewClick(View v) {
    }

    public boolean onKeyListener(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onBackNoDismiss() {
        return false;
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
