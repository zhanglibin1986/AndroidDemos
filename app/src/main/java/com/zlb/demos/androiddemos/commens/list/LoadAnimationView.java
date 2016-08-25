package com.zlb.demos.androiddemos.commens.list;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zlb.demos.androiddemos.R;

public class LoadAnimationView extends RelativeLayout {
	
//	private ImageView mLoadAnimationBig;
//	private ImageView mLoadAnimationSmall;
//	private Animation mAnimationBig;
//	private Animation mAnimationSmall;

	private ImageView ivLoadingBreadman;
	private AnimationDrawable frameAnim;

	public LoadAnimationView(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.load_animation_view, this, true);
//		mLoadAnimationBig = (ImageView) findViewById(R.id.ivLoadingBig);
//		mLoadAnimationSmall = (ImageView) findViewById(R.id.ivLoadingSmall);
//		mAnimationBig = AnimationUtils.loadAnimation(context, R.anim.loading_animator_big);
//		mAnimationSmall = AnimationUtils.loadAnimation(context, R.anim.loading_animator_small);
		ivLoadingBreadman = (ImageView) findViewById(R.id.ivLoadingBreadman);
		frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.loading_anim);
		frameAnim.setOneShot(false);
		ivLoadingBreadman.setBackgroundDrawable(frameAnim);
		startAnimation();
	}
	
	public LoadAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.load_animation_view, this, true);
//		mLoadAnimationBig = (ImageView) findViewById(R.id.ivLoadingBig);
//		mLoadAnimationSmall = (ImageView) findViewById(R.id.ivLoadingSmall);
//		mAnimationBig = AnimationUtils.loadAnimation(context, R.anim.loading_animator_big);
//		mAnimationSmall = AnimationUtils.loadAnimation(context, R.anim.loading_animator_small);
		ivLoadingBreadman = (ImageView) findViewById(R.id.ivLoadingBreadman);
		frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.loading_anim);
		frameAnim.setOneShot(false);
		ivLoadingBreadman.setBackgroundDrawable(frameAnim);
		startAnimation();
	}
	
	public void setWhite() {
//	    mLoadAnimationBig.setImageResource(R.drawable.loading_animation_big_white);
//	    mLoadAnimationSmall.setImageResource(R.drawable.loading_animation_small_white);
	}
	
	public void startAnimation(){
//		mLoadAnimationBig.startAnimation(mAnimationBig);
//		mLoadAnimationSmall.startAnimation(mAnimationSmall);
		if (frameAnim != null && !frameAnim.isRunning()) {
			frameAnim.start();
		}
	}
	
	public void stopAnimation(){
//		mLoadAnimationBig.clearAnimation();
//		mLoadAnimationSmall.clearAnimation();
		if (frameAnim != null && frameAnim.isRunning()) {
			frameAnim.stop();
		}
	}
	
}
