package com.zlb.demos.androiddemos.html.mmjpg;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseFragment;
import com.zlb.demos.androiddemos.commens.list.RecyclerViewMoreManager;
import com.zlb.demos.androiddemos.html.DetailAdapter;
import com.zlb.demos.androiddemos.utils.Logger;
import com.zlb.demos.androiddemos.utils.UrlUtil;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/26 下午12:41
 */

public class MmFragment extends BaseFragment {
    public static final String TEST_URL1 = "http://img.mmjpg.com/2016/702/2.jpg";
    public static final String TEST_URL2 = "http://img.mmjpg.com/2015/1/1.jpg";
    public static final String TEST_URL3 = "http://img.mmjpg.com/2015/489/29.jpg";

    int year = 2015;
    int second = 1;
    int third = 1;

    public static MmFragment newInstance() {
        return new MmFragment();
    }

    @BindView(R.id.list_html_detail)
    protected RecyclerView recyclerView;

    private DetailAdapter adapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_html_detail, container, false);
    }

    LinearLayoutManager layoutManager;

    @Override
    protected void initView() {
        //layoutManager = new LinearLayoutManager(getActivity());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);


        new RecyclerViewMoreManager(getActivity(), recyclerView, 1,
                new RecyclerViewMoreManager.MoreRecyclerCallback() {
                    @Override
                    public boolean isRequesting() {
                        return isRequesting;
                    }

                    @Override
                    public boolean hasMore() {
                        return true;
                    }

                    @Override
                    public void loadMore() {
                        log("loadMore");
                        addData();
                    }

                    @Override
                    public boolean isLastItemLoading() {
                        return true;
                    }
                });

        adapter = new DetailAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    boolean isRequesting;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addData();
    }

    private void addData() {
        isRequesting = true;
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(createData(20));
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        isRequesting = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        isRequesting = false;
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        isRequesting = false;
                        log("add datas " + strings.size());
                        adapter.addDatas(strings);
                    }
                });
    }

    int secondFailTime = 0;
    int thirdFailTime = 0;

    private String getUrl() {
        String temp = "http://img.mmjpg.com/" + year + "/" + second + "/" + third + ".jpg";

        while (!urlUtil.isAvailable(temp)) {
            log("------- no available " + temp);
            thirdFailTime++;
            if (thirdFailTime == 100) {
                secondFailTime++;
                second++;
                thirdFailTime = 0;
                third = 1;
                if (secondFailTime == 500) {
                    year++;
                    second = 1;
                    secondFailTime = 0;
                }
            }
            third++;
            temp = "http://img.mmjpg.com/" + year + "/" + second + "/" + third + ".jpg";
        }
        String url = "http://img.mmjpg.com/" + year + "/" + second + "/" + third + ".jpg";
        third++;
        log(url);
        return url;
    }

    private UrlUtil urlUtil = new UrlUtil();

    private List<String> createData(int count) {//从2015/1/1开始
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getUrl());
        }
        return list;
    }

    private void log(String log) {
        Logger.d("mm", log);
    }
}
