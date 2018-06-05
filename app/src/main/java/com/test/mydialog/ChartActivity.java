package com.test.mydialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends AppCompatActivity {
    LineChartView chart;
    private ArrayList<Line> lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chart= (LineChartView) findViewById(R.id.chart);
        initData();
    }

    private void initData() {
        /*Chart.setInteractive(boolean isInteractive);
        Chart.setZoomType(ZoomType zoomType);
        Chart.setContainerScrollEnabled(boolean isEnabled, ContainerScrollType type);

        ChartData.setAxisXBottom(Axis axisX);
        ColumnChartData.setStacked(boolean isStacked);
        Line.setStrokeWidth(int strokeWidthDp);*/



        List<PointValue> values = new ArrayList<PointValue>();
        for (float i = 0; i < 10; i+=0.1) {
            values.add(new PointValue(i, i+20));
//            values.add(new PointValue(2, 4));
//            values.add(new PointValue(4, 3));
//            values.add(new PointValue(6, 4));
//            values.add(new PointValue(8, 50));
        }

        List<PointValue> values2 = new ArrayList<PointValue>();
        values2.add(new PointValue(0.0f, 0.1f));
        values2.add(new PointValue(0.1f, 0.3f));
        values2.add(new PointValue(0.2f, 0.5f));
        values2.add(new PointValue(0.3f, 0.7f));
        values2.add(new PointValue(0.4f, 0.9f));

        //In most cased you can call data model methods in builder-pattern-like manner.

        List<Line> lines = new ArrayList<Line>();
        lines.add(getLine(values,Color.BLUE));
        lines.add(getLine(values2,Color.GREEN));

        LineChartData data = new LineChartData();
        Axis axisX = new Axis();
        List<AxisValue>list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new AxisValue(i));
        }
        axisX.setValues(list);

        Axis axisY = new Axis();
        List<AxisValue>list2=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list2.add(new AxisValue(i));
        }
        axisY.setValues(list2);
        data.setAxisYLeft(axisX);
        data.setAxisXBottom(axisY);
        data.setLines(lines);

//        LineChartView chart = new LineChartView(this);
        chart.setLineChartData(data);
//        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

//        Viewport v = new Viewport(chart.getMaximumViewport());
        Viewport v = new Viewport(chart.getMaximumViewport());
        v.left = 1;
        v.right = 0;
        chart.setCurrentViewport(v);

//        chart.setInteractive(true);//设置图表是可以交互的（拖拽，缩放等效果的前提）
//        chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);//设置缩放方向
//        chart.setViewportCalculationEnabled(false);
//        chart.setZoomEnabled(false);
    }

    public Line getLine(List<PointValue> values,@ColorInt int color){
        Line line = new Line(values).setColor(color).setCubic(true);
        line.setCubic(false);
        return line;
    }
}
