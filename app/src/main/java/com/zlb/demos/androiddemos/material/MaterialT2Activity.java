package com.zlb.demos.androiddemos.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;

import butterknife.Bind;

public class MaterialT2Activity extends BaseActivity {
    public static String URL_IMG = "http://breadtrip-dev.qiniudn.com/hunter/product/cover/90559_o_1apd1aok0kkt1q1t1eovri0102f9.jpg?imageView2/5/w/788/h/426/format/jpg/interlace/1/";
    @Bind(R.id.m_trans_image2_left) protected SimpleDraweeView imageLeft;
    @Bind(R.id.m_trans_image2_right) protected ImageView imageRight;

    public static void launch(Activity activity, View view, String transitionName) {
        Intent intent = new Intent(activity, MaterialT2Activity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, transitionName);//一个共享元素
            ActivityCompat.startActivity(activity, intent, compat.toBundle());
        }
    }

    public static void launch(Activity activity, View view1, String transitionName1, View view2, String transitionName2, View view3, String transitionName3) {
        Intent intent = new Intent(activity, MaterialT2Activity.class);

        Pair<View, String> pair1 = Pair.create(view1, transitionName1);
        Pair<View, String> pair2 = Pair.create(view2, transitionName2);
        Pair<View, String> pair3 = Pair.create(view3, transitionName3);

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1, pair2, pair3);//多个共享元素
        ActivityCompat.startActivity(activity, intent, compat.toBundle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_t2);

        FrescoManager.loadUrl(URL_IMG).into(imageLeft);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            image.setTransitionName("image1");
//        }
    }
}
