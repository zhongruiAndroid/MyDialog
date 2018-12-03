package com.github.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class TheDialog extends AppCompatDialog implements DialogDecorInter<TheDialog>{


    private int width = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.7);
    private int height = WindowManager.LayoutParams.WRAP_CONTENT; //(int) (getScreenWidth() * 0.6);


    //    private int titleViewHeight = -1;
//    private int titleViewColor = -1;
    private boolean translucentStatus = false;
    private int backgroundColor=Color.WHITE;
    private int backgroundDrawableResId =-1;
    private Drawable backgroundDrawable;
    private int paddingLeft=-1;
    private int paddingTop=-1;
    private int paddingRight=-1;
    private int paddingBottom=-1;
    private int gravity=Gravity.CENTER;
    private float dimAmount = 0.3f;
    private float alpha = 1;
    private int leftRadius;
    private int topRadius;
    private int rightRadius;
    private int bottomRadius;

    private int animationId=-1;


    private Window window;

    public TheDialog(@NonNull Context context) {
        super(context);
        window=getWindow();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public TheDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        window=getWindow();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public TheDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        window=getWindow();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    @Override
    public TheDialog setWidth(int width) {
        this.width=width;
        return this;
    }

    @Override
    public TheDialog setHeight(int height) {
        this.height=height;
        return this;
    }

    @Override
    public TheDialog setFullWidth() {
        this.width=WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    @Override
    public TheDialog setFullHeight() {
        this.height=WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    @Override
    public TheDialog setRadius(int radius) {
        this.leftRadius=radius;
        this.topRadius=radius;
        this.rightRadius=radius;
        this.bottomRadius=radius;
        setDrawable();
        return this;
    }


    private void setDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(backgroundColor);
        float[] fourRadius = new float[]{
                leftRadius, leftRadius,
                topRadius, topRadius,
                rightRadius, rightRadius,
                bottomRadius, bottomRadius};
        gradientDrawable.setCornerRadii(fourRadius);
        backgroundDrawable=gradientDrawable;
        window.setBackgroundDrawable(gradientDrawable);
    }

    @Override
    public TheDialog setRadius(int leftRadius, int topRadius, int rightRadius, int bottomRadius) {
        this.leftRadius=leftRadius;
        this.topRadius=topRadius;
        this.rightRadius=rightRadius;
        this.bottomRadius=bottomRadius;
        setDrawable();
        return this;
    }

    @Override
    public float getDimAmount() {
        return dimAmount;
    }

    @Override
    public TheDialog setDimAmount(float dimAmount) {
        this.dimAmount=dimAmount;
        window.setDimAmount(this.dimAmount);
        return this;
    }

    @Override
    public float getAlpha() {
        return this.alpha;
    }

    @Override
    public TheDialog setAlpha(float alpha) {
        this.alpha=alpha;
        window.getAttributes().alpha = this.alpha;
        return this;
    }

    @Override
    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    @Override
    public TheDialog setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable=backgroundDrawable;
        window.setBackgroundDrawable(backgroundDrawable);
        return this;
    }

    @Override
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public TheDialog setBackgroundColor(int backgroundColor) {
        this.backgroundColor=backgroundColor;
        setDrawable();
        return this;
    }

    @Override
    public int getBackgroundDrawableResource() {
        return this.backgroundDrawableResId;
    }

    @Override
    public TheDialog setBackgroundDrawableResource(int resId) {
        this.backgroundDrawableResId=resId;
        window.setBackgroundDrawableResource(this.backgroundDrawableResId);
        return this;
    }

    @Override
    public boolean getTranslucentStatus() {
        return this.translucentStatus;
    }

    @Override
    public TheDialog setTranslucentStatus(boolean translucentStatus) {
        this.translucentStatus= translucentStatus;
        if (this.translucentStatus) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return this;
    }

    @Override
    public TheDialog setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.paddingLeft=paddingLeft;
        this.paddingTop=paddingTop;
        this.paddingRight=paddingRight;
        this.paddingBottom=paddingBottom;
        if(paddingLeft!=-1&&paddingTop!=-1&&paddingRight!=-1&&paddingBottom!=-1){
            window.getDecorView().setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        return this;
    }


    public TheDialog setPadding(int padding) {
        return setPadding(padding,padding,padding,padding);
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public TheDialog setGravity(int gravity) {
        this.gravity=gravity;
        window.setGravity(gravity);
        if(animationId==-1){
            setAnimation(-1);
        }
        return this;
    }

    @Override
    public TheDialog setAnimation(int resId) {
        if(animationId==-1){
            switch (gravity){
                case Gravity.TOP:
                    window.setWindowAnimations(R.style.MyDialogAnimationTop);
                    break;
                case Gravity.BOTTOM:
                    window.setWindowAnimations(R.style.MyDialogAnimationBottom);
                    break;
            }
        }else{
            this.animationId=resId;
            window.setWindowAnimations(animationId);
        }
        return this;
    }

    @Override
    public void show() {
        WindowManager.LayoutParams lp = window.getAttributes();
        if(this.width!=WindowManager.LayoutParams.WRAP_CONTENT){
            lp.width = width;
        }
        if(this.height!=WindowManager.LayoutParams.WRAP_CONTENT){
            lp.height = height;
        }
        if (width != WindowManager.LayoutParams.WRAP_CONTENT || height != WindowManager.LayoutParams.WRAP_CONTENT) {
            window.setAttributes(lp);
        }



        super.show();
    }
}