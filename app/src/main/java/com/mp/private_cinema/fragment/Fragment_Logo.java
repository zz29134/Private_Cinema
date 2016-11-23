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
import com.mp.private_cinema.R;
import com.mp.private_cinema.activity.Activity_Main;
import com.mp.private_cinema.base.BaseFragment;
import com.mp.private_cinema.bean.Advertisement_Index;
import com.mp.private_cinema.utils.Constants;
import com.orhanobut.logger.Logger;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Fragment_Logo extends BaseFragment {

    private int SECOND_COUNTDOWN = 5;
    private Advertisement_Index ad;

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;

    @OnClick(R.id.textView)
    public void onClick() {
        handler_countdown.removeCallbacks(runnable_countdown);
        startActivity(new Intent(_mActivity, Activity_Main.class));
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
            if (null != ad && TextUtils.isEmpty(ad.getAdvertisement_ImagePath())
                    && TextUtils.isEmpty(ad.getAdvertisement_ID())
                    && DateUtils.getInstance().millis() - DateUtils.getInstance().millisOfDate(ad.getAdvertisement_DeadLine()) < 0) {
                Glide.with(mContext).load(ad.getAdvertisement_ImagePath()).listener(new RequestListener<String, GlideDrawable>() {

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);
                handler_countdown.postDelayed(runnable_countdown, 0);
            } else {
                goToMainActivity();
            }
        }
    };

    public static Fragment_Logo newInstance() {
        Bundle args = new Bundle();
        Fragment_Logo fragment = new Fragment_Logo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.logo_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        addGetRequest(Constants.CMD.WELCOME, Constants.REQUEST_FLAG.LOAD_ADVERTISEMENT, null, new SimpleResponseListener<String>() {
            @Override
            public void onStart(int what) {
//                handler_logo.postDelayed(runnable_logo, 3000);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Logger.e(response.get());
//                ad = mGson.fromJson(response.get(), Advertisement_Index.class);
            }
        });
    }

    private void goToMainActivity() {
        startActivity(new Intent(_mActivity, Activity_Main.class));
        _mActivity.finish();
    }
}
