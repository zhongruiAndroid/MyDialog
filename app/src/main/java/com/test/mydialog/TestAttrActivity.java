package com.test.mydialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mydialog.TheDialog;

public class TestAttrActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioButton rbTopTopLeft;
    private RadioButton rbTopBottomLeft;
    private RadioButton rbCenterLeft;
    private RadioButton rbBottomTopLeft;
    private RadioButton rbBottomBottomLeft;
    private RadioButton rbLeftLeftTop;
    private RadioButton rbLeftRightTop;
    private RadioButton rbCenterTop;
    private RadioButton rbRightLeftTop;
    private RadioButton rbRightRightTop;
    private RadioButton rbTopTopRight;
    private RadioButton rbTopBottomRight;
    private RadioButton rbCenterRight;
    private RadioButton rbBottomTopRight;
    private RadioButton rbBottomBottomRight;
    private RadioButton rbLeftLeftBottom;
    private RadioButton rbLeftRightBottom;
    private RadioButton rbCenterBottom;
    private RadioButton rbRightLeftBottom;
    private RadioButton rbRightRightBottom;
    private RadioButton rbShowLeft;
    private RadioButton rbShowTop;
    private RadioButton rbShowRight;
    private RadioButton rbShowBottom;
    private RadioButton rbTop;
    private RadioButton rbCenter;
    private RadioButton rbBottom;
    private Button btShow;

    private RadioGroup rgLeft;
    private RadioGroup rgTop;
    private RadioGroup rgShowType;
    private AppCompatCheckBox cbCanMove;
    private AppCompatCheckBox cbHiddenNavigation;
    private AppCompatCheckBox cbHiddenStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        test1();
        setContentView(R.layout.activity_test_attr);
        initView();
    }
    public   void test1(){
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void initView() {
        final TextView tv = findViewById(R.id.tv);
        tv.post(new Runnable() {
            @Override
            public void run() {

                int[] location = new int[2];
                tv.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                Log.i("=====", x + "==3===" + y);
            }
        });

        int[] location = new int[2];
        tv.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        Log.i("=====", x + "===2==" + y);

        rgLeft = findViewById(R.id.rgLeft);
        rgLeft.setOnCheckedChangeListener(this);

        rgTop = findViewById(R.id.rgTop);
        rgTop.setOnCheckedChangeListener(this);


        rbTop = findViewById(R.id.rbTop);
        rbCenter = findViewById(R.id.rbCenter);
        rbBottom = findViewById(R.id.rbBottom);
        rgShowType = findViewById(R.id.rgShowType);
        rgShowType.setOnCheckedChangeListener(this);

        rbTopTopLeft = findViewById(R.id.rb_top_top_left);
        rbTopBottomLeft = findViewById(R.id.rb_top_bottom_left);
        rbCenterLeft = findViewById(R.id.rb_center_left);
        rbBottomTopLeft = findViewById(R.id.rb_bottom_top_left);
        rbBottomBottomLeft = findViewById(R.id.rb_bottom_bottom_left);
        rbLeftLeftTop = findViewById(R.id.rb_left_left_top);
        rbLeftRightTop = findViewById(R.id.rb_left_right_top);
        rbCenterTop = findViewById(R.id.rb_center_top);
        rbRightLeftTop = findViewById(R.id.rb_right_left_top);
        rbRightRightTop = findViewById(R.id.rb_right_right_top);
        rbShowLeft = findViewById(R.id.rbShowLeft);
        rbShowTop = findViewById(R.id.rbShowTop);
        rbShowRight = findViewById(R.id.rbShowRight);
        rbShowBottom = findViewById(R.id.rbShowBottom);
        btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(this);


        cbCanMove = findViewById(R.id.cbCanMove);
        cbHiddenNavigation = findViewById(R.id.cbHiddenNavigation);
        cbHiddenStatusBar = findViewById(R.id.cbHiddenStatusBar);
        cbHiddenStatusBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          /*      if(isChecked){
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }else{
                    int nowFlags = getWindow().getAttributes().flags;
                    nowFlags&=~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }*/
                if(isChecked){
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }else{
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btShow:
                showDialog();
                break;
        }
    }

    private int directId = R.id.rbShowBottom;

    private int leftAndRight = TheDialog.top_top;
    private int topAndBottom = TheDialog.left_left;

    private void showDialog() {
        TestDialog2 dialog = new TestDialog2(this);
        dialog.getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        ViewGroup decorView = (ViewGroup) dialog.getWindow().getDecorView();
        View viewById = decorView.findViewById(android.R.id.content);
        viewById.setBackgroundColor(Color.RED);
        dialog.setCanMoveDialog(cbCanMove.isChecked());
        dialog.setHideNavigation(cbHiddenNavigation.isChecked());
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText("test");
//        dialog.setContentView(textView);
        dialog.setDimAmount(0.2f);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setWidth(200);
        dialog.setHeight(200);
        dialog.setFullWidth();
        if(rbTop.isChecked()){
            dialog.setGravity(Gravity.TOP);
            dialog.show();
            return;
        }
        if(rbCenter.isChecked()){
            dialog.setGravity(Gravity.CENTER);
            dialog.show();
            return;
        }
        if(rbBottom.isChecked()){
            dialog.setGravity(Gravity.BOTTOM);
            dialog.show();
            return;
        }
        if (directId == R.id.rbShowLeft) {
            dialog.showAsLeft(rbCenterTop, 0, 0, leftAndRight);
        } else if (directId == R.id.rbShowTop) {
            dialog.showAsTop(rbCenterTop, 0, 0, topAndBottom);
        } else if (directId == R.id.rbShowRight) {
            dialog.showAsRight(rbCenterTop, 0, 0, leftAndRight);
        } else if (directId == R.id.rbShowBottom) {
            dialog.showAsBottom(rbCenterTop, 0, 0, topAndBottom);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int checkedRadioButtonId = group.getCheckedRadioButtonId();
        switch (group.getId()) {
            case R.id.rgLeft:
                if (R.id.rb_top_top_left == checkedId) {
                    leftAndRight = TheDialog.top_top;
                } else if (R.id.rb_top_bottom_left == checkedId) {
                    leftAndRight = TheDialog.top_bottom;
                } else if (R.id.rb_center_left == checkedId) {
                    leftAndRight = TheDialog.center;
                } else if (R.id.rb_bottom_top_left == checkedId) {
                    leftAndRight = TheDialog.bottom_top;
                } else if (R.id.rb_bottom_bottom_left == checkedId) {
                    leftAndRight = TheDialog.bottom_bottom;
                }
                break;
            case R.id.rgTop:
                if (R.id.rb_left_left_top == checkedId) {
                    topAndBottom = TheDialog.left_left;
                } else if (R.id.rb_left_right_top == checkedId) {
                    topAndBottom = TheDialog.left_right;
                } else if (R.id.rb_center_top == checkedId) {
                    topAndBottom = TheDialog.center;
                } else if (R.id.rb_right_left_top == checkedId) {
                    topAndBottom = TheDialog.right_left;
                } else if (R.id.rb_right_right_top == checkedId) {
                    topAndBottom = TheDialog.right_right;
                }
                break;
            case R.id.rgShowType:
                directId = checkedRadioButtonId;
                break;
        }
    }
}
