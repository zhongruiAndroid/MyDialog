package com.test.mydialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.mydialog.view.MyView;

public class MyViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///
        setContentView(R.layout.activity_my_view);

        MyView my_view= (MyView) findViewById(R.id.my_view);
        my_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyViewActivity.this, ChartActivity2.class));
//                startActivity(new Intent(MyViewActivity.this, ChartActivity.class));
//                startActivity(new Intent(MyViewActivity.this, ClockActivity.class));
            }
        });
    }


}
