package com.test.mydialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.mydialog.view.CircleProgress;

public class ClockActivity extends AppCompatActivity {
    CircleProgress circleprogress;
    AppCompatSeekBar seekbar,seekbar2;
    TextView tv_num;
    TextView tv_percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        circleprogress= (CircleProgress) findViewById(R.id.circleprogress);
        circleprogress.setOnCircleProgressInter(new CircleProgress.OnCircleProgressInter() {
            @Override
            public void progress(int progress, int max) {
                tv_num.setText(progress+"");
                tv_percent.setText((progress*100/max)+"%");
            }
        });
        seekbar= (AppCompatSeekBar) findViewById(R.id.seekbar);
        seekbar2= (AppCompatSeekBar) findViewById(R.id.seekbar2);
        tv_num= (TextView) findViewById(R.id.tv_num);
        tv_percent= (TextView) findViewById(R.id.tv_percent);
        initView();
    }

    private void initView() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleprogress.setProgress(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                circleprogress.setStartAngle(90+progress/2);
                circleprogress.setDisableAngle(progress);
                circleprogress.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
