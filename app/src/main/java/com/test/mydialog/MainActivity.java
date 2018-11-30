package com.test.mydialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;

import com.github.mydialog.DialogDecor;
import com.github.mydialog.TheAlertDialog;
import com.github.mydialog.TheDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior behavior;
    private boolean behaviorFlag = true;
    private int peekHeight;

    CheckBox cb_bottomsheets;
    CheckBox cb_bottomsheets_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_mydialog_top).setOnClickListener(this);
        findViewById(R.id.tv_mydialog_center).setOnClickListener(this);
        findViewById(R.id.tv_mydialog_bottom).setOnClickListener(this);
        findViewById(R.id.tv_bottomsheetdialog).setOnClickListener(this);
        findViewById(R.id.tv_bottomsheetdialogfragment).setOnClickListener(this);

        cb_bottomsheets = (CheckBox) findViewById(R.id.cb_bottomsheets);
        cb_bottomsheets.setOnClickListener(this);

        cb_bottomsheets_setting = (CheckBox) findViewById(R.id.cb_bottomsheets_setting);
        cb_bottomsheets_setting.setOnClickListener(this);
        cb_bottomsheets_setting.setChecked(true);

        findViewById(R.id.tv_hidden).setOnClickListener(this);
        findViewById(R.id.tv_collapsed).setOnClickListener(this);
        findViewById(R.id.tv_dragging).setOnClickListener(this);
        findViewById(R.id.tv_settling).setOnClickListener(this);
        findViewById(R.id.tv_expanded).setOnClickListener(this);
        bottomSheets();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mydialog_top:
             /*   dialog = new MySimpleDialog(this);
                dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog, null));
                dialog.setGravity(Gravity.TOP);
                dialog.setFullWidth();
                dialog.setRadius(40);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                dialog.getWindow().setWindowAnimations(R.style.MyDialogAnimationTop);
                dialog.show();
*/
                TheAlertDialog.Builder builder = new TheAlertDialog.Builder(this);

//                builder.setView(getLayoutInflater().inflate(R.layout.dialog, null));

                builder.setView(getLayoutInflater().inflate(R.layout.dialog, null));
//                builder.setTitle("提示");
//                builder.setMessage("消息");
//                builder.setNegativeButton();
//                builder.setPositiveButton();
//                builder.setNeutralButton();


                DialogDecor dialogDecor = DialogDecor.newInstance(builder)
                        .setAlpha(0.9f)
                        .setDimAmount(0.3f)
                        .setHeight(300)
                        .setFullWidth()
//                        .setFullHeight()
//                        .setBackgroundColor(ContextCompat.getColor(this, R.color.white))
//                         .setBackgroundDrawableResource(R.color.wheel_bg)
                        .setTranslucentStatus(false)
                        .setPadding(0)
                        .setGravity(Gravity.BOTTOM)
//                        .setRadius(150)
                        ;
                Log(dialogDecor.getWidth() + "===" + dialogDecor.getHeight());
                dialogDecor.show();
                Log(dialogDecor.getWidth() + "===" + dialogDecor.getHeight());
/*                Window window1 = dialogDecor.getWindow();

                WindowManager.LayoutParams lp = window1.getAttributes();
                window1.getDecorView().setPadding(0, 0, 0, 0);

                lp.width = PhoneUtils.getScreenWidth(this);
                window1.setAttributes(lp);

                dialogDecor.show();*/

//                dialogDecor.getWindow().setWindowAnimations(R.style.MyDialogAnimationBottom);
//                dialogDecor.show();

//                builder.show();


                break;
            case R.id.tv_mydialog_center:



//                showDialog();
                break;
            case R.id.tv_mydialog_bottom:

                TheDialog theDialog = new TheDialog(this);


                theDialog.setContentView(getLayoutInflater().inflate(R.layout.dialog, null));
                theDialog.setAlpha(0.9f)
                        .setDimAmount(0.3f)
//                        .setHeight(200)
//                        .setWidth(600)
                        .setFullWidth()
//                        .setFullHeight()
                        .setBackgroundColor(ContextCompat.getColor(this, R.color.white))
//                        .setBackgroundDrawableResource(R.color.wheel_bg)
                        .setTranslucentStatus(false)
                        .setPadding(0)
                        .setGravity(Gravity.BOTTOM)
//                        .setRadius(20)
                ;
                theDialog.show();


                break;
            case R.id.tv_bottomsheetdialog:
                View bottomDialogView = getLayoutInflater().inflate(R.layout.dialog2, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(bottomDialogView);
                bottomSheetDialog.show();
//                DialogUtil.setBottomSheetDialogHeight(bottomSheetDialog,300);


                break;
            case R.id.tv_bottomsheetdialogfragment:
                SheetDialogFragment sheetDialogFragment = SheetDialogFragment.newInstance();
                sheetDialogFragment.show(getSupportFragmentManager(), "tag");

                break;
            case R.id.cb_bottomsheets:
                if (cb_bottomsheets.isChecked()) {
                    cb_bottomsheets.setText("peekHeight-45dp");
                    behavior.setPeekHeight(135);
                } else {
                    cb_bottomsheets.setText("peekHeight-0dp");
                    behavior.setPeekHeight(0);
                }
                break;
            case R.id.cb_bottomsheets_setting:
                if (cb_bottomsheets_setting.isChecked()) {
                    cb_bottomsheets_setting.setText("hideable-true");
                } else {
                    cb_bottomsheets_setting.setText("hideable-false");
                }
                behavior.setHideable(cb_bottomsheets_setting.isChecked());
                break;
            case R.id.tv_hidden:
                if (cb_bottomsheets_setting.isChecked()) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                break;
            case R.id.tv_collapsed:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.tv_dragging:
                behavior.setState(BottomSheetBehavior.STATE_DRAGGING);
                break;
            case R.id.tv_settling:
                behavior.setState(BottomSheetBehavior.STATE_SETTLING);
                break;
            case R.id.tv_expanded:
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
        }
    }

    private void bottomSheets() {
        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet状态的改变
                Log("***" + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
                Log("&&&" + slideOffset);
            }
        });
    }

    public void Log(String log) {
        Log.i("===", log);
    }


}
