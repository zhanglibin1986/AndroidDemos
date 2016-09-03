package com.zlb.demos.androiddemos.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.UrlUtil;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import me.relex.photodraweeview.PhotoDraweeView;

public class ViewPagerActivity extends AppCompatActivity {


    public static void startActivity(Context context, ArrayList<String> imageUrls) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putStringArrayListExtra("urls", imageUrls);
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
        ArrayList<String> mDrawables = new ArrayList<>();
        mAdapter.setData(getIntent().getStringArrayListExtra("urls"));
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
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            final PhotoDraweeView photoDraweeView = new PhotoDraweeView(viewGroup.getContext());
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
