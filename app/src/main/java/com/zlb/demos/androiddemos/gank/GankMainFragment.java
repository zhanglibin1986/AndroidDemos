package com.zlb.demos.androiddemos.gank;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.commens.list.BaseCommenListAdapter;
import com.zlb.demos.androiddemos.commens.list.BaseCommenListFragment;
import com.zlb.demos.androiddemos.commens.list.CommenListRequest;
import com.zlb.demos.androiddemos.commens.list.CommentListResponse;
import com.zlb.demos.androiddemos.commens.list.IResponseParser;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.gank.bean.GankImage;
import com.zlb.demos.androiddemos.gank.bean.ResultsList;
import com.zlb.demos.androiddemos.utils.DisplayUtils;

import java.util.List;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.gank.bean
 * @date 16/8/31上午10:06
 * @Description
 */
public class GankMainFragment extends BaseCommenListFragment {
    public static String PAGE_SIZE = "20";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestData(CommenListRequest.Builder.newInstanse("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + PAGE_SIZE + "/", new GankParser())
                .resultObject(ResultsList.class)
                .addPath("1")
                .build());
    }

    @Override
    protected BaseCommenListAdapter initAdapter() {
        return new GankMainAdapter();
    }

    @Override
    protected int initSpanCount() {
        return 2;
    }

    class GankMainAdapter extends BaseCommenListAdapter<GankImage> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GankViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.recycler_list_gank_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GankViewHolder gankViewHolder = (GankViewHolder) holder;
            gankViewHolder.simpleDraweeView.getLayoutParams().width = DisplayUtils.getScreenWidth(getActivity()) / 2 - DisplayUtils.dipToPx(getActivity(), 8);
            FrescoManager.loadUrl(datas.get(position).getUrl()).into(gankViewHolder.simpleDraweeView);
        }
    }

    class GankViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        public GankViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.gank_img);
        }
    }

    class GankParser implements IResponseParser<ResultsList> {
        @Override
        public CommentListResponse parse(ResultsList result) {
            CommentListResponse response = new CommentListResponse();
            response.setData(result.getResults());
            response.setHasMore(true);
            List<String> paths = mRequest.getPaths();
            String page = paths.get(0);
            int p = Integer.valueOf(page);
            mRequest.getPaths().clear();
            mRequest.addPath("" + (p + 1));
            return response;
        }
    }
}
