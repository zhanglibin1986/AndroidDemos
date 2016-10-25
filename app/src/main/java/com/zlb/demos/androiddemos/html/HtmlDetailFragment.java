package com.zlb.demos.androiddemos.html;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseFragment;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.fresco.ImageResponseListener;
import com.zlb.demos.androiddemos.gank.ViewPagerActivity;
import com.zlb.demos.androiddemos.net.OkHttpManager;
import com.zlb.demos.androiddemos.utils.BitmapUtil;
import com.zlb.demos.androiddemos.utils.DisplayUtils;
import com.zlb.demos.androiddemos.utils.DownloadUtil;
import com.zlb.demos.androiddemos.utils.Logger;
import com.zlb.demos.androiddemos.utils.PatternUtil;
import com.zlb.demos.androiddemos.utils.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/25 下午1:14
 */

public class HtmlDetailFragment extends BaseFragment {
    public static HtmlDetailFragment newInstance(String url) {
        Bundle b = new Bundle();
        b.putString("url", url);
        HtmlDetailFragment fragment = new HtmlDetailFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @BindView(R.id.list_html_detail)
    protected RecyclerView recyclerView;

    private DetailAdapter adapter;
    private int total = 100;
    private int index = 2;
    private String url;

    private Handler handler;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_html_detail, container, false);
    }

    private String getIndexUrl(int num) {
        return url + "/" + num;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DetailAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.url = getArguments().getString("url");
        loadUrl(url);
        handler = new Handler();
    }

    private void loadUrl(String url) {
        OkHttpManager.getInstance()
                .request(new Request.Builder().url(url).build())
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response, String>() {
                    @Override
                    public String call(Response response) {

                        try {
                            String s = response.body().string();
                            return s;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (total == 0) {
                            String t = PatternUtil.getPatternLists(s,
                                    PatternUtil.PATTERN_MZ_HOME_DETAIL_TOTAL_PAGE).get(0);
                            Log.d("rx", " total page = " + t);
                            String page =
                                    s.substring("<span>".length(), s.length() - "</span>".length());
                            Log.d("rx", " total page --- = " + page);
                            //total = Integer.valueOf(page);
                        }
                        Log.d("rx", "doOnNext ---- total = " + total);
                    }
                })
                //.doOnNext(new Action1<String>() {
                //    @Override
                //    public void call(String s) {
                //        Log.d("rx", "doOnNext ---- total = " + total);
                //        if (total == 0) {
                //            total = Integer.valueOf(PatternUtil.getPatternLists(s,
                //                    PatternUtil.PATTERN_MZ_HOME_DETAIL_TOTAL_PAGE).get(0));
                //        }
                //    }
                //})
                //.doOnCompleted(new Action0() {
                //    @Override
                //    public void call() {
                //        Log.d("rx", "doOnCompleted total = " + total + " , index = " + index);
                //        if (index < total) {
                //            loadUrl(getIndexUrl(index++));
                //        }
                //    }
                //})
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.from(PatternUtil.getPatternLists(s,
                                PatternUtil.PATTERN_MZ_HOME_DETAIL_URL));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("rx", "onCompleted");
                        Log.d("rx", "doOnCompleted total = " + total + " , index = " + index);
                        if (index < total) {
                            loadUrl(getIndexUrl(index++));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rx", "onError");
                    }

                    @Override
                    public void onNext(String s) {
                        String image = s.substring("img src=\"".length(), s.length() - 1);
                        Log.d("rx", "onNext image = " + image);
                        adapter.addData(image);
                    }
                });
    }

    class DetailAdapter extends RecyclerView.Adapter {
        private ArrayList<String> datas = new ArrayList<>();

        public void setData(List<String> data) {
            datas.clear();
            datas.addAll(data);
            notifyDataSetChanged();
        }

        public void addData(String data) {
            datas.add(data);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.mz_detail_item, parent, false);
            return new HtmlDetailFragment.HolderDetail(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HtmlDetailFragment.HolderDetail viewHolder = (HtmlDetailFragment.HolderDetail) holder;
            //FrescoManager.loadUrl(datas.get(position)).into(viewHolder.image);

            //viewHolder.image.setImageURI(Uri.parse(datas.get(position)));

            Uri uri = Uri.parse(datas.get(position));

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setControllerListener(new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(String id,
                                @javax.annotation.Nullable ImageInfo imageInfo,
                                @javax.annotation.Nullable Animatable animatable) {
                            super.onFinalImageSet(id, imageInfo, animatable);
                            int w = imageInfo.getWidth();
                            int h = imageInfo.getHeight();
                            Logger.d("image", "w = " + w + " , h = " + h);
                            viewHolder.image.getLayoutParams().width = DisplayUtils.getScreenWidth(getActivity());
                            viewHolder.image.getLayoutParams().height = (int)(h * (1.0 * DisplayUtils.getScreenWidth(getActivity())) / w);
                        }
                    })
                    .setUri(uri)
                    // other setters
                    .build();

            //ImagePipeline imagePipeline = Fresco.getImagePipeline();

            viewHolder.image.setController(controller);

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPagerActivity.startActivity(getActivity(), datas, position);
                }
            });
            viewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savePhoto(datas.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class HolderDetail extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        Button download;

        public HolderDetail(View itemView) {
            super(itemView);
            image = ((SimpleDraweeView) itemView.findViewById(R.id.mz_detail_image));
            download = (Button) itemView.findViewById(R.id.mz_detail_down);
        }
    }

    /**
     * 保存图片到面包旅行相册
     */
    public void savePhoto(final String url) {
        FrescoManager.loadUrl(url).into(getActivity(), new ImageResponseListener() {
            @Override
            public void onSuccess(final Bitmap bitmap) {
                final Bitmap newBitmap = BitmapUtil.createNewShareBitmap(bitmap);
                Log.e("demo", "onSuccess begin download");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        File file = DownloadUtil.getDownloadImagePath();
                        File myCaptureFile = new File(
                                file.getPath() + File.separator + Util.getMD5String(url) + ".jpg");
                        if (myCaptureFile.exists()) {
                            myCaptureFile.delete();
                            Logger.e("file is exists");
                        }
                        try {
                            if (myCaptureFile.createNewFile()) {
                                FileOutputStream fos = new FileOutputStream(myCaptureFile);
                                if (newBitmap != null && !newBitmap.isRecycled()) {
                                    newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                }
                                fos.flush();
                                fos.close();
                            }
                            String path = myCaptureFile.getAbsolutePath();
                            if (!TextUtils.isEmpty(path)) {
                                DownloadUtil.updateMedia(getActivity(), path);
                                Util.showToast(getActivity(),
                                        getString(R.string.tv_save_photo_path, path));
                                Log.e("demo", "download success");
                            }
                        } catch (IOException e) {
                            Log.e("demo", "download failed");
                        }
                    }
                });
            }

            @Override
            public void onFailure() {

            }
        });
    }

    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo,
                @Nullable Animatable anim) {
            if (imageInfo == null) {
                return;
            }
            QualityInfo qualityInfo = imageInfo.getQualityInfo();
            FLog.d("Final image received! " + "Size %d x %d",
                    "Quality level %d, good enough: %s, full quality: %s", imageInfo.getWidth(),
                    imageInfo.getHeight(), qualityInfo.getQuality(),
                    qualityInfo.isOfGoodEnoughQuality(), qualityInfo.isOfFullQuality());
            Logger.d("image",
                    "width = " + imageInfo.getWidth() + " , height = " + imageInfo.getHeight());
        }

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
        }
    };
}
