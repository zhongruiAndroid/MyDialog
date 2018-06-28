package com.test.mydialog.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/24.
 */

public class LinearLayout4 extends LinearLayout {
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
    public LinearLayout4(Context context) {
        super(context);
        init();
    }
    public LinearLayout4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public LinearLayout4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
//        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.theme_color));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));



        path=new Path();
        RectF rectF=new RectF(getPaddingLeft(),getPaddingTop(),getWidth()-getPaddingRight(),getHeight()-getPaddingBottom());
        path.addRoundRect(rectF,w/2,w/2, Path.Direction.CW);
//        PointF pointF=new PointF(getWidth()/2,getHeight()/2);
//        path.addCircle(pointF.x,pointF.y,w/2, Path.Direction.CW);
//        path.moveTo(0, 0);  // 通过空操作让Path区域占满画布
//        path.moveTo(w, h);
    }



    Paint paint;
    Path path;
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        int saveLayer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

//        canvas.drawPath(path,paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(saveLayer);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG|Paint.ANTI_ALIAS_FLAG));
        canvas.clipPath(path);

        return super.drawChild(canvas, child, drawingTime);
    }
}
