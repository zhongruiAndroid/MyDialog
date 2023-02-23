package com.test.mydialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.github.androidtools.StatusBarUtil;
import com.github.mydialog.TheDialog;
import com.github.statusbar.StatusBarUtils;

public class TestFullDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox cbFull0;
    private CheckBox cbFull1;
    private CheckBox cbFull2;
    private CheckBox cbDialogFull0;
    private CheckBox cbDialogFull2;
    private CheckBox cbDialogFull1;
    private CheckBox cbDialogUseActStatus3;
    private TheDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_full_dialog);
//        StatusBarUtils.setStatusColor(getWindow(),Color.parseColor("#18B4ED"),true);
        findViewById(R.id.btTestFull).setOnClickListener(this);
        cbFull0 = findViewById(R.id.cbFull0);
        cbFull0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFull0.isChecked()) {
                    StatusBarUtils.setStatusColor(getWindow(), Color.TRANSPARENT, cbFull0.isChecked());
                } else {
                    StatusBarUtils.setStatusColor(getWindow(), Color.parseColor("#18B4ED"), cbFull0.isChecked());
                }
            }
        });
        cbFull1 = findViewById(R.id.cbFull1);
        cbFull2 = findViewById(R.id.cbFull2);
    }

    private void testDialogFull() {
        dialog = new TheDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.test_full_dialog, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.getWindow().setAttributes(attributes);


        cbDialogFull0 = view.findViewById(R.id.cbDialogFull0);
        cbDialogFull0.setOnClickListener(this);

        cbDialogFull2 = view.findViewById(R.id.cbDialogFull2);
        cbDialogFull2.setOnClickListener(this);

        cbDialogFull1 = view.findViewById(R.id.cbDialogFull1);
        cbDialogFull1.setOnClickListener(this);

        cbDialogUseActStatus3 = view.findViewById(R.id.cbDialogUseActStatus3);
        cbDialogUseActStatus3.setOnClickListener(this);



        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btTestFull:
                testDialogFull();
                break;
            case R.id.cbDialogFull0:
            case R.id.cbDialogFull2:
            case R.id.cbDialogFull1:

                if(dialog==null){
                    return;
                }
                dialog.setFullScreenCompat(cbDialogFull1.isChecked(), cbDialogFull2.isChecked());
                dialog.setIntoStatusBar(cbDialogFull0.isChecked());
                dialog.setTranslucentStatus(cbDialogUseActStatus3.isChecked());
               /* if (cbDialogFull0.isChecked()) {
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    intoStatusBar(dialog.getWindow(), true);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                } else {

                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    intoStatusBar(dialog.getWindow(), false);
//                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                }
                FullScreenUtils.fullScreen(dialog.getWindow(), cbDialogFull1.isChecked(), cbDialogFull2.isChecked());*/
                break;
            case R.id.cbDialogUseActStatus3:
                if (dialog != null) {
//                    dialog.setUseActStatusBarFontColor(cbDialogUseActStatus3.isChecked());
                    dialog.setIntoStatusBarV1(cbDialogUseActStatus3.isChecked());
                }
                break;
        }
    }
}