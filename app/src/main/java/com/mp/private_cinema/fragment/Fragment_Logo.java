package com.mp.private_cinema.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mp.private_cinema.R;
import com.mp.private_cinema.activity.Activity_Main;
import com.mp.private_cinema.base.BaseFragment;
import com.mp.private_cinema.utils.Constants;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Fragment_Logo extends BaseFragment {

    private static final int LOAD_ADVERTISEMENT = 0x00F;

    private int SECOND_COUNTDOWN = 5;

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
                startActivity(new Intent(_mActivity, Activity_Main.class));
                _mActivity.finish();
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

        addGetRequest(Constants.CMD.WELCOME, LOAD_ADVERTISEMENT, null, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
//                Glide.with(_mActivity).load(response.get()).into(imageView);
            }
        });
//        Glide.with(_mActivity).load("http://img1.mydrivers.com/img/20140604/cc9403330d6245e590f16427586f09da.jpg").into(imageView);
//        handler_countdown.postDelayed(runnable_countdown, 3000);
    }
}
