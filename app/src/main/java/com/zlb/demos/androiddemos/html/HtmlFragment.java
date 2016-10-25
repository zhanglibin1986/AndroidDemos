package com.zlb.demos.androiddemos.html;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseFragment;
import com.zlb.demos.androiddemos.commens.list.RecyclerViewMoreManager;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.html.database.HtmlDbManager;
import com.zlb.demos.androiddemos.net.OkHttpManager;
import com.zlb.demos.androiddemos.utils.PatternUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/24 下午9:59
 */

public class HtmlFragment extends BaseFragment {
    private static final String TEST_URL = "http://www.mzitu.com";
    private static final String TEST_URL1 = "http://www.baidu.com";

    @BindView(R.id.list_mzt) protected RecyclerView recyclerView;

    public static HtmlFragment newInstance() {
        return new HtmlFragment();
    }

    private MyAdapter adapter;
    private int index = 0;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_html, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(TEST_URL);
    }

    private void loadData(String url) {
        OkHttpManager.getInstance().request(new Request.Builder().url(url).build())
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response, String>() {
                    @Override
                    public String call(Response response) {
                        try {
                            return response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return "Error...";
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.from(PatternUtil.getPatternLists(s, PatternUtil.PATTERN_MZ_HOME_LIST_URL));
                    }
                })
                .buffer(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        if(index < 20) {
                            loadData(TEST_URL + "/page/" + index++);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        MzHomeItem item = new MzHomeItem();
                        String detailUrl = s.get(0).substring("<li><a href=\"".length(), s.get(0).length());//详情URL
                        item.setDetailUrl(detailUrl);
                        String title = s.get(1).substring("'lazy' alt='".length(), s.get(1).length() - 1);//标题
                        item.setTitle(title);
                        String picUrl = s.get(2).substring("data-original='".length(), s.get(2).length() - 1);//标题图片的URL
                        item.setPicUrl(picUrl);
                        Log.d("html", "---- " + item);
                        adapter.addData(item);
                    }
                });
    }


    @Override
    protected void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
    }

    class MyAdapter extends RecyclerView.Adapter {
        private List<MzHomeItem> datas = new ArrayList<>();

        public void setData(List<MzHomeItem> data) {
            datas.clear();
            datas.addAll(data);
            notifyDataSetChanged();
        }

        public void addData(MzHomeItem data) {
            datas.add(data);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.mz_list_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            final MzHomeItem mzHomeItem = datas.get(position);

            FrescoManager.loadUrl(mzHomeItem.getPicUrl()).into(viewHolder.image);
            viewHolder.title.setText(mzHomeItem.getTitle());

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HtmlDetailActivity.launch(getActivity(), mzHomeItem.getDetailUrl());
                }
            });

            viewHolder.star.setSelected(mzHomeItem.isStar());

            viewHolder.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.star.setSelected(!mzHomeItem.isStar());
                    if(mzHomeItem.isStar()) {
                        HtmlDbManager.getInstance(getActivity()).saveImage(mzHomeItem);
                    } else {
                        HtmlDbManager.getInstance(getActivity()).deleteItem(mzHomeItem.getDetailUrl());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView title;
        ImageButton star;
        public ItemViewHolder(View itemView) {
            super(itemView);
            image = ((SimpleDraweeView) itemView.findViewById(R.id.mz_home_item_image));
            title = ((TextView) itemView.findViewById(R.id.mz_home_item_title));
            star = (ImageButton) itemView.findViewById(R.id.mz_star);
        }
    }
}
