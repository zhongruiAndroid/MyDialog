package com.github.mydialog;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

public interface DialogInter {

    public TheAlertDialog create();
    public TheAlertDialog show();

    /*设置dialog宽度*/
    public void setWidth(int width);

/*    *//*返回dialog宽度*//*
    public int getWidth();
    *//*返回dialog高度*//*
    public int getHeight();*/

    /*设置dialog高度*/
    public void setHeight(int height);

    /*设置dialog宽度为屏幕宽度*/
    public void setFullWidth();

    /*设置dialog高度为屏幕高度*/
    public void setFullHeight() ;

    /*设置dialog圆角*/
    public void setRadius(int radius);

    /*设置dialog圆角*/
    public void setRadius(int leftRadius,int topRadius,int rightRadius,int bottomRadius) ;

    /*返回dialog背景透明度0~1*/
    public double getDimAmount();

    /*设置dialog背景透明度0~1*/
    public void setDimAmount(double dimAmount);

    /*返回dialog透明度0~1*/
    public double getAlpha();

    /*设置dialog透明度0~1*/
    public void setAlpha(double alpha) ;

    /*返回dialog title高度*/
    public int getTitleViewHeight();

    /*设置dialog title高度*/
    public void setTitleViewHeight(int titleViewHeight);

    /*返回dialog title颜色*/
    public int getTitleViewColor();

    /*返回dialog title颜色*/
    public void setTitleViewColor(@ColorInt int titleViewColor) ;

    /*返回dialog 背景drawable*/
    public Drawable getBackgroundDrawable();

    /*设置dialog 背景drawable*/
    public void setBackgroundDrawable(Drawable backgroundDrawable) ;

    /*返回dialog 背景颜色*/
    public int getBackgroundColor();


    /*设置dialog 背景颜色*/
    public void setBackgroundColor(@ColorInt int backgroundColor) ;


    /*返回statusBar 是否半透明 */
    public boolean isStatusBarTranslucent();


    /*设置dialog */
    public void setStatusBarTranslucent(boolean statusBarTranslucent);

    /*返回dialog */
//    public boolean isAutoCustom();


    /*设置dialog */
//    public void setAutoCustom(boolean autoCustom);


    /*设置dialog padding*/
    public void setPadding(int left, int top, int right, int bottom);

    /*返回dialog 位置*/
    public int getGravity();


    /*设置dialog 位置*/
    public void setGravity(int gravity);
}
