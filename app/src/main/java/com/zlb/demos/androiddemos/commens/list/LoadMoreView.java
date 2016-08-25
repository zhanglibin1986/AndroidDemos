package com.zlb.demos.androiddemos.commens.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zlb.demos.androiddemos.R;


/**
 * Created by yeqingqing on 16/4/26.
 *//**/
public class LoadMoreView extends LinearLayout {

    private View body;
    private View face;
    private View ll_face;
    private ImageView iv_face;
    private View expression;
    private ImageView iv_expression;
    private View left_hand;
    private View right_hand;
    private View line;

    public LoadMoreView(Context context) {
        this(context,null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.loadmore, null, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view,lp);

        body = view.findViewById(R.id.body);
        face = view.findViewById(R.id.face);
        ll_face = view.findViewById(R.id.ll_face);
        iv_face = (ImageView)view.findViewById(R.id.iv_face);
        expression = view.findViewById(R.id.expression);
        iv_expression = (ImageView)view.findViewById(R.id.iv_expression);
        left_hand = view.findViewById(R.id.left_hand);
        right_hand = view.findViewById(R.id.right_hand);
        line = view.findViewById(R.id.line);
    }

    private int mAttachedTimes = 0;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animationExpression = AnimationUtils.loadAnimation(getContext(),
                        R.anim.loadmore_expression_down);
//        animationExpression.setFillAfter(true);
                expression.startAnimation(animationExpression);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv_expression.setImageResource(R.drawable.loadmore_expression_openeyes);
                    }
                },250);

                Animation animationLeftHand = AnimationUtils.loadAnimation(getContext(),
                        R.anim.loadmore_lefthand_down);
//        animationLeftHand.setFillAfter(true);
                left_hand.startAnimation(animationLeftHand);

                Animation animationRightHand = AnimationUtils.loadAnimation(getContext(),
                        R.anim.loadmore_righthand_down);
//        animationRightHand.setFillAfter(true);
                animationRightHand.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        blinkRandom(mAttachedTimes);
                        left_hand.setVisibility(GONE);
                        right_hand.setVisibility(GONE);
                        expression.setVisibility(GONE);
                        line.setVisibility(GONE);
                        Animation animation1 = AnimationUtils.loadAnimation(getContext(),
                                R.anim.loadmore_before_rotate);
                        animation1.setFillAfter(true);
                        final Animation animation2 = AnimationUtils.loadAnimation(getContext(),
                                R.anim.loadmore_rotate);
                        ll_face.startAnimation(animation1);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ll_face.clearAnimation();
                                ll_face.startAnimation(animation2);
                            }
                        },310);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                right_hand.startAnimation(animationRightHand);
            }
        },200);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttachedTimes++;

        ll_face.clearAnimation();
        expression.clearAnimation();
        left_hand.clearAnimation();
        right_hand.clearAnimation();
        line.setVisibility(VISIBLE);
        left_hand.setVisibility(VISIBLE);
        right_hand.setVisibility(VISIBLE);
        expression.setVisibility(VISIBLE);
        face.setVisibility(GONE);
    }

    private void blinkRandom(final int attachedTimes){
        iv_face.setImageResource(R.drawable.loadmore_face);
        face.setVisibility(VISIBLE);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if(attachedTimes<mAttachedTimes){
                    return;
                }
                iv_face.setImageResource(R.drawable.loadmore_face_b);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(attachedTimes<mAttachedTimes){
                            return;
                        }
                        iv_face.setImageResource(R.drawable.loadmore_face_c);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(attachedTimes<mAttachedTimes){
                                    return;
                                }
                                iv_face.setImageResource(R.drawable.loadmore_face);
                                blinkRandom(attachedTimes);
                            }
                        },120);
                    }
                },120);
            }
        },2000);
    }

}



