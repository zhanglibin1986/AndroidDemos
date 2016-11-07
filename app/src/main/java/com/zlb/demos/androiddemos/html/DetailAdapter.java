package com.zlb.demos.androiddemos.html;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.fresco.FrescoManager;
import com.zlb.demos.androiddemos.fresco.ImageResponseListener;
import com.zlb.demos.androiddemos.gank.ViewPagerActivity;
import com.zlb.demos.androiddemos.gank.bean.CommenImage;
import com.zlb.demos.androiddemos.html.mzitu.HtmlDetailFragment;
import com.zlb.demos.androiddemos.utils.BitmapUtil;
import com.zlb.demos.androiddemos.utils.DisplayUtils;
import com.zlb.demos.androiddemos.utils.DownloadUtil;
import com.zlb.demos.androiddemos.utils.Logger;
import com.zlb.demos.androiddemos.utils.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/26 下午1:13
 */

public class DetailAdapter extends RecyclerView.Adapter {
    private ArrayList<CommenImage> datas = new ArrayList<>();
    private Activity activity;

    public DetailAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<CommenImage> data) {
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(CommenImage data) {
        log("add data : " + data);
        datas.add(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<CommenImage> data) {
        log("add datas : " + data);
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.mz_detail_item, parent, false);
        return new DetailAdapter.HolderDetail(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailAdapter.HolderDetail viewHolder = (DetailAdapter.HolderDetail) holder;
        //FrescoManager.loadUrl(datas.get(position)).into(viewHolder.image);

        //viewHolder.image.setImageURI(Uri.parse(datas.get(position)));
        CommenImage item = datas.get(position);
        Uri uri = Uri.parse(item.getUrl());

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id,
                            @javax.annotation.Nullable ImageInfo imageInfo,
                            @javax.annotation.Nullable Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        int w = imageInfo.getWidth();
                        int h = imageInfo.getHeight();
                        //Logger.d("image", "w = " + w + " , h = " + h);
                        viewHolder.image.getLayoutParams().width = DisplayUtils.getScreenWidth(activity);
                        viewHolder.image.getLayoutParams().height = (int)(h * (1.0 * DisplayUtils.getScreenWidth(activity)) / w);
                    }
                })
                .setUri(uri)
                // other setters
                .build();
        File file = FrescoManager.getCachedImageOnDisk(item.getUrl());
        Log.d("file", "file = " + item + " , postion = " + position);

        //ImagePipeline imagePipeline = Fresco.getImagePipeline();

        viewHolder.image.setController(controller);
        viewHolder.textView.setText(datas.get(position).getId());

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.startActivity(activity, toListString(datas), position);
            }
        });
        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhoto(datas.get(position).getUrl());
            }
        });
    }

    private ArrayList<String> toListString(List<CommenImage> images) {
        ArrayList<String> list = new ArrayList<>();
        for (CommenImage image : images) {
            list.add(image.getUrl());
        }
        return list;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HolderDetail extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        Button download;
        TextView textView;

        public HolderDetail(View itemView) {
            super(itemView);
            image = ((SimpleDraweeView) itemView.findViewById(R.id.mz_detail_image));
            download = (Button) itemView.findViewById(R.id.mz_detail_down);
            textView = (TextView) itemView.findViewById(R.id.desc);
        }
    }

    /**
     * 保存图片到面包旅行相册
     * 最好采用这种方式下载图片 http://www.jianshu.com/p/565e5b0ca823
     */
    public void savePhoto(final String url) {
        FrescoManager.loadUrl(url).into(activity, new ImageResponseListener() {
            @Override
            public void onSuccess(final Bitmap bitmap) {
                final Bitmap newBitmap = BitmapUtil.createNewShareBitmap(bitmap);
                Log.e("demo", "onSuccess begin download");
                new Handler().post(new Runnable() {
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
                                DownloadUtil.updateMedia(activity, path);
                                Util.showToast(activity,
                                        activity.getString(R.string.tv_save_photo_path, path));
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
            //Logger.d("image",
            //        "width = " + imageInfo.getWidth() + " , height = " + imageInfo.getHeight());
        }

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
        }
    };

    private void log(String log) {
        Logger.d("mmjpg", log);
    }
}

