package com.test.mydialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.PointValue;

public class ChartActivity2 extends AppCompatActivity {
    LineChart chart;
    private ArrayList<Line> lines;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);
        chart= (LineChart) findViewById(R.id.chart);
        initData();

    }

    private void initData() {
        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 1440; i++) {
            entries.add(new Entry(i, (float) (Math.random()*200+100)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.GREEN);
        dataSet.setDrawCircles(false);

//        LineData lineData = new LineData(dataSet);
        lineData = new LineData(dataSet);


        List<Entry> entries2 = new ArrayList<Entry>();

        for (float i = 0; i < 1440; i++) {
            entries2.add(new Entry(i, (float) (Math.random()*400+100)));
        }
        LineDataSet dataSet2 = new LineDataSet(entries2, "Label"); // add entries to dataset
        dataSet2.setColor(Color.GREEN);
        dataSet2.setDrawCircles(false);
        dataSet2.setValueTextColor(Color.RED);

        lineData.addDataSet(dataSet2);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(80);
//        xAxis.setLabelRotationAngle(45);


//        xAxis.setAxisMaximum(100);
//        xAxis.setXOffset(10);
//        xAxis.setSpaceMax(40);
//        xAxis.setAxisMinimum(20);
//        xAxis.setGranularity(20);


        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisRight().setAxisMinimum(0f);

        chart.setData(lineData);
        chart.invalidate(); // refresh
//        initData2();
    }

    private void initData2() {
        List<Entry> entries = new ArrayList<Entry>();
        int j=0;
        for (int i = 0; i < 2880; i++) {
            entries.add(new Entry(i, (float) (Math.random()*200+100)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.GREEN);
        dataSet.setValueTextColor(Color.RED);

        lineData.addDataSet(dataSet);
        chart.setData(lineData);
    }

    public Line getLine(List<PointValue> values,@ColorInt int color){
        Line line = new Line(values).setColor(color).setCubic(true);
        line.setCubic(false);
        return line;
    }
}
