package com.github.mydialog;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Window;

public class NewAlertDialog extends BaseDialogHelper implements DialogInter{

    private TheAlertDialog.Builder dialogBuilder;
    private TheAlertDialog alertDialog;

    public NewAlertDialog(Context context) {
        super(context);
        dialogBuilder = new TheAlertDialog.Builder(context);
    }
    public NewAlertDialog(@NonNull Context context, int themeResId) {
        super(context);
        dialogBuilder =new TheAlertDialog.Builder(context,themeResId);
    }

    @Override
    public TheAlertDialog create() {
        if(alertDialog==null){
            alertDialog = (TheAlertDialog) dialogBuilder.create();
        }
        return alertDialog;
    }

    @Override
    public TheAlertDialog show() {
        if(alertDialog==null){
            alertDialog = (TheAlertDialog) dialogBuilder.create();
        }
        alertDialog.show();
        return alertDialog;
    }

    @Override
    public void setWidth(int width) {
        this.width=width;
    }

    @Override
    public void setHeight(int height) {
        this.height=height;
    }

    @Override
    public void setFullWidth() {

    }

    @Override
    public void setFullHeight() {

    }

    @Override
    public void setRadius(int radius) {

    }

    @Override
    public void setRadius(int leftRadius, int topRadius, int rightRadius, int bottomRadius) {

    }

    @Override
    public double getDimAmount() {
        return 0;
    }

    @Override
    public void setDimAmount(double dimAmount) {

    }

    @Override
    public double getAlpha() {
        return 0;
    }

    @Override
    public void setAlpha(double alpha) {

    }

    @Override
    public int getTitleViewHeight() {
        return 0;
    }

    @Override
    public void setTitleViewHeight(int titleViewHeight) {

    }

    @Override
    public int getTitleViewColor() {
        return 0;
    }

    @Override
    public void setTitleViewColor(int titleViewColor) {

    }

    @Override
    public Drawable getBackgroundDrawable() {
        return null;
    }

    @Override
    public void setBackgroundDrawable(Drawable backgroundDrawable) {

    }

    @Override
    public int getBackgroundColor() {
        return 0;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {

    }

    @Override
    public boolean isStatusBarTranslucent() {
        return false;
    }

    @Override
    public void setStatusBarTranslucent(boolean statusBarTranslucent) {

    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {

    }

    @Override
    public int getGravity() {
        return 0;
    }

    @Override
    public void setGravity(int gravity) {

    }
}
