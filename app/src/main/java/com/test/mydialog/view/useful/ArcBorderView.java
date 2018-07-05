package com.test.mydialog.view.useful;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.test.mydialog.R;


/**
 * Created by Administrator on 2018/7/5.
 */

public class ArcBorderView extends View {
    private Paint mPaint;
    private Paint arcPaint;
    public ArcBorderView(Context context) {
        super(context);
        init(null);
    }
    public ArcBorderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArcBorderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private float borderWidth=5;
    private float circleRadius =200;
    private float scaleRadius =0.25f;
    private float circleX=-1;
    private float circleY;
    private int borderColor=Color.DKGRAY;

    private void init(AttributeSet attrs) {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        arcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);

        if(attrs!=null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ArcBorderView);
            borderWidth= typedArray.getDimension(R.styleable.ArcBorderView_borderWidth, 5);
            borderColor= typedArray.getColor(R.styleable.ArcBorderView_borderColor, Color.DKGRAY);
            circleRadius= typedArray.getDimension(R.styleable.ArcBorderView_circleRadius, 200);
            circleX= typedArray.getDimension(R.styleable.ArcBorderView_circleX, -1);
            scaleRadius= typedArray.getFloat(R.styleable.ArcBorderView_scaleRadius, 0.25f);

            typedArray.recycle();
        }



        refreshPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float w=300;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT&&getLayoutParams().width==ViewGroup.LayoutParams.WRAP_CONTENT){
            float h= circleRadius *scaleRadius +borderWidth/2;
            setMeasuredDimension((int)w,(int)h);
        }else if(getLayoutParams().width== ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension((int)w,height);
        }else if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT){
            float h= circleRadius *scaleRadius +borderWidth/2;
            setMeasuredDimension(width,(int)h);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        circleY =h- circleRadius *scaleRadius + circleRadius;
        if(circleX==-1){
            circleX=w/2;
        }
    }

    public void refreshPaint(){
        mPaint.setStrokeWidth(borderWidth*2);
//        mPaint.setColor(borderColor);

        arcPaint.setStrokeWidth(borderWidth);
        arcPaint.setColor(borderColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refreshPaint();

        Path path=new Path();
        path.moveTo(0,getHeight());
        path.lineTo(getWidth(),getHeight());

        Path arcPath=new Path();
        arcPath.addCircle(circleX, circleY, circleRadius, Path.Direction.CW);

        Path secondArcPath=new Path();
        secondArcPath.set(arcPath);
        secondArcPath.moveTo(0,-getHeight()/2);
        secondArcPath.moveTo(getWidth(),getHeight()/2);

        arcPath.addPath(path);


        canvas.saveLayer(new RectF(0,0,getWidth(),getHeight()),null,Canvas.ALL_SAVE_FLAG);


        canvas.drawPath(arcPath,arcPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawPath(secondArcPath,mPaint);
        mPaint.setXfermode(null);

        canvas.restore();
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public ArcBorderView setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public float getCircleRadius() {
        return circleRadius;
    }

    public ArcBorderView setCircleRadius(float circleRadius) {
        this.circleRadius = circleRadius;
        return this;
    }

    public float getScaleRadius() {
        return scaleRadius;
    }

    public ArcBorderView setScaleRadius(float scaleRadius) {
        this.scaleRadius = scaleRadius;
        return this;
    }

    public float getCircleX() {
        return circleX;
    }

    public ArcBorderView setCircleX(float circleX) {
        this.circleX = circleX;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public ArcBorderView setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        return this;
    }
    public void complete(){
        invalidate();
    }
}
