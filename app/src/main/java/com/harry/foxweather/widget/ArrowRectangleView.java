package com.harry.foxweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.harry.foxweather.R;

/**
 * Created by Harry.Pan on 2018/1/19.
 */

public class ArrowRectangleView extends LinearLayout {
    int arrowWidth = 60;
    int arrowHeight = 30;

    private Paint mPaint;

    public ArrowRectangleView(Context context) {
        this(context, null);
    }

    public ArrowRectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     *  绘制箭头
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.translate(0, 0);
        // 笔锋圆滑度
        // mPaint.setPathEffect(new CornerPathEffect(10));
        Path mPath = new Path();
        int midOfLayout = getWidth() / 2;
        mPath.moveTo(midOfLayout, getHeight() - arrowHeight);
        mPath.lineTo(midOfLayout - arrowWidth / 2, getHeight());
        mPath.lineTo(midOfLayout + arrowWidth / 2, getHeight());
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
