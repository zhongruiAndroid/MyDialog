package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CircleProgress extends View {
    public boolean isDebug=true;
    private int centerX;
    private int centerY;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    private Paint mPaint;

    public CircleProgress(Context context) {
        super(context);
        initAttr(null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }


        initData();
        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }


    //内圆颜色
    private int neiYuanColor;
    //圆环半径
    private int ringRadius;
    //圆环宽度
    private int ringWidth;
    //圆环颜色
    private int ringColor;
    //圆环进度颜色
    private int ringProgressColor;

    private void initData() {
        neiYuanColor=ContextCompat.getColor(getContext(),R.color.white);
        ringRadius =300;
        ringWidth=30;
        ringColor=ContextCompat.getColor(getContext(),R.color.gray_99);
        ringProgressColor=ContextCompat.getColor(getContext(),R.color.green1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        //绘制圆环
        drawRing(canvas);
        drawNeiYuan(canvas);
        //绘制进度圆环
        drawProgressRing(canvas);
    }

    private void drawNeiYuan(Canvas canvas) {

        mPaint.setColor(neiYuanColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX,centerY,ringRadius-ringWidth/2,mPaint);
    }

    private void drawProgressRing(Canvas canvas) {

        mPaint.setColor(ringProgressColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
//        canvas.drawCircle(centerX,centerY, ringRadius,mPaint);
       /* *//**
         * 画弧形
         * float left：表示弧形呈360度显示时，对应的最左边那个端点所对应的 x 坐标
         * float top：表示弧形呈360度显示时，对应的最上边那个端点所对应的 y 坐标
         * float right：表示弧形呈360度显示时，对应的最右边那个端点所对应的 x 坐标
         * float bottom：表示弧形呈360度显示时，对应的最下边那个端点所对应的 y 坐标
         * float startAngle：
         * 表示与水平方向呈多少角度开始绘制弧形，顺时针的角度记为正
         * float sweepAngle：
         * 表示绘制弧形时所对应的扇形角度，数值为正，则表示从顺时针方向开始绘制
         * boolean useCenter：
         * true表示扇形需要中间那一部分三角形，
         * false表示显示的是：从扇形中出去中间那个三角形之后的一小部分的弧形
         * Paint paint：表示我们所用的画笔
         *//*
        mPaint.setStrokeJoin(Paint.Join.MITER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(200,200,500,500,30,90,true,mPaint);
        }*/

//        canvas.drawArc(oval, -90, alphaAngle, false, circlePaint);
        RectF rectF=new RectF(centerX-ringRadius,centerY-ringRadius,centerX+ringRadius,centerY+ringRadius);
        mPaint.setShadowLayer(1000,1000,1000,ContextCompat.getColor(getContext(),R.color.blue_00));

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF,-90,145,false,mPaint);
    }
//    @SuppressLint("NewApi")
//    @Override
//    public void setLayerType(int layerType, @Nullable Paint paint) {
//        super.setLayerType(LAYER_TYPE_SOFTWARE, paint);
//        setLayerType( LAYER_TYPE_SOFTWARE , null);
//        //this.setLayerType(LAYER_TYPE_SOFTWARE,null);
//    }

    private void drawRing(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setColor(ringColor);
        canvas.drawCircle(centerX,centerY, ringRadius,mPaint);
    }
}
