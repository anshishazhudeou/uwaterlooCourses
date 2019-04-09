package com.tongxunlu.myapplication11112;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RotateView2 extends View {
    Bitmap bitmap;
    Matrix matrix ;
    int width , height ;
    int degrees ;
    Paint mPaint ;
    private int startX ,startY ;
    public RotateView2(Context context) {
        super(context);
        Drawable drawable = this.getResources().getDrawable(
                R.mipmap.foot_left);
        BitmapDrawable drawable2 = (BitmapDrawable) drawable ;
        bitmap = drawable2.getBitmap() ;
        matrix = new Matrix() ;
        width = bitmap.getWidth() ;
        height = bitmap.getHeight() ;
        mPaint = new Paint() ;
        mPaint.setDither(true) ;
        mPaint.setAntiAlias(true) ;
    }

    public RotateView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Drawable drawable = this.getResources().getDrawable(
                R.mipmap.foot_left);
        BitmapDrawable drawable2 = (BitmapDrawable) drawable ;
        bitmap = drawable2.getBitmap() ;
        matrix = new Matrix() ;
        width = bitmap.getWidth() ;
        height = bitmap.getHeight() ;
        mPaint = new Paint() ;
        mPaint.setDither(true) ;
        mPaint.setAntiAlias(true) ;
    }

    public RotateView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Drawable drawable = this.getResources().getDrawable(
                R.mipmap.foot_left);
        BitmapDrawable drawable2 = (BitmapDrawable) drawable ;
        bitmap = drawable2.getBitmap() ;
        matrix = new Matrix() ;
        width = bitmap.getWidth() ;
        height = bitmap.getHeight() ;
        mPaint = new Paint() ;
        mPaint.setDither(true) ;
        mPaint.setAntiAlias(true) ;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.setTranslate(width / 2, height / 2) ;	//设置转轴位置
        matrix.preRotate(degrees) ;						//开始转
        matrix.preTranslate(- width / 2 , - height / 2 ) ;	//转轴还原
        canvas.drawBitmap(bitmap, matrix, mPaint) ;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = (int) event.getX() ;
            startY = (int) event.getY() ;
            Log.i("TAG", "startX: "+ startX+",startY: " + startY) ;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int moveX = (int) (event.getX() - startX) ;
            Log.i("TAG", "moveX: "+ moveX) ;
            if (moveX > 50) {
                degrees += 50 ;
                invalidate() ;
                Log.i("TAG", "Right") ;
            } else if (moveX < 0) {
                degrees -= 50 ;
                invalidate() ;
                Log.i("TAG", "Left") ;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.i("TAG", "up........") ;
        }
        return true ;	//告诉Android将move、up事件发送到此方法。

    }

}
