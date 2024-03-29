package com.github.mydialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;

public abstract class IBaseDialog extends TheDialog implements View.OnClickListener {
    private View view;
    private static final int MIN_CLICK_DELAY_TIME = 980;

    public IBaseDialog(@NonNull Context context) {
        super(context);
    }

    public IBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public IBaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {  //返回键拦截
                    //按返回键，loading框消失
                    loadDismiss();
                    return onBackNoDismiss();
                }
                return onKeyListener(dialog, keyCode, event);
            }
        });
        /*默认*/
        setCanceledOnTouchOutside(false);
        setContentView(view);
        initView(view);
        _initView(view);
        initData();

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
    public void _initView(View view){

    }

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

    public abstract boolean onBackNoDismiss();


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


    private ViewGroup viewGroup;
    private volatile int continueShowToast;
    private static Handler handler;

    public void dismissAndToast(String text) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        loadShow(false);
        showToast(text, 700);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 700);
    }

    @Override
    public void dismiss() {
        loadDismiss();
        super.dismiss();
    }

    public void showToast(String text) {
        showToast(text, 1200);
    }

    public void showToast(String text, long duration) {
        if (duration <= 0) {
            duration = 1200;
        }
        try {
            if (viewGroup == null) {
                Activity activity = TheDialog.findActivity(getContext());
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
                    return;
                }
                Window window = getWindow();
                if (window == null) {
                    return;
                }
                viewGroup = window.getDecorView().findViewById(android.R.id.content);
            }
            TextView toastView = viewGroup.findViewById(R.id.toastId);
            if (continueShowToast == 0) {
                continueShowToast = 1;
            } else {
                continueShowToast = 2;
            }
            if (toastView == null) {
                toastView = new TextView(viewGroup.getContext());
                toastView.setId(R.id.toastId);

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                toastView.setLayoutParams(layoutParams);

                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(dp2px(50));
                gradientDrawable.setColor(Color.parseColor("#70000000"));

                toastView.setBackgroundDrawable(gradientDrawable);
                toastView.setEllipsize(TextUtils.TruncateAt.END);
                toastView.setMaxLines(2);
                int dp15 = dp2px(15);
                int dp10 = dp2px(10);
                toastView.setPadding(dp15, dp10, dp15, dp10);
                toastView.setTextColor(Color.WHITE);
                toastView.setTextSize(14);

                viewGroup.addView(toastView);
            }
            toastView.setText(text);
            toastView.setVisibility(View.VISIBLE);
            final TextView finalToastView = toastView;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (continueShowToast == 2) {
                        continueShowToast = 1;
                        return;
                    }
                    continueShowToast = 0;
                    if (finalToastView != null) {
                        finalToastView.setVisibility(View.INVISIBLE);
                    }
                }
            }, duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDismiss() {
        if (viewGroup == null) {
            return;
        }
        View loadView = viewGroup.findViewById(R.id.loadId);
        if (loadView == null) {
            return;
        }
        loadView.setVisibility(View.INVISIBLE);
    }

    public void loadShow() {
        loadShow(true);
    }

    public void loadShow(boolean showAnim) {
        try {
            if (viewGroup == null) {
                Activity activity = TheDialog.findActivity(getContext());
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
                    return;
                }
                Window window = getWindow();
                if (window == null) {
                    return;
                }
                viewGroup = window.getDecorView().findViewById(android.R.id.content);
            }
            View loadView = viewGroup.findViewById(R.id.loadId);

            if (loadView == null) {
                loadView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading_layout, null);
                loadView.setId(R.id.loadId);

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.gravity = Gravity.CENTER;
                loadView.setLayoutParams(layoutParams);


                viewGroup.addView(loadView);
            }
            View flAnimContent = viewGroup.findViewById(R.id.flAnimContent);
            if (flAnimContent != null) {
                flAnimContent.setVisibility(showAnim ? View.VISIBLE : View.INVISIBLE);
            }
            loadView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getColor(@ColorRes int colorId) {
        return ContextCompat.getColor(getContext(), colorId);
    }

    public int dp2px(int value) {
        return (int) (getContext().getResources().getDisplayMetrics().density * value);
    }

    public String getString(View view) {
        if (view == null) {
            return "";
        }
        if (view instanceof EditText) {
            Editable text = ((EditText) view).getText();
            if (text == null) {
                return "";
            }
            return text.toString();
        } else if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text == null) {
                return "";
            }
            return text.toString();
        }
        return "";
    }

    public void closeDialog(View view) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void closeDialog(@IdRes int viewId) {
        if (view == null) {
            return;
        }
        closeDialog(view.findViewById(viewId));
    }
}
