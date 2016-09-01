package com.zlb.demos.androiddemos.material;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.fresco.FrescoManager;

import butterknife.Bind;
import butterknife.OnClick;

public class MaterialTransitionActivity extends BaseActivity {

    public static String URL_IMG = "http://breadtrip-dev.qiniudn.com/hunter/product/cover/90559_o_1apd1aok0kkt1q1t1eovri0102f9.jpg?imageView2/5/w/788/h/426/format/jpg/interlace/1/";
    @Bind(R.id.m_trans_image_left) protected SimpleDraweeView imageLeft;
    @Bind(R.id.m_trans_image_right) protected ImageView imageRight;

    @Bind(R.id.m_trans_btn) protected Button button;
    @Bind(R.id.m_together) protected Button together;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setExitTransition(new Explode());
//        }

        setContentView(R.layout.activity_material_transition);
        FrescoManager.loadUrl(URL_IMG).into(imageLeft);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            image.setTransitionName("image1");
//        }
    }

    @OnClick(R.id.m_trans_image_left)
    public void onImageClicked(View view) {
        MaterialT2Activity.launch(this, view, getString(R.string.trans_image_left));
    }

    @OnClick(R.id.m_trans_image_right)
    public void onLocalImageClicked(View view) {
        MaterialT2Activity.launch(this, view, getString(R.string.trans_image_right));
    }

    @OnClick(R.id.m_trans_btn)
    public void onBtnClicked(View view) {
        MaterialT2Activity.launch(this, view, getString(R.string.trans_btn));
    }

    @OnClick(R.id.m_together)
    public void onTogetherClicked(View view) {
        MaterialT2Activity.launch(this, imageLeft, getString(R.string.trans_image_left), imageRight, getString(R.string.trans_image_right), button, getString(R.string.trans_btn));
    }

}
