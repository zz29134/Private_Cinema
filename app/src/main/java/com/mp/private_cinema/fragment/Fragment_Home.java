package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.idisfkj.loopview.LoopView;
import com.idisfkj.loopview.entity.LoopViewEntity;
import com.kevin.ultimaterecyclerview.UltimateRecyclerView;
import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.utils.LibConstants;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_Home_HitCinemas;
import com.mp.private_cinema.adapter.Adapter_Home_HitFilms;
import com.mp.private_cinema.bean.Bean_Home_Advertisement;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;
import com.mp.private_cinema.utils.Constants;
import com.mp.private_cinema.widget.DefaultLoadLayout;
import com.mp.private_cinema.widget.DefaultRefreshLayout;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mp.private_cinema.R.id.homeRecyclerView;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Home extends BaseFragment {

    private RecyclerView hitFilm_RecyclerView;
    private LoopView loopView;
    private DefaultLoadLayout loadLayout;
    private Adapter_Home_HitCinemas cinemasAdapter;

    @BindView(R.id.home_two_bar_code_scanner)
    ImageView homeTwoBarCodeScanner;
    @BindView(R.id.home_search)
    TextView homeSearch;
    @BindView(R.id.home_search_icon)
    ImageView homeSearchIcon;
    @BindView(homeRecyclerView)
    UltimateRecyclerView mUltimateRecyclerView;

    @OnClick(R.id.home_search)
    public void onClick() {
        startParent(new StartParentEvent(Fragment_Search.newInstance()));
    }

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
        mUltimateRecyclerView.setHeaderLayout(new DefaultRefreshLayout(mContext));
        loadLayout = new DefaultLoadLayout(mContext);
        mUltimateRecyclerView.setSecondFooterLayout(loadLayout);

        WrapRecyclerView mWrapRecyclerView = mUltimateRecyclerView.getRefreshableView();
        mWrapRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mWrapRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cinemasAdapter = new Adapter_Home_HitCinemas(mContext);
        mWrapRecyclerView.setAdapter(cinemasAdapter);
        mWrapRecyclerView.addHeaderView(initHeaderView());

        addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, responseListener);
        addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, null, responseListener);
        addGetRequest(Constants.CMD.HOME_CINEMALIST, Constants.REQUEST_FLAG.HOME_HITCINEMAS, null, responseListener);

