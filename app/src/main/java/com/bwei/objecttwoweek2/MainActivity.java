package com.bwei.objecttwoweek2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.bwei.objecttwoweek2.view.Circle;

public class MainActivity extends AppCompatActivity {

    // private ImageView image;
    float lastDistance = -1;
    float x0 = 0;
    float y0 = 0;
    private int screenWidth;
    private int screenHeight;

    private Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // image = (ImageView) findViewById(R.id.imgage);

        circle = (Circle) findViewById(R.id.circle);
        // 获得屏幕宽高 不让出界
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 200;

        // 这一个图片的触摸事件
        // image.setOnTouchListener(new OnTouchListener() {
        circle.setOnTouchListener(new View.OnTouchListener() {

            private float x;
            private float y;

            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                // TODO Auto-generated method stub
                int action = event.getAction();
                Log.i("TAG", "触摸开始......");
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // getX()是表示Widget相对于自身左上角的x坐标,而getRawX()是表示相对于屏幕左上角的x坐标值(注意:这个屏幕左上角是手机屏幕左上角,不管activity是否有titleBar或是否全屏幕)
                        x = event.getRawX();
                        y = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // 得到触摸点的个数
                        int count = event.getPointerCount();
                        Log.i("TAG", "触摸点的个数：" + count);

                        if (count > 1) {

                            Log.i("TAG", event.getX(0) + "," + event.getY(0) + "|"
                                    + event.getX(1) + "," + event.getY(1));
                            // 获得两点的坐标差
                            float distanceX = event.getX(0) - event.getX(1);
                            float distanceY = event.getY(0) - event.getY(1);
                            // 获得两点之间的距离
                            float betweenDistance = (float) Math.sqrt(distanceX
                                    * distanceX + distanceY * distanceY);

                            Log.i("TAG", "两点之间的上一次距离：" + lastDistance);
                            Log.i("TAG", "当前两点之间的新距离：" + betweenDistance);

                            if (betweenDistance < 1) {// 表示没动
                                lastDistance = betweenDistance;

                            } else if ((betweenDistance - lastDistance) > 5) {
                                // 放大
                                lastDistance = betweenDistance;
                                // 获得布局参数
                                RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) circle
                                        .getLayoutParams();
                                params.width = (int) (circle.getWidth() * 1.1f);
                                params.height = (int) (circle.getHeight() * 1.1f);
                                circle.setLayoutParams(params);
                            } else if ((lastDistance - betweenDistance) > 5) {
                                // 缩小
                                lastDistance = betweenDistance;
                                // 获得布局参数
                                RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) circle
                                        .getLayoutParams();
                                params.width = (int) (circle.getWidth() * 0.9f);
                                params.height = (int) (circle.getHeight() * 0.9f);
                                circle.setLayoutParams(params);

                            }

                        } else if (count == 1) {
                            // 移动距离
                            float rawX = event.getRawX() - x;
                            float rawy = event.getRawY() - y;
                            // 定义新的
                            int left = (int) (circle.getLeft() + rawX);
                            int top = (int) (circle.getTop() + rawy);
                            int right = (int) (circle.getRight() + rawX);
                            int bottom = (int) (circle.getBottom() + rawy);
                            // 设置不能出界
                            if (left < 0) {
                                left = 0;
                                right = left + v.getWidth();

                            }

                            if (right > screenWidth) {
                                right = screenWidth;
                                left = right - v.getWidth();
                            }

                            if (top < 0) {
                                top = 0;
                                bottom = top + v.getHeight();
                                Log.i("TAG", "bottom==" + bottom);
                                return true;
                            }

                            if (bottom > screenHeight) {
                                bottom = screenHeight;
                                top = bottom - v.getHeight();
                            }

                            // 赋值
                            circle.layout(left, top, right, bottom);
                            // 改成新的按下作标
                            x = event.getRawX();
                            y = event.getRawY();
						/* return false; */
                        }
                        break;

                }

                return true;
            }
        });
    }

    // 全局移动
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
		/*
		 * image.setX(event.getX()); image.setY(event.getY());
		 */
        return true;
    }

}
