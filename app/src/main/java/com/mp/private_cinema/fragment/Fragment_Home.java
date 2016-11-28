package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mp.pc_library.viewpager_indicator.CirclePageIndicator;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_ViewPager_ImageView;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Home extends BaseFragment {

    @BindView(R.id.home_two_bar_code_scanner)
    ImageView homeTwoBarCodeScanner;
    @BindView(R.id.home_search)
    TextView homeSearch;
    @BindView(R.id.home_search_icon)
    ImageView homeSearchIcon;
    @BindView(R.id.home_top_viewPager)
    ViewPager home_top_viewPager;
    @BindView(R.id.home_top_indicator)
    CirclePageIndicator home_top_indicator;
    @BindView(R.id.more_film)
    LinearLayout moreFilm;
    @BindView(R.id.hit_film_viewPager)
    ViewPager hitFilmViewPager;
    @BindView(R.id.hit_film_indicator)
    CirclePageIndicator hitFilmIndicator;

    @OnClick(R.id.home_search)
    public void onClick() {
        startParent(new StartParentEvent(Fragment_Search.newInstance()));
    }

    private static final int UPTATE_ADVERTISEMENT_TOP = 0;
    private Timer timer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_ADVERTISEMENT_TOP:
                    if (msg.arg1 != 0) {
                        home_top_viewPager.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        home_top_viewPager.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };

    public static Fragment_Home newInstance() {
        Bundle args = new Bundle();
        Fragment_Home fragment = new Fragment_Home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.home_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        initAdvertisement();
        initHitFilms();
    }

    /**
     * 加载首页最上方广告
     */
    private void initAdvertisement() {
//        addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, new SimpleResponseListener<String>() {
//            @Override
//            public void onStart(int what) {
//                super.onStart(what);
//            }
//
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//                super.onSucceed(what, response);
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//                super.onFailed(what, response);
//            }
//
//            @Override
//            public void onFinish(int what) {
//                /**
//                 * 实现viewPager自动轮播
//                 */
//            }
//        });

        String[] imagePaths = {
                "http://www.microfotos.com/pic/1/121/12199/1219932preview4.jpg",
                "http://d.hiphotos.baidu.com/exp/w=480/sign=e7f6471ade88d43ff0a990fa4d1fd2aa/024f78f0f736afc362c4df0abb19ebc4b6451290.jpg",
                "http://img2.imgtn.bdimg.com/it/u=508400732,572530980&fm=21&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=2772389283,3198910176&fm=21&gp=0.jpg"
        };
        final ArrayList<ImageView> imageViewList = new ArrayList<>();
        for (String temp : imagePaths) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(mContext).load(temp).crossFade().centerCrop().into(imageView);
            imageViewList.add(imageView);
        }
        home_top_viewPager.setAdapter(new Adapter_ViewPager_ImageView(imageViewList));
        home_top_indicator.setRadius(13.0F);
        home_top_indicator.setPageColor(ContextCompat.getColor(getContext(), R.color.cyan700));
        home_top_indicator.setFillColor(ContextCompat.getColor(getContext(), R.color.ameber400));
        home_top_indicator.setStrokeColor(ContextCompat.getColor(getContext(), R.color.blueGrey600));
        home_top_indicator.setStrokeWidth(1.0F);
        home_top_indicator.setViewPager(home_top_viewPager);

        // 自动轮播代码
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_ADVERTISEMENT_TOP;
                if (home_top_viewPager.getCurrentItem() == imageViewList.size() - 1) {
                    message.arg1 = 0;
                } else {
                    message.arg1 = home_top_viewPager.getCurrentItem() + 1;
                }
                mHandler.sendMessage(message);
            }
        }, 5000, 5000);
    }

    private void initHitFilms() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
}
