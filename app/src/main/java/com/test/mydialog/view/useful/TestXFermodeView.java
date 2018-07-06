package com.test.mydialog.view.useful;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/7/6.
 */

public class TestXFermodeView extends View {
    public TestXFermodeView(Context context) {
        super(context);
    }

    public TestXFermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestXFermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    Paint srcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint dstPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        srcPaint.setColor(Color.BLUE);
        dstPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);

        Path srcPath=new Path();
        RectF rectF=new RectF(getWidth()/2-100,getHeight()/2-100,getWidth(),getHeight());
        srcPath.addRect(rectF, Path.Direction.CW);
        srcPath.moveTo(0,0);
        srcPath.moveTo(getWidth(),getHeight());
        canvas.drawPath(srcPath,srcPaint);

        Path dstPath=new Path();
        dstPath.addCircle(getWidth()/2-100,getHeight()/2-100,getWidth()/4, Path.Direction.CW);
        dstPath.moveTo(0,0);
        dstPath.moveTo(getWidth(),getHeight());
        dstPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawPath(dstPath,dstPaint);
        canvas.restore();
    }
}
