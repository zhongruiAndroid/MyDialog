package com.github.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogDecor implements DialogDecorInter {


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
    private Dialog dialog;

    public static <T extends AlertDialog.Builder> DialogDecor newInstance(T builder) {
        return new DialogDecor(builder);
    }
    public static DialogDecor newInstance(Dialog dialog) {
        return new DialogDecor(dialog);
    }
    public  DialogDecor(Dialog dialog) {
        this.dialog=dialog;
        this.window = dialog.getWindow();

    }
    public <T extends AlertDialog.Builder> DialogDecor(T builder) {
        this.dialog=builder.create();
        this.window = this.dialog.getWindow();

    }

    public Dialog show(){
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
        dialog.show();
        return dialog;
    }

    public Window getWindow() {
        return window;
    }
    public int getWidth() {
        return window.getAttributes().width;
    }
    public int getHeight() {
        return window.getAttributes().height;
    }

    @Override
    public DialogDecor setWidth(int width) {
        this.width=width;
        return this;
    }

    @Override
    public DialogDecor setHeight(int height) {
        this.height=height;
        return this;
    }

    @Override
    public DialogDecor setFullWidth() {
        this.width=WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    @Override
    public DialogDecor setFullHeight() {
        this.height=WindowManager.LayoutParams.MATCH_PARENT;
        return this;
    }

    @Override
    public DialogDecor setRadius(int radius) {
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
    public DialogDecor setRadius(int leftRadius, int topRadius, int rightRadius, int bottomRadius) {
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
    public DialogDecor setDimAmount(float dimAmount) {
        this.dimAmount=dimAmount;
        window.setDimAmount(this.dimAmount);
        return this;
    }

    @Override
    public float getAlpha() {
        return this.alpha;
    }

    @Override
    public DialogDecor setAlpha(float alpha) {
        this.alpha=alpha;
        window.getAttributes().alpha = this.alpha;
        return this;
    }

/*    @Override
    public int getTitleViewHeight() {
        return titleViewHeight;
    }

    @Override
    public DialogDecor setTitleViewHeight(int titleViewHeight) {
////
        return this;
    }

    @Override
    public int getTitleViewColor() {
        return this.titleViewColor;
    }

    @Override
    public DialogDecor setTitleViewColor(int titleViewColor) {
////
        return this;
    }*/

    @Override
    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    @Override
    public DialogDecor setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable=backgroundDrawable;
        window.setBackgroundDrawable(backgroundDrawable);
        return this;
    }

    @Override
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    /**不留padding或者设置圆角，最好是设置一下dialog背景*/
    @Override
    public DialogDecor setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor=backgroundColor;
        setDrawable();
        return this;
    }

    @Override
    public int getBackgroundDrawableResource() {
        return this.backgroundDrawableResId;
    }

    @Override
    public DialogDecor setBackgroundDrawableResource(int resId) {
        this.backgroundDrawableResId=resId;
        window.setBackgroundDrawableResource(this.backgroundDrawableResId);
        return this;
    }

    @Override
    public boolean getTranslucentStatus() {
        return this.translucentStatus;
    }

    @Override
    public DialogDecor setTranslucentStatus(boolean translucentStatus) {
        this.translucentStatus= translucentStatus;
        if (this.translucentStatus) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return this;
    }

    @Override
    public DialogDecor setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.paddingLeft=paddingLeft;
        this.paddingTop=paddingTop;
        this.paddingRight=paddingRight;
        this.paddingBottom=paddingBottom;
        if(paddingLeft!=-1&&paddingTop!=-1&&paddingRight!=-1&&paddingBottom!=-1){
            window.getDecorView().setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
        return this;
    }
    public DialogDecor setPadding(int padding) {
        return setPadding(padding,padding,padding,padding);
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public DialogDecor setGravity(int gravity) {
        this.gravity=gravity;
        window.setGravity(gravity);
        if(animationId==-1){
            setAnimation(-1);
        }
        return this;
    }

    @Override
    public DialogDecor setAnimation(@StyleRes int resId) {
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


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int getScreenHeight() {
        int heightPixels = window.getContext().getResources().getDisplayMetrics().heightPixels;
        return heightPixels;
    }

    private int getScreenWidth() {
        int widthPixels = window.getContext().getResources().getDisplayMetrics().widthPixels;
        return widthPixels;
    }





    //系统默认size
    private final int def_titleSize=20;
    private final int def_messageSize=16;
    private final int def_buttonSize=14;
    public static Button getPositiveButton(AlertDialog alertDialog) {
        return alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
    }
    public static Button getNegativeButton(AlertDialog alertDialog) {
        return alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
    }
    public static Button getNeutralButton(AlertDialog alertDialog) {
        return alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
    }
    public static TextView getTitleView(AlertDialog alertDialog) {
        return (TextView) alertDialog.findViewById(android.support.v7.appcompat.R.id.alertTitle);
    }
    public static TextView getMessageView(AlertDialog alertDialog) {
        return (TextView) alertDialog.findViewById(android.R.id.message);
    }
    public static ImageView getIcon(AlertDialog alertDialog) {
        return (ImageView) alertDialog.findViewById(android.R.id.icon);
    }
    public static void setDimAmount(AlertDialog alertDialog,double dimAmount) {
        Window win = alertDialog.getWindow();
        if(dimAmount>1){
            win.setDimAmount(1);
        }else if(dimAmount<0){
            win.setDimAmount(0);
        }else{
            win.setDimAmount((float) dimAmount);
        }
    }
    public static void setAlpha(AlertDialog alertDialog,double alpha) {
        Window win = alertDialog.getWindow();
        if(alpha>1){
            win.getAttributes().alpha= 1;
        }else if(alpha<0){
            win.getAttributes().alpha=0;
        }else{
            win.getAttributes().alpha= (float) alpha;
        }
    }



    private static final int sheetDialogDefaultParam=-100;
    public static void setBottomSheetDialogWH(BottomSheetDialog bottomSheetDialog, int width, int height){
        try {
            View design_bottom_sheet = bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet);
            if(design_bottom_sheet!=null){
                FrameLayout bottomSheet= (FrameLayout) design_bottom_sheet;
                CoordinatorLayout.LayoutParams layoutParams;
                if(width!=sheetDialogDefaultParam&&height!=sheetDialogDefaultParam){
                    layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.width=width;
                    layoutParams.height=height;
                }else if(width!=sheetDialogDefaultParam){
                    layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.width=width;
                }else{
                    layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.height=height;
                }
                layoutParams.gravity= Gravity.BOTTOM;
                bottomSheet.setLayoutParams(layoutParams);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void setBottomSheetDialogWidth(BottomSheetDialog bottomSheetDialog,int width){
        setBottomSheetDialogWH(bottomSheetDialog,width,sheetDialogDefaultParam);
    }
    public static void setBottomSheetDialogHeight(BottomSheetDialog bottomSheetDialog,int height){
        setBottomSheetDialogWH(bottomSheetDialog,sheetDialogDefaultParam,height);
    }



}
