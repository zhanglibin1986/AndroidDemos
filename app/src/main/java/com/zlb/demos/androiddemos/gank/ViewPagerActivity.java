package com.zlb.demos.androiddemos.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator;
import me.relex.photodraweeview.PhotoDraweeView;

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.current_page) protected TextView currentPage;
    @BindView(R.id.total_page) protected TextView totalPage;

    public static final String TAG = "pager";

    public static void startActivity(Context context, ArrayList<String> imageUrls, int position) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putStringArrayListExtra("urls", imageUrls);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    DraweePagerAdapter mAdapter = new DraweePagerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        MultiTouchViewPager viewPager = (MultiTouchViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(mAdapter);
        indicator.setViewPager(viewPager);
        mAdapter.setData(getIntent().getStringArrayListExtra("urls"));
        totalPage.setText("" + mAdapter.getCount());
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
        currentPage.setText("" + ((getIntent().getIntExtra("position", 0) + 1)));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage.setText("" + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class DraweePagerAdapter extends PagerAdapter {
        ArrayList<String> mDrawables = new ArrayList<>();
        public void setData(ArrayList<String> data) {
            this.mDrawables.clear();
            this.mDrawables.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mDrawables.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.d(TAG, "isViewFromObject: " + (view == object));
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
//            final PhotoDraweeView photoDraweeView = new PhotoDraweeView(viewGroup.getContext());
            PhotoDraweeView photoDraweeView = (PhotoDraweeView)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_photo_pager_item, viewGroup, false);
            Log.d(TAG, "instantiateItem: instantiate item position = " + position);
            photoDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);

//            FrescoManager.loadUrl(mDrawables.get(position)).into(photoDraweeView);

            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(Uri.parse(mDrawables.get(position)));
            controller.setOldController(photoDraweeView.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        return;
                    }
                    Log.d(TAG, "onFinalImageSet: image width = " + imageInfo.getWidth() + " , height = " + imageInfo.getHeight());
                    photoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            photoDraweeView.setController(controller.build());

            try {
                viewGroup.addView(photoDraweeView, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return photoDraweeView;
        }
    }
}
