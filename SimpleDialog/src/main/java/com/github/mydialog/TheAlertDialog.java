package com.github.mydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

import static com.github.mydialog.BaseDialogHelper.negative;
import static com.github.mydialog.BaseDialogHelper.neutral;
import static com.github.mydialog.BaseDialogHelper.positive;
import static com.github.mydialog.BaseDialogHelper.title;

public class TheAlertDialog extends AlertDialog {


    protected TheAlertDialog(@NonNull Context context) {
        super(context);
    }

    protected TheAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TheAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder extends AlertDialog.Builder{

        public Builder(@NonNull Context context) {
            super(context);
        }
        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            super(context, themeResId);
        }
        /**************************************跳过********************************************/
        public AlertDialog.Builder setNeutralButton() {
            return super.setNeutralButton(neutral, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        public AlertDialog.Builder setNeutralButton(OnClickListener listener) {
            return super.setNeutralButton(neutral, listener);
        }
        /**************************************取消********************************************/
        public AlertDialog.Builder setNegativeButton() {
            return super.setNegativeButton(negative, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        public AlertDialog.Builder setNegativeButton(OnClickListener listener) {
            return super.setNegativeButton(negative, listener);
        }
        /**************************************确定********************************************/
        public AlertDialog.Builder setPositiveButton() {
            return super.setPositiveButton(positive, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        public AlertDialog.Builder setPositiveButton(OnClickListener listener) {
            return super.setPositiveButton(positive, listener);
        }
        /**************************************提示********************************************/
        public AlertDialog.Builder setTitle() {
            return super.setTitle(title);
        }
    }


    /*返回dialog window宽度*/
    public int getWidth(){
        return getWindow().getAttributes().width;
    };
    /*返回dialog window高度*/
    public int getHeight(){
        return getWindow().getAttributes().height;
    };

}
