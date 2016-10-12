package com.zlb.demos.androiddemos.commens.list.sample;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.commens.list.BaseCommenListAdapter;
import com.zlb.demos.androiddemos.commens.list.BaseCommenListFragment;
import com.zlb.demos.androiddemos.commens.list.CommenListRequest;
import com.zlb.demos.androiddemos.commens.list.CommentListResponse;
import com.zlb.demos.androiddemos.commens.list.IResponseParser;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list.sample
 * @date 16/8/24下午2:50
 * @Description
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SampleFragment extends BaseCommenListFragment {
    @Override
    protected BaseCommenListAdapter initAdapter() {
        return new SampleAdapter(R.layout.last_item_product_list);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestData((new CommenListRequest.Builder("http://api.breadtrip.com/v2/index/", new ResponseParser()))
                .resultObject(TripElements.class)
                .nextStartKey("next_start")
                .nextStartValue("0")
                .build());
    }

    @Override
    protected int initSpanCount() {
        return 2;
    }

    class SampleAdapter extends BaseCommenListAdapter<TripElements.TripData> {

        public SampleAdapter(int customLastLayoutId) {
            super(customLastLayoutId);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HolderHotTrip(LayoutInflater.from(getActivity()).inflate(R.layout.trip_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HolderHotTrip viewHolder = (HolderHotTrip) holder;
            Log.e("error", "position = " + position);
            TripElements.TripData tripData = datas.get(position);
            TripElements.TripItem item = tripData.getData().get(0);
            viewHolder.title.setText(item.getName());
            if(!TextUtils.isEmpty(item.getCover_image_default())) {
                FrescoManager.loadUrl(item.getCover_image_default()).into(viewHolder.image);
            }
            viewHolder.image.setTransitionName("");
            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewHolder.image.setTransitionName("test");

                    int totalType = 4;
                    Intent intent = null;
                    if((position + totalType) % totalType == 0) {
                        intent = new Intent(getActivity(), SampleActivity2.class);
                    } else if((position + totalType) % totalType == 1) {
                        intent = new Intent(getActivity(), SampleActivity3.class);
                    } else if((position + totalType) % totalType == 2) {
                        intent = new Intent(getActivity(), SampleActivity4.class);
                    } else if((position + totalType) % totalType == 3) {
                        intent = new Intent(getActivity(), SampleActivity5.class);
                    }
                    intent.putExtra("url", item.getCover_image_default());
                    //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                    Util.launch(getActivity(), intent, view, "test");

                }
            });

        }
    }

    class HolderHotTrip extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView title;

        public HolderHotTrip(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.trip_img);
            title = (TextView) itemView.findViewById(R.id.trip_title);
        }
    }

    class ResponseParser implements IResponseParser<TripElements> {
        @Override
        public CommentListResponse parse(TripElements featuredAll) {
            CommentListResponse<TripElements.TripData> response = new CommentListResponse<>();
            List<TripElements.TripData> temp = featuredAll.getElements();
            List<TripElements.TripData> datas = new ArrayList<>();
            for(int i = 0; i < temp.size(); i++) {
                TripElements.TripData tripData = temp.get(i);
                if(tripData.getType().equals("4")) {
                    datas.add(tripData);
                }
            }

            response.setData(datas);
            String next_start = featuredAll.getNext_start();
            response.setHasMore(!"-1".equals(next_start));
            response.setNextStart(next_start);
            return response;
        }
    }

}
