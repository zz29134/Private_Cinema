package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.pc_library.viewpager_indicator.CirclePageIndicator;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_Home_HitFilms;
import com.mp.private_cinema.adapter.Adapter_ViewPager_ImageView;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;
import com.mp.private_cinema.utils.Constants;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;

import java.util.ArrayList;
import java.util.List;
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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
//        addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, responseListener);
//        addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, null, responseListener);

    }

    /**
     * 加载首页最上方广告
     */
    private void initAdvertisement() {

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
        List<Bean_Home_HitFilms> hitFilmsList = new ArrayList<>();
        hitFilmsList.add(new Bean_Home_HitFilms("电影1", "http://img2.imgtn.bdimg.com/it/u=1681751274,1729335524&fm=21&gp=0.jpg", "9.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影2", "http://d15.lxyes.com/15xm/prev/20151211/9/99862808.jpg", "8.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影3", "http://img1.gamedog.cn/2012/03/06/20-120306142Z8.jpg", "7.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影4", "http://img1.gamedog.cn/2012/03/06/20-120306142Z6.jpg", "6.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影5", "http://img2.imgtn.bdimg.com/it/u=1681751274,1729335524&fm=21&gp=0.jpg", "5.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影6", "http://d15.lxyes.com/15xm/prev/20151211/9/99862808.jpg", "4.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影7", "http://img1.gamedog.cn/2012/03/06/20-120306142Z8.jpg", "3.0"));
        hitFilmsList.add(new Bean_Home_HitFilms("电影8", "http://img1.gamedog.cn/2012/03/06/20-120306142Z6.jpg", "2.0"));

        Adapter_Home_HitFilms adapter_home_hitFilms = new Adapter_Home_HitFilms(mContext, hitFilmsList);
        adapter_home_hitFilms.setOnItemClickListener(new Adapter_Home_HitFilms.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Bean_Home_HitFilms hitFilms) {
                ToastUtils.show(mContext, hitFilms.getFilm_Name());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter_home_hitFilms);

    }

    private SimpleResponseListener responseListener = new SimpleResponseListener() {
        @Override
        public void onStart(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                default:
                    break;
            }
        }

        @Override
        public void onSucceed(int what, Response response) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    initAdvertisement();
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    initHitFilms();
                default:
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                default:
                    break;
            }
        }

        @Override
        public void onFinish(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
}
