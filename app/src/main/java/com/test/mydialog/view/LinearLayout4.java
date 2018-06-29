package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.test.mydialog.BuildConfig;

/**
 * Created by Administrator on 2018/5/24.
 */

public class LinearLayout4 extends LinearLayout {
    public boolean isDebug=true;
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

    private void init() {
        borderColor= Color.TRANSPARENT;
        borderColor=Color.BLUE;
    }


    Paint mPaint;
    Paint borderPaint;
    float borderWidth=20;
    int borderColor;

    Path mPath;
    Path borderPath;
    Region clickRegion;
    Region viewRegion;
    boolean isCircle=true;

    float allRadius=270;

    float topLeftRadius;
    float topRightRadius;
    float bottomLeftRadius;
    float bottomRightRadius;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        clickRegion=new Region();
        viewRegion=new Region(0,0,w,h);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setFilterBitmap(false);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        borderPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);
        float gradientLeft=getPaddingLeft()-borderWidth/2;
        float gradientTop=getPaddingTop()-borderWidth/2;
        float gradientRigth=w-getPaddingRight()+borderWidth/2;
        float gradientBottom=h-getPaddingBottom()+borderWidth/2;
        LinearGradient linearGradient = new LinearGradient(gradientLeft,
                gradientTop,
                gradientRigth,
                gradientTop,
                new int[]{Color.BLUE,Color.GREEN},
                new float[]{0f,1f}, Shader.TileMode.CLAMP);

        int  X=(w-getPaddingLeft()-getPaddingRight())/2+getPaddingLeft();
        int  Y=(h-getPaddingTop()-getPaddingBottom())/2+getPaddingTop();
//        SweepGradient sweepGradient=new SweepGradient(X,Y,new int[]{Color.BLUE,Color.GREEN},new float[]{0.7f,1f});
//        SweepGradient sweepGradient=new SweepGradient(X,Y,Color.BLUE,Color.GREEN);
        RadialGradient radialGradient=new RadialGradient(X,Y,(w-getPaddingLeft()-getPaddingRight())/2,Color.BLUE,Color.GREEN, Shader.TileMode.CLAMP);
//        borderPaint.setShader(radialGradient);

//        borderPaint.setPathEffect(new DashPathEffect(new float[]{22,22},1));

        mPath=new Path();
        borderPath=new Path();
        float[]radius=new float[8];
        if(allRadius>0){
            radius[0]=allRadius;
            radius[1]=allRadius;

            radius[2]=allRadius;
            radius[3]=allRadius;

            radius[4]=allRadius;
            radius[5]=allRadius;

            radius[6]=allRadius;
            radius[7]=allRadius;
        }else{
            radius[0]=topLeftRadius;
            radius[1]=topLeftRadius;

            radius[2]=topRightRadius;
            radius[3]=topRightRadius;

            radius[4]=bottomLeftRadius;
            radius[5]=bottomLeftRadius;

            radius[6]=bottomRightRadius;
            radius[7]=bottomRightRadius;
        }
        RectF rectF=new RectF(getPaddingLeft(),getPaddingTop(),w-getPaddingRight(),h-getPaddingBottom());
        if(isCircle){//裁剪是以padding外部为界限还是padding内部
            int  centerX=(w-getPaddingLeft()-getPaddingRight())/2+getPaddingLeft();
            int  centerY=(h-getPaddingTop()-getPaddingBottom())/2+getPaddingTop();

            int  circleRadius=(w-getPaddingLeft()-getPaddingRight())>(h-getPaddingTop()-getPaddingBottom())?(h-getPaddingTop()-getPaddingBottom())/2:(w-getPaddingLeft()-getPaddingRight())/2;
            mPath.addCircle(centerX,centerY,circleRadius, Path.Direction.CW);
            borderPath.addCircle(centerX,centerY,circleRadius, Path.Direction.CW);
        }else{
            mPath.addRoundRect(rectF,radius, Path.Direction.CW);
            borderPath.addRoundRect(rectF,radius, Path.Direction.CW);
        }

        clickRegion.setPath(mPath,viewRegion);
        mPath.moveTo(0,0);
        mPath.moveTo(w,h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveLayer = canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

        canvas.drawPath(mPath,mPaint);
        canvas.drawPath(borderPath,borderPaint);
        canvas.restoreToCount(saveLayer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(clickRegion.contains((int)event.getX(),(int)event.getY())){
                return super.onTouchEvent(event);
            }else{
                return false;
            }
        }
        return super.onTouchEvent(event);
    }
}
