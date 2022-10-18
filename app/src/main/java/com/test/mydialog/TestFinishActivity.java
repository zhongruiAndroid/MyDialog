package com.test.mydialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finish);
        View btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDialog testDialog = showDialog();
                AppHandler.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testDialog.show();
                        finish();
                        testDialog.show();
                    }
                },2000);
            }
        });

        View btDismiss = findViewById(R.id.btDismiss);
        btDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDialog dismiss = dismissDialog();
                dismiss.show();
                AppHandler.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },2000);
                AppHandler.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss.dismiss();
                    }
                },4000);
            }
        });
    }

    public TestDialog showDialog() {
        Log.i("=====","=====show");
        TestDialog d = new TestDialog(this);
        return d;
    }
    public TestDialog dismissDialog() {
        Log.i("=====","=====dismissDialog");
        TestDialog d = new TestDialog(this);
        return d;
    }

    @Override
    protected void onDestroy() {
        Log.i("=====","=====onDestroy1");
        super.onDestroy();
        Log.i("=====","=====onDestroy2");
    }
}