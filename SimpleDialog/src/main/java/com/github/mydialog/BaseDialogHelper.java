package com.github.mydialog;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class BaseDialogHelper {
    public final int def_titleSize = 20;
    public final int def_messageSize = 16;
    public final int def_buttonSize = 14;
    public static final String neutral = "跳过";
    public static final String negative = "取消";
    public static final String positive = "确定";
    public static final String title = "提示";


    private final int flag_width = -10;
    public int width = (int) (getScreenWidth() * 0.6);
    public int height = (int) (getScreenWidth() * 0.4);
    //    private int width = flag_width; //(int) (getScreenWidth() * 0.7);
//    private int height = flag_width; //(int) (getScreenWidth() * 0.6);
    private int titleViewHeight = -1;
    private int titleViewColor = -1;
    private boolean isTranslucent = true;
    private boolean isAutoCustom = true;
    private int backgroundColor;
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
    private Context context;

    public BaseDialogHelper(Context context) {
        this.context = context;
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

    public Context getContext() {
        return context;
    }
}
