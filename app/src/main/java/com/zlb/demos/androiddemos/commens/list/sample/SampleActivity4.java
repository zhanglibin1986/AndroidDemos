package com.zlb.demos.androiddemos.commens.list.sample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.commens.list.BaseCommenListAdapter;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SampleActivity4 extends BaseActivity {
    @BindView(R.id.sample4_list) protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setEnterTransition( new Explode() );
//        getWindow().setExitTransition( new Explode() );
//        getWindow().setEnterTransition( new Slide() );
//        getWindow().setExitTransition( new Slide() );
//        getWindow().setEnterTransition( new Fade() );
//        getWindow().setExitTransition( new Fade() );
        setContentView(R.layout.activity_sample4);
        //共享元素是SimpleDraweeView的话，目标Activity必须调用下面的方法
        getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.FIT_CENTER));
        getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_CENTER, ScalingUtils.ScaleType.CENTER_CROP));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        ArrayList<Object> data = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            data.add(i);
        }
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    class MyAdapter extends BaseCommenListAdapter<Object> {

        public static final int TYPE_BANNER = 0;
        public static final int TYPE_DATA = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder = null;
            if(viewType == TYPE_BANNER) {
                holder = new BannerHolder(LayoutInflater.from(SampleActivity4.this).inflate(R.layout.sample4_list_header, parent, false));
            } else {
                holder = new TextHolder(LayoutInflater.from(SampleActivity4.this).inflate(R.layout.sample4_list_item_data, parent, false));
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == TYPE_BANNER) {
                BannerHolder bannerHolder = (BannerHolder) holder;
                bannerHolder.image.setTransitionName("test");
                FrescoManager.loadUrl(getIntent().getStringExtra("url")).into(bannerHolder.image);
            } else {
                TextHolder textHolder = (TextHolder) holder;
                textHolder.textView.setText("Test data position = " + position);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? TYPE_BANNER : TYPE_DATA;
        }
    }



    class BannerHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView image;
        public BannerHolder(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.sample4_list_header);
        }
    }

    class TextHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.sample4_list_item_text);
        }
    }
}
