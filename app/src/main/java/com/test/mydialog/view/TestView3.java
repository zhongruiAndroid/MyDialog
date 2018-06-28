package com.test.mydialog.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/24.
 */

public class TestView3 extends View {
    public boolean isDebug=true;
    private double v;
    private double v2;

    private Region newRegion;
    private Region leftRegion;
    private Region topRegion;
    private Region rightRegion;
    private Region bottomRegion;
    private int x;
    private int y;
    int touchFlag=-1;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public TestView3(Context context) {
        super(context);
        init();
    }
    public TestView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint mPaint;
    Paint pointPaint;
    int strokeWidth=2;
    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setDither(true);
        pointPaint.setStrokeWidth(13);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));

    }



    private Path path;
    private Path leftPath;
    private Path topPath;
    private Path rightPath;
    private Path bottomPath;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        RectF bRectF=new RectF(10,10,600,500);
        RectF sRectF=new RectF(130,130,480,380);
        RectF cRectF=new RectF(150,150,460,360);
        int bAngle=84;
        int sAngle=-82;
        Region region=new Region (10,10,600,500);
        newRegion = new Region();
        leftRegion = new Region();
        topRegion = new Region();
        rightRegion = new Region();
        bottomRegion = new Region();

        path = new Path();
//        path.addCircle(300,300,300, Path.Direction.CW);
        path.addArc(cRectF,0,360);
        newRegion.setPath(path,region);

        leftPath = new Path();
        leftPath.addArc(bRectF,-40,bAngle);
        leftPath.arcTo(sRectF,bAngle-40,sAngle);
        leftPath.close();
        leftRegion.setPath(leftPath,region);

        topPath = new Path();
        topPath.addArc(bRectF,-130,bAngle);
        topPath.arcTo(sRectF,bAngle-130,sAngle);
        topPath.close();
        topRegion.setPath(topPath,region);



        rightPath = new Path();
        rightPath.addArc(bRectF,-220,bAngle);
        rightPath.arcTo(sRectF,bAngle-220,sAngle);
        rightPath.close();
        rightRegion.setPath(rightPath,region);


        bottomPath = new Path();
        bottomPath.addArc(bRectF,50,bAngle);
        bottomPath.arcTo(sRectF,bAngle+50,sAngle);
        bottomPath.close();
        bottomRegion.setPath(bottomPath,region);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.theme_color));
        paint.setStrokeWidth(10);

    }



    Paint paint;
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getWidth()/2,getHeight()/2);
        Matrix matrix = canvas.getMatrix();
        float[]aa=new float[]{x,y};
        float[]b=new float[2];
        matrix.mapPoints(b,aa);
        Log("22=--=="+b[0]+"==="+b[1]);
        canvas.drawPoint(x,y,paint);
        float[]src=new float[]{x,y};
        float[]dts=new float[2];
        Matrix a=new Matrix();
        boolean invert = canvas.getMatrix().invert(a);
        a.mapPoints(dts,src);
        Log(invert+"==="+dts[0]+"====--======"+dts[1]);
        canvas.drawPoint(dts[0],dts[1],paint);
        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                touchFlag=-1;
                Log(event.getRawX()+"=="+event.getRawY());
                x = (int) event.getX();
                y = (int) event.getY();
                if (newRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击center"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (leftRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击left"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (topRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击top"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (rightRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击right"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (bottomRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击bottom"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
//                invalidate();
            break;
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                Log(x+"==--------=="+y);
                Log(event.getRawX()+"==--------=="+event.getRawY());
                if (newRegion.contains(x, y)) {
                    touchFlag=0;
                }
                if (leftRegion.contains(x, y)) {
                    touchFlag=1;
                }
                if (topRegion.contains(x, y)) {
                    touchFlag=2;
                }
                if (rightRegion.contains(x, y)) {
                    touchFlag=3;
                }
                if (bottomRegion.contains(x, y)) {
                    touchFlag=4;
                }
                invalidate();
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

}
