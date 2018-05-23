package com.test.mydialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import com.test.mydialog.view.CircleProgress;

public class ClockActivity extends AppCompatActivity {
    CircleProgress circleprogress;
    AppCompatSeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        circleprogress= (CircleProgress) findViewById(R.id.circleprogress);

        seekbar= (AppCompatSeekBar) findViewById(R.id.seekbar);
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
    }
}
