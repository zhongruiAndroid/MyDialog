package com.test.mydialog.view.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/6/27.
 */

public class DaoYing {
    private void getNewBitmap(ImageView iv,Context context) {
        Matrix matrix=new Matrix();
        matrix.preScale(1,-1);
        Bitmap oldBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap, 0, oldBitmap.getHeight() / 2, oldBitmap.getWidth(), oldBitmap.getHeight()/2, matrix, false);
//        iv.setImageBitmap(newBitmap);

        int gapWidth=5;
        Bitmap bitmap = Bitmap.createBitmap(oldBitmap.getWidth(), gapWidth + oldBitmap.getHeight() + oldBitmap.getHeight() / 2, Bitmap.Config.ARGB_8888);


        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(oldBitmap,0,0,new Paint());
//        int count = canvas.saveLayer(0,0,0,0, null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,new Paint());
        int color1= ContextCompat.getColor(context, R.color.transparent_half);
        int color2= ContextCompat.getColor(context, R.color.black);
        LinearGradient linearGradient=new LinearGradient(0,oldBitmap.getHeight()+gapWidth,0,bitmap.getHeight(),0x53000000,0xED000000, Shader.TileMode.MIRROR);

        Paint paint = new Paint();
        paint.setShader(linearGradient);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,paint);
        canvas.drawRect(0,oldBitmap.getHeight()+gapWidth,bitmap.getWidth(),bitmap.getHeight(),paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(count);
        iv.setImageBitmap(bitmap);
    }

}
