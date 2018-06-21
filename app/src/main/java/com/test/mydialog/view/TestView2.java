package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
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

public class TestView2 extends View {
    public boolean isDebug=true;
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
    public TestView2(Context context) {
        super(context);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setDither(true);
        pointPaint.setStrokeWidth(3);
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


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.translate(getWidth()/2,getHeight()/2);


//        a(canvas);
       /* b(canvas);


        if(x>0){
            pointPaint.setStrokeWidth(9);
            pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.top_color));
            canvas.drawPoint(x,y,pointPaint);

            pointPaint.setStrokeWidth(3);
            pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));
        }*/
//        canvas.drawColor(Color.RED);

        RectF rect = new RectF(10, 10, 290, 290);
        int saveLayer = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        Xfermode xfermode=new Xfermode();
        Xfermode xfermode2 = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);


        canvas.drawBitmap(getA(),0,0,mPaint);
        mPaint.setXfermode(xfermode2);
        canvas.drawBitmap(getB(),10,10,mPaint);
        mPaint.setXfermode(null);
//pointPaint
        canvas.restoreToCount(saveLayer);
        Path a=new Path();
//        a.addRect();
    }
    private  Bitmap drawRectBm(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawRect(new RectF(0,0,70,70),paint);
        return bm;
    }
    private  Bitmap drawCircleBm(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.home_text_color));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawCircle(70,70,35,paint);
        return bm;
    }
    public Bitmap getA(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(180,180, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawRect(new RectF(10, 10, 190, 190),paint);
        return bm;
    }
    public Bitmap getB(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.home_text_color));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(380,380, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawCircle(170,170,90,paint);
        return bm;
    }

    private void b(Canvas canvas) {

        canvas.drawPath(path,pointPaint);
        canvas.drawPath(leftPath,pointPaint);
        canvas.drawPath(topPath,pointPaint);
        canvas.drawPath(rightPath,pointPaint);
        canvas.drawPath(bottomPath,pointPaint);
        switch (touchFlag){
            case 0:
                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(path,pointPaint);
            break;
            case 1:
                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(leftPath,pointPaint);

            break;
            case 2:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(topPath,pointPaint);
            break;
            case 3:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(rightPath,pointPaint);
            break;
            case 4:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(bottomPath,pointPaint);
            break;
        }

        pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));

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
                invalidate();
            break;
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
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

    private void a(Canvas canvas) {

        Path path=new Path();
        RectF rectF=new RectF(-250,-250,250,250);
        RectF rectF2=new RectF(-200,-200,200,200);


        path.addArc(rectF,-45,90);

        path.arcTo(rectF2,45,-90);
        path.close();

        canvas.drawPath(path,mPaint);





    }
}
