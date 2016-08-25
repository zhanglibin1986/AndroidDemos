package com.zlb.demos.androiddemos.gank;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Bind(R.id.gank_list)
    protected RecyclerView recyclerView;
    protected SimpleListAdapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        initRecyclerView();
        requestData();
    }
    @MainThread
    private void initRecyclerView() {
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SimpleListAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void requestData() {
        GankApi api = RetrofitService.createService(GankApi.class);
        Observable<ResultsList> call = api.requestGankList(10, 1);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GankMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResultsList resultsList) {
                        adapter.setData(parseData(resultsList));
                    }
                });
    }

    private List<String> parseData(ResultsList resultsList) {
        List<String> urls = new ArrayList<>();
        List<GankImage> gankList = resultsList.getResults();
        for(int i = 0; i < gankList.size(); i++) {
            urls.add(gankList.get(i).getUrl());
        }
        return urls;
    }

    class SimpleListAdapter extends RecyclerView.Adapter {
        private List<String> urls = new ArrayList<>();
        private int screenWidth;

        public SimpleListAdapter() {
            screenWidth = DisplayUtils.getScreenWidth(GankMainActivity.this);
        }

        public void setData(List<String> data) {
            this.urls.addAll(data);
            notifyDataSetChanged();
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GankViewHolder(LayoutInflater.from(GankMainActivity.this).inflate(R.layout.recycler_list_gank_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GankViewHolder gankViewHolder = (GankViewHolder) holder;
            gankViewHolder.simpleDraweeView.getLayoutParams().width = DisplayUtils.getScreenWidth(GankMainActivity.this) / 2 - DisplayUtils.dipToPx(GankMainActivity.this, 8);
            FrescoManager.loadUrl(urls.get(position)).into(gankViewHolder.simpleDraweeView);
        }

        @Override
        public int getItemCount() {
            return urls.size();
        }
    }

    class GankViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        public GankViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.gank_img);
        }
    }
}
