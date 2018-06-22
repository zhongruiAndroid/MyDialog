package com.github.mydialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/7.
 */

public class MySimpleDialog extends AppCompatDialog {
    private final int flag_width = -10;
    private int width = flag_width; //(int) (getScreenWidth() * 0.7);
    private int height = flag_width; //(int) (getScreenWidth() * 0.6);
    private int titleViewHeight = -1;
    private int titleViewColor = -1;
    private boolean isTranslucent = true;
    private boolean isAutoCustom = true;
    private int backgroundColor = android.R.color.transparent;
    private Drawable backgroundDrawable;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private int gravity;
    private double dimAmount = 0.3;
    private double alpha = 1;
    private boolean isInit = false;
    private int leftRadius;
    private int topRadius;
    private int rightRadius;
    private int bottomRadius;

    public MySimpleDialog(Context context) {
        super(context);
    }

    public MySimpleDialog(Context context, int theme) {
        super(context, theme);
    }

    protected MySimpleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFullWidth() {
        this.width = WindowManager.LayoutParams.MATCH_PARENT;
    }

    public void setFullHeight() {
        this.height = WindowManager.LayoutParams.MATCH_PARENT;
    }

    public void setRadius(int radius) {
        leftRadius      = radius;
        topRadius       = radius;
        rightRadius     = radius;
        bottomRadius    = radius;
    }

    public void setRadius(int leftRadius,int topRadius,int rightRadius,int bottomRadius) {
        this.leftRadius = leftRadius;
        this.topRadius = topRadius;
        this.rightRadius = rightRadius;
        this.bottomRadius = bottomRadius;
    }

    public double getDimAmount() {
        return dimAmount;
    }

    public void setDimAmount(double dimAmount) {
        if (dimAmount > 1) {
            this.dimAmount = 1;
        } else if (dimAmount < 0) {
            this.dimAmount = 0;
        } else {
            this.dimAmount = dimAmount;
        }
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        if (alpha > 1) {
            this.alpha = 1;
        } else if (alpha < 0) {
            this.alpha = 0;
        } else {
            this.alpha = alpha;
        }
    }

    public int getTitleViewHeight() {
        return titleViewHeight;
    }

    public void setTitleViewHeight(int titleViewHeight) {
        this.titleViewHeight = titleViewHeight;
    }

    public int getTitleViewColor() {
        return titleViewColor;
    }

    public void setTitleViewColor(@ColorInt int titleViewColor) {
        this.titleViewColor = titleViewColor;
    }

    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public void setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }

    public boolean isTranslucent() {
        return isTranslucent;
    }

    public void setTranslucent(boolean translucent) {
        isTranslucent = translucent;
    }

    public boolean isAutoCustom() {
        return isAutoCustom;
    }

    public void setAutoCustom(boolean autoCustom) {
        isAutoCustom = autoCustom;
    }


    public void setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public MySimpleDialog init() {
        isInit = true;
        if (isAutoCustom) {
            Window win = this.getWindow();
            if (isTranslucent) {
                win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
//            int titleViewId = getContext().getResources().getIdentifier("title", "id", getContext().getPackageName());
            TextView titleView = (TextView) findViewById(android.support.v7.appcompat.R.id.title);
            if (titleView != null) {
                if (titleViewColor != -1) {
                    titleView.setBackgroundColor(titleViewColor);
                }
                if (titleViewHeight >= 0) {
                    titleView.setHeight(titleViewHeight);
                } else {
                    titleView.setHeight(getStatusBarHeight(getContext()));
                }
            }

            if (backgroundDrawable != null) {
                win.setBackgroundDrawable(backgroundDrawable);
            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(ContextCompat.getColor(getContext(),backgroundColor));
                float[] fourRadius = new float[]{
                        leftRadius, leftRadius,
                        topRadius, topRadius,
                        rightRadius, rightRadius,
                        bottomRadius, bottomRadius};
                gradientDrawable.setCornerRadii(fourRadius);
                win.setBackgroundDrawable(gradientDrawable);
            }
            WindowManager.LayoutParams lp = win.getAttributes();
            if (width != flag_width) {
                lp.width = width;
            }
            if (height != flag_width) {
                lp.height = height;
            }

            if (width != flag_width || height != flag_width) {
                win.setAttributes(lp);
            }
        }
        return this;
    }

    @Override
    public void show() {
        if (isInit) {
            isInit = false;
        } else {
            init();
        }
        Window win = getWindow();
        win.setDimAmount((float) dimAmount);
        win.setGravity(gravity);
        win.getDecorView().setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        getWindow().getAttributes().alpha = (float) this.alpha;
        super.show();
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int getScreenHeight() {
        int heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
        return heightPixels;
    }

    private int getScreenWidth() {
        int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;
        return widthPixels;
    }
}
