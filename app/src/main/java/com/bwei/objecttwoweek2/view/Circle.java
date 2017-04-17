package com.bwei.objecttwoweek2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Circle extends View {

	private Paint pa = new Paint();

	public Circle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public Circle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Circle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {

		float wi = canvas.getWidth();
		float he = canvas.getHeight();
		pa.setColor(Color.GREEN);

			canvas.drawCircle(wi / 2, he / 2, wi / 2, pa);
		
		super.onDraw(canvas);
	}

	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { wi =
	 * event.getX(); he = event.getY(); this.invalidate();
	 * 
	 * return true; }
	 */
}
