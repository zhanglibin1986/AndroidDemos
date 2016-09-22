package com.zlb.demos.androiddemos.apidemos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 贝塞尔曲线：http://www.cnblogs.com/jay-dong/archive/2012/09/26/2704188.html
 * path http://www.cnblogs.com/tt_mc/archive/2012/12/07/2807518.html
 * @author zhanglibin
 * @since 16/9/6 下午2:15
 */
public class PathView extends View {
    private Paint mPaint;

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(28);
        canvas.drawText("y=100", 0, 100, mPaint);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 200);
        canvas.drawPath(path, mPaint);

        path.close();//把开始的点和最后的点连接在一起，构成一个封闭图形
            /*
             * 最重要的就是movtTo和close,如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式，
             * 如果不设置close,图形不封闭。
             *
             * 当然，你也可以不设置close，再添加一条线，效果一样。
             */

        path.moveTo(300, 100);
        path.lineTo(300, 200);
        canvas.drawPath(path, mPaint);

        //贝塞尔曲线：http://www.cnblogs.com/jay-dong/archive/2012/09/26/2704188.html
        path.moveTo(400, 200);
        path.quadTo(500, 0, 700, 200);
        canvas.drawPath(path, mPaint);

        mPaint.setStrokeWidth(4);
        canvas.drawPoint(500, 0, mPaint);
        mPaint.setStrokeWidth(2);
        canvas.drawText("贝塞尔二次曲线", 710, 200, mPaint);

        path.moveTo(0, 500);
        path.cubicTo(100, 300, 300, 300, 500, 500);
        canvas.drawPath(path, mPaint);

        drawPoint(canvas, 100, 300);
        drawPoint(canvas, 300, 300);
        canvas.drawText("贝塞尔三次曲线", 510, 500, mPaint);

        path.moveTo(0, 650);
        RectF rectF = new RectF(0, 550, 200, 750);
        path.arcTo(rectF, 180, 90);
        canvas.drawPath(path, mPaint);


    }

    private void drawPoint(Canvas canvas, float x, float y) {
        mPaint.setStrokeWidth(4);
        canvas.drawPoint(x, y, mPaint);
        mPaint.setStrokeWidth(2);
    }


}