//        List<Bean_Home_Advertisement> advertisements = new ArrayList<>();
//        advertisements.add(new Bean_Home_Advertisement("http://img1.gamedog.cn/2012/07/13/24-120G30940540.jpg", "", ""));
//        advertisements.add(new Bean_Home_Advertisement("http://pic.lvmama.com/uploads/pc/place2/2015-08-03/032897a6-f54e-4030-b95c-aa9c2ae83947_480_320.jpg", "", ""));
//        advertisements.add(new Bean_Home_Advertisement("http://img3.imgtn.bdimg.com/it/u=656334941,1373975950&fm=21&gp=0.jpg", "", ""));
//        advertisements.add(new Bean_Home_Advertisement("http://img1.qunarzz.com/travel/poi/1408/26/fc137f9bab25161bffffffffc8d65eac.jpg_r_480x360x95_7ab8a034.jpg", "", ""));
//        List<Bean_Home_HitFilms> hitFilms = new ArrayList<>();
//        hitFilms.add(new Bean_Home_HitFilms("1", "电影1", "type1", "", "", "", "", "", "", "", "1", "", "http://image4.xyzs.com/upload/77/6d/1447032350767745/20151110/144712267591355_0.png", ""));
//        hitFilms.add(new Bean_Home_HitFilms("2", "电影2", "type2", "", "", "", "", "", "", "", "2", "", "http://img.25pp.com/uploadfile/bizhi/iphone3/2013/0117/20130117111149500.jpg", ""));
//        hitFilms.add(new Bean_Home_HitFilms("3", "电影3", "type3", "", "", "", "", "", "", "", "3", "", "http://img2.imgtn.bdimg.com/it/u=1833678113,705995739&fm=21&gp=0.jpg", ""));
//        hitFilms.add(new Bean_Home_HitFilms("4", "电影4", "type4", "", "", "", "", "", "", "", "4", "", "http://att.x2.hiapk.com/forum/201205/27/2248307767q30felqe77em.jpg", ""));
//        hitFilms.add(new Bean_Home_HitFilms("5", "电影5", "type5", "", "", "", "", "", "", "", "5", "", "http://img.25pp.com/uploadfile/bizhi/iphone3/2014/1027/20141027055746977.jpg", ""));
//        hitFilms.add(new Bean_Home_HitFilms("6", "电影6", "type6", "", "", "", "", "", "", "", "6", "", "http://img.lenovomm.com/s3/img/app/app-img-lestore/8275-2015-09-10115719-1441929439127.jpeg?isCompress=true&width=320&height=480&quantity=1&rotate=true", ""));
//        List<Bean_Home_HitCinemas> cinemas = new ArrayList<>();
//        cinemas.add(new Bean_Home_HitCinemas("", "影院1", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "3", "", "http://image4.xyzs.com/upload/77/6d/1447032350767745/20151110/144712267591355_0.png"));
//        cinemas.add(new Bean_Home_HitCinemas("", "影院2", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "4", "", "http://img.25pp.com/uploadfile/bizhi/iphone3/2013/0117/20130117111149500.jpg"));
//        cinemas.add(new Bean_Home_HitCinemas("", "影院3", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "5", "", "http://img2.imgtn.bdimg.com/it/u=1833678113,705995739&fm=21&gp=0.jpg"));
//        cinemas.add(new Bean_Home_HitCinemas("", "影院4", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "6", "", "http://att.x2.hiapk.com/forum/201205/27/2248307767q30felqe77em.jpg"));
//        cinemas.add(new Bean_Home_HitCinemas("", "影院5", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "7", "", "http://img.25pp.com/uploadfile/bizhi/iphone3/2014/1027/20141027055746977.jpg"));
//        cinemas.add(new Bean_Home_HitCinemas("", "影院6", "地址：XXXXXXXXXXXXXX", "电话：15111111111", "8", "", "http://img.lenovomm.com/s3/img/app/app-img-lestore/8275-2015-09-10115719-1441929439127.jpeg?isCompress=true&width=320&height=480&quantity=1&rotate=true"));
//        initAdvertisement(advertisements);
//        initHitFilms(hitFilms);
//        initHitCinemas(cinemas);
    }

    private View initHeaderView() {
        View headerView = mInflater.inflate(R.layout.home_recyclerview_headerview, mUltimateRecyclerView.getRefreshableView(), false);
        loopView = ButterKnife.findById(headerView, R.id.home_LoopView);
        hitFilm_RecyclerView = ButterKnife.findById(headerView, R.id.hitFilm_RecyclerView);

        return headerView;
    }

    private OnResponseListener<String> responseListener = new OnResponseListener<String>() {
        @Override
        public void onStart(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:
                    break;
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
                        break;
                    case Constants.REQUEST_FLAG.HOME_HITFILMS:
                        initHitFilms(getResultContentJsonArray(response, Bean_Home_HitFilms.class));
                        break;
                    case Constants.REQUEST_FLAG.HOME_HITCINEMAS:
                        initHitCinemas(getResultContentJsonArray(response, Bean_Home_HitCinemas.class));
                        break;
                    default:
                        break;
                }
            }

        }

        @Override
        public void onFailed(int what, Response response) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onFinish(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITCINEMAS:
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * @param advertisements
     */
    private void initAdvertisement(List<Bean_Home_Advertisement> advertisements) {
        List<LoopViewEntity> entityList = new ArrayList<>();
        for (Bean_Home_Advertisement temp : advertisements) {
            LoopViewEntity entity = new LoopViewEntity();
            entity.setImageUrl(temp.getADVERTISEMENT_IMAGEPATH());
            entityList.add(entity);
        }
        loopView.setLoopData(entityList);

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
        cinemasAdapter.add(cinemases);
    }

    private void initEvent() {
        // 设置刷新监听
        mUltimateRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<WrapRecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<WrapRecyclerView> refreshView) {
                addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, responseListener);
                addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, null, responseListener);
                addGetRequest(Constants.CMD.HOME_CINEMALIST, Constants.REQUEST_FLAG.HOME_HITCINEMAS, null, responseListener);
            }
        });

        // 设置最后一个条目可见监听
        mUltimateRecyclerView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                boolean hasMoreData = loadLayout.isHasMoreData();
                if(hasMoreData) {
                    addGetRequest(Constants.CMD.HOME_CINEMALIST, Constants.REQUEST_FLAG.HOME_HITCINEMAS, null, responseListener);
                }
            }
        });
    }
}
