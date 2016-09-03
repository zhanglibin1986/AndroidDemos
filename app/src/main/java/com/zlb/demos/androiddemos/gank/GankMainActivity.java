package com.zlb.demos.androiddemos.gank;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.gank.bean.GankImage;
import com.zlb.demos.androiddemos.gank.bean.ResultsList;
import com.zlb.demos.androiddemos.net.retrofit.RetrofitService;
import com.zlb.demos.androiddemos.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GankMainActivity extends BaseActivity {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition( new Explode() );
        getWindow().setExitTransition( new Explode() );
        setContentView(R.layout.activity_gank);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
