package com.zlb.demos.androiddemos.material;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/6 下午3:43
 */
public class PathInterpolatorView extends View {
    private Path path;
    private Paint mPaint;
    public PathInterpolatorView(Context context) {
        super(context);
    }

    public PathInterpolatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void setPath(Path path) {
        this.path = path;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(path != null) {
            canvas.drawPath(path, mPaint);
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
