package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.utils.LibConstants;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.pc_library.viewpager_indicator.CirclePageIndicator;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_Home_HitFilms;
import com.mp.private_cinema.adapter.Adapter_ViewPager_ImageView;
import com.mp.private_cinema.bean.Bean_Home_Advertisement;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;
import com.mp.private_cinema.utils.Constants;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.hitFilm_RecyclerView)
    RecyclerView hitFilm_RecyclerView;
    @BindView(R.id.more_cinema)
    LinearLayout moreCinema;
    @BindView(R.id.home_scroll)
    LinearLayout homeScroll;

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
//        initAdvertisement();
//        initHitFilms();
//        initHitCinemas();
        addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, responseListener);
//        addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, null, responseListener);
//        addGetRequest(Constants.CMD.HOME_CINEMALIST, Constants.REQUEST_FLAG.HOME_HITCINEMAS, null, responseListener);

    }

    private SimpleResponseListener responseListener = new SimpleResponseListener() {
        @Override
        public void onStart(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:

                default:
                    break;
            }
        }

        @Override
        public void onSucceed(int what, Response response) {
            if (getResultCode(response).equals(LibConstants.JsonName.code_success)) {
                switch (what) {
                    case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                        initAdvertisement(getResultContentJsonArray(response, Bean_Home_Advertisement.class));
                    case Constants.REQUEST_FLAG.HOME_HITFILMS:
                        initHitFilms(getResultContentJsonArray(response, Bean_Home_HitFilms.class));
                    case Constants.REQUEST_FLAG.HOME_HITCINEMAS:
                        initHitCinemas(getResultContentJsonArray(response, Bean_Home_HitCinemas.class));
                    default:
                        break;
                }
            }

        }

        @Override
        public void onFailed(int what, Response response) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:

                default:
                    break;
            }
        }

        @Override
        public void onFinish(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:

                case Constants.REQUEST_FLAG.HOME_HITFILMS:

                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:

                default:
                    break;
            }
        }
    };

    /**
     * 加载首页最上方广告
     *
     * @param advertisements
     */
    private void initAdvertisement(List<Bean_Home_Advertisement> advertisements) {
        final ArrayList<ImageView> imageViewList = new ArrayList<>();
        for (Bean_Home_Advertisement temp : advertisements) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(mContext).load(temp.getADVERTISEMENT_IMAGEPATH()).crossFade().centerCrop().into(imageView);
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

    private void initHitFilms(List<Bean_Home_HitFilms> filmses) {

        Adapter_Home_HitFilms adapter_home_hitFilms = new Adapter_Home_HitFilms(mContext, filmses);
        adapter_home_hitFilms.setOnItemClickListener(new Adapter_Home_HitFilms.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Bean_Home_HitFilms hitFilms) {
                ToastUtils.show(mContext, hitFilms.getMovie_name());
            }
        });
        hitFilm_RecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        hitFilm_RecyclerView.setAdapter(adapter_home_hitFilms);

    }

    private void initHitCinemas(List<Bean_Home_HitCinemas> cinemases) {
        for (final Bean_Home_HitCinemas bean : cinemases) {
            View item_HitCinema = LayoutInflater.from(mContext).inflate(R.layout.home_item_hitcinemas, null);
            ImageView cinema_post = ButterKnife.findById(item_HitCinema, R.id.hitCinema_ImageView);
            TextView cinema_name = ButterKnife.findById(item_HitCinema, R.id.hitCinema_Name);
            TextView cinema_address = ButterKnife.findById(item_HitCinema, R.id.hitCinema_Address);
            TextView cinema_feature = ButterKnife.findById(item_HitCinema, R.id.hitCinema_Feature);
            SimpleRatingBar cinema_rating = ButterKnife.findById(item_HitCinema, R.id.rb_cinemaRating);
            Glide.with(mContext).load(bean.getPicture_address()).into(cinema_post);
            cinema_name.setText(bean.getStore_name());
            cinema_address.setText(bean.getStore_adress());
            cinema_feature.setText(bean.getStore_telephone());
            cinema_rating.setRating(Float.parseFloat(bean.getStore_score()) / 2);
            item_HitCinema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.show(mContext, bean.getStore_name());
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(15, 15, 15, 15);
            item_HitCinema.setLayoutParams(layoutParams);
            homeScroll.addView(item_HitCinema);
        }
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
