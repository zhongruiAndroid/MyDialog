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


public abstract class BaseDialog extends TheDialog implements View.OnClickListener {


    public View mView;
    private Long clickTimeFlag;

    public BaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        mView = getLayoutView();
        if (mView == null) {
            mView = getLayoutInflater().inflate(getLayoutId(), null);
        }
        Window window = getWindow();
        window.setWindowAnimations(-1); // 去掉动画动画
        setDimAmount(0.2f);
        setBackgroundColor(Color.TRANSPARENT);
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {  //返回键拦截
                    //按返回键，loading框消失
                    loadDismiss();
                    return noBack();
                }
                return false;
            }
        });
        /*默认*/
        setCanceledOnTouchOutside(false);
        initView(mView);
        setContentView(mView);

        /*防止用户设置为true,noBack优先级更高*/
        if (noBack()) {
            setCanceledOnTouchOutside(false);
        }
    }

    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    public abstract void initView(View view);

    public long getNoDoubleClickInterval() {
        return 1000;
    }

    public abstract void onNoDoubleClick(View v);

    public void onViewClick(View v) {
    }

    public abstract boolean noBack();

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

    public <T extends View> T findViewById(@IdRes int id) {
        return mView.findViewById(id);
    }

    public final <T extends View> T fid(@IdRes int id) {
        return fid(id, false);
    }

    public final <T extends View> T fid(@IdRes int id, boolean needSetClick) {
        View viewById = mView.findViewById(id);
        if (needSetClick) {
            viewById.setOnClickListener(this);
        }
        return (T) viewById;
    }

    private ViewGroup viewGroup;
    private volatile int continueShowToast;

    public void dismissAndToast(String text) {
        loadShow(false);
        showToast(text, 700);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
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
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
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
//                flAnimContent.setBackgroundResource(R.drawable.shape_loading_bg);
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

    public void closeDialog(@IdRes int viewId) {
        mView.findViewById(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }


}
