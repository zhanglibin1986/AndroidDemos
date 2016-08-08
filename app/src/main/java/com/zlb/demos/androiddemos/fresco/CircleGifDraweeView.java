package com.zlb.demos.androiddemos.fresco;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.utils.DisplayUtils;

/**
 * 圆形gif图
 */
public class CircleGifDraweeView extends SimpleDraweeView {

    /**是否是gif*/
    private boolean isGif = false;
    /**裁切路径*/
    private Path mPath;
    /**加V认证裁切路径*/
    private Path mVPath;
    /**直径*/
    private int circle;

    /**当前图片uri*/
    private Uri mCurrentUri;

    /**圆形裁切上次直径*/
    private int lastCircle;

    /**是否是加V认证用户*/
    private boolean isTranslate;

    public CircleGifDraweeView(Context context) {
        super(context);
    }

    public CircleGifDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleGifDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**是否是gif*/
    public void isGif(boolean isGif) {
        this.isGif = isGif;
    }

    public Uri getCurrentUri() {
        return mCurrentUri;
    }

    /**图片*/
    public void setCurrentUri(Uri currentUri) {
        mCurrentUri = currentUri;
    }

    /**背景是否需要透明镂空*/
    public void setVUser(boolean isTranslate) {
        this.isTranslate = isTranslate;
        invalidate();
    }

    private void initCirclePath() {
        if (mPath == null) {
            mPath = new Path();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                //关掉系统硬件加速
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        }
        circle = Math.min(getWidth(), getHeight());
        if (circle != lastCircle) {
            lastCircle = circle;
            mPath.reset();
            mPath.addCircle(getWidth() / 2, getHeight() / 2, circle / 2, Path.Direction.CW);

            // 加V认证，裁切镂空区域
            if (mVPath == null) {
                mVPath = new Path();
            }
            mVPath.addCircle(getWidth() - DisplayUtils.dipToPx(getContext(), 7),DisplayUtils.dipToPx(getContext(), 7), DisplayUtils.dipToPx(getContext(), 14),Path.Direction.CW);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (isGif || isTranslate) {
            initCirclePath();

            canvas.save();
            try {
                if (isGif) {
                    canvas.clipPath(mPath);
                }
                if (isTranslate) {
                    canvas.clipPath(mVPath, Region.Op.DIFFERENCE);
                }
            } catch (UnsupportedOperationException e) {
                // 避免android系统bug
            }
            super.onDraw(canvas);
            canvas.restore();
        } else {
            super.onDraw(canvas);
        }
    }


}
