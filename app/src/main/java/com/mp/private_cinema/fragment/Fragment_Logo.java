package com.mp.private_cinema.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mp.pc_library.utils.DateUtils;
import com.mp.pc_library.utils.ImageUtils;
import com.mp.private_cinema.R;
import com.mp.private_cinema.activity.Activity_Main;
import com.mp.private_cinema.base.BaseFragment;
import com.mp.private_cinema.bean.Bean_Advertisement_Index;
import com.mp.private_cinema.utils.Constants;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Fragment_Logo extends BaseFragment {

    private int SECOND_COUNTDOWN = 6;
    private Bean_Advertisement_Index ad;

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;

    /**
     * 点击“跳过”按钮
     */
    @OnClick(R.id.textView)
    public void onClick() {
        handler_countdown.removeCallbacks(runnable_countdown);
        goToMainActivity();
    }

    public static Fragment_Logo newInstance() {
        Bundle args = new Bundle();
        Fragment_Logo fragment = new Fragment_Logo();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Fragment布局Layout
     * @return
     */
    @Override
    protected int getContentID() {
        return R.layout.logo_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        addGetRequest(Constants.CMD.WELCOME, Constants.REQUEST_FLAG.LOGO_ADVERTISEMENT, null, new SimpleResponseListener<String>() {
            @Override
            public void onStart(int what) {
                ad = new Bean_Advertisement_Index();
                handler_logo.postDelayed(runnable_logo, 3000);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                ad = mGson.fromJson(response.get(), Bean_Advertisement_Index.class);
            }
        });
    }

    /**
     * “跳过”倒计时相关线程
     */
    private Handler handler_countdown = new Handler();
    private Runnable runnable_countdown = new Runnable() {
        @Override
        public void run() {
            if (SECOND_COUNTDOWN > 0) {
                SECOND_COUNTDOWN--;
                textView.setVisibility(View.VISIBLE);
                textView.setText("跳过 " + SECOND_COUNTDOWN);
                handler_countdown.postDelayed(runnable_countdown, 1000);
            } else {
                goToMainActivity();
            }
        }
    };

    /**
     * Logo显示相关线程
     */
    private Handler handler_logo = new Handler();
    private Runnable runnable_logo = new Runnable() {
        @Override
        public void run() {
            if (!TextUtils.isEmpty(ad.getADVERTISEMENT_ID())
                    && !TextUtils.isEmpty(ad.getADVERTISEMENT_IMAGEPATH())
                    && DateUtils.getInstance().millis() - DateUtils.getInstance().millisOfDate(ad.getADVERTISEMENT_DEADLINE()) < 0) {
                List<Bean_Advertisement_Index> ad_find = DataSupport.where("ADVERTISEMENT_ID=?", ad.getADVERTISEMENT_ID()).find(Bean_Advertisement_Index.class);
                if (null != ad_find && ad_find.size() == 1) {
                    if (null != ad_find.get(0).getADVERTISEMENT_IMAGE()) {
                        Glide.with(mContext).load(ad_find.get(0).getADVERTISEMENT_IMAGE()).into(imageView);
                        handler_countdown.post(runnable_countdown);
                    } else {
                        getAdvertisementImage();
                    }
                } else {
                    getAdvertisementImage();
                }
            } else {
                cancelRequest(Constants.REQUEST_FLAG.LOGO_ADVERTISEMENT);
                goToMainActivity();
            }
        }
    };

    /**
     * 网络获取广告图片
     */
    private void getAdvertisementImage() {
        handler_ImageLoad.postDelayed(runnable_ImageLoad, 3000);
        Glide.with(mContext).load(ad.getADVERTISEMENT_IMAGEPATH()).dontAnimate().listener(new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                handler_ImageLoad.removeCallbacks(runnable_ImageLoad);
                ad.setADVERTISEMENT_IMAGE(ImageUtils.getInstance().Drawable2Bytes(resource));
                ad.save();
                handler_countdown.post(runnable_countdown);
                return false;
            }

            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                goToMainActivity();
                return false;
            }

        }).into(imageView);
    }

    /**
     * 图片加载超时限制
     * 限制最长加载时间为3秒，否则直接进入主界面
     */
    private Handler handler_ImageLoad = new Handler();
    private Runnable runnable_ImageLoad = new Runnable() {
        @Override
        public void run() {
            goToMainActivity();
        }
    };

    /**
     * 跳转主界面
     */
    private void goToMainActivity() {
        startActivity(new Intent(_mActivity, Activity_Main.class));
        _mActivity.finish();
    }
}
