package com.tongxunlu.myapplication11112;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView imageView;
    private RelativeLayout relativeLayout,rlyt_content;

    private int lastX, lastY;    //保存手指点下的点的坐标
    final static int IMAGE_SIZE = 280;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
       relativeLayout = (RelativeLayout) findViewById(R.id.layout);
        rlyt_content= (RelativeLayout) findViewById(R.id.rlyt_content);
       //初始设置一个layoutParams
        //设置屏幕触摸事件
        imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //将点下的点的坐标保存
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                //计算出需要移动的距离
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                //将移动距离加上，现在本身距离边框的位置
                int left = view.getLeft() + dx;
                int top = view.getTop() + dy;
                //获取到layoutParams然后改变属性，在设置回去
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.height = IMAGE_SIZE;
                layoutParams.width = IMAGE_SIZE;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                view.setLayoutParams(layoutParams);
                //记录最后一次移动的位置
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        //刷新界面
        relativeLayout.invalidate();
        return true;
    }
}
