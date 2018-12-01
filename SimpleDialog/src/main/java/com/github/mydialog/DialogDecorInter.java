package com.github.mydialog;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;

public interface DialogDecorInter<T> {

//    public TheAlertDialog create();
//    public TheAlertDialog show();

    /*设置dialog宽度*/
    public T setWidth(int width);

/*    *//*返回dialog宽度*//*
    public int getWidth();
    *//*返回dialog高度*//*
    public int getHeight();*/

    /*设置dialog高度*/
    public T setHeight(int height);

    /*设置dialog宽度为屏幕宽度*/
    public T setFullWidth();

    /*设置dialog高度为屏幕高度*/
    public T setFullHeight() ;

    /*设置dialog圆角*/
    public T setRadius(int radius);

    /*设置dialog圆角*/
    public T setRadius(int leftRadius, int topRadius, int rightRadius, int bottomRadius) ;

    /*返回dialog背景透明度0~1*/
    public float getDimAmount();

    /*设置dialog背景透明度0~1*/
    public T setDimAmount(float dimAmount);

    /*返回dialog透明度0~1*/
    public float getAlpha();

    /*设置dialog透明度0~1*/
    public T setAlpha(float alpha) ;

    /*返回dialog title高度*/
//    public int getTitleViewHeight();

    /*设置dialog title高度*/
//    public T setTitleViewHeight(int titleViewHeight);

    /*返回dialog title颜色*/
//    public int getTitleViewColor();

    /*返回dialog title颜色*/
//    public T setTitleViewColor(@ColorInt int titleViewColor) ;

    /*返回dialog 背景drawable*/
    public Drawable getBackgroundDrawable();

    /*设置dialog 背景drawable*/
    public T setBackgroundDrawable(Drawable backgroundDrawable) ;

    /*返回dialog 背景颜色*/
    public int getBackgroundColor();

    /*设置dialog 背景颜色*/
    public T setBackgroundColor(@ColorInt int backgroundColor) ;

    /*返回dialog 背景drawable*/
    public int getBackgroundDrawableResource();

    /*设置dialog 背景drawable*/
    public T setBackgroundDrawableResource(@DrawableRes int resId) ;




    /*返回statusBar 是否半透明 */
    public boolean getTranslucentStatus();


    /*设置dialog */
    public T setTranslucentStatus(boolean translucentStatus);

    /*返回dialog */
//    public boolean isAutoCustom();


    /*设置dialog */
//    public T setAutoCustom(boolean autoCustom);


    /*设置dialog padding*/
    public T setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom);
    public T setPadding(int padding);
    /*返回dialog 位置*/
    public int getGravity();


    /*设置dialog 位置*/
    public T setGravity(int gravity);

    public T setAnimation(@StyleRes int resId);
}
