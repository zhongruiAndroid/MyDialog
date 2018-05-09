package com.github.mydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2018/5/8.
 */

public class MyAlertDialog extends AlertDialog {
    private final int def_titleSize=20;
    private final int def_messageSize=16;
    private final int def_buttonSize=14;
    private static final String neutral="跳过";
    private static final String negative="取消";
    private static final String positive="确定";
    private static final String title="提示";

    protected MyAlertDialog(@NonNull Context context) {
        super(context);
    }
    protected MyAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    protected MyAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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

}
