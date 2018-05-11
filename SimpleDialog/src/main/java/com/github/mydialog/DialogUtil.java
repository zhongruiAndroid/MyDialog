package com.github.mydialog;

import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/8.
 */

public class DialogUtil {
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
    public static void setBottomSheetDialogWH(BottomSheetDialog bottomSheetDialog,int width,int height){
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
