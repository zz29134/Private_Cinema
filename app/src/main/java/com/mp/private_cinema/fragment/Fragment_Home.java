package com.mp.private_cinema.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.utils.LibConstants;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_Home_Advertisement;
import com.mp.private_cinema.adapter.Adapter_Home_HitFilms;
import com.mp.private_cinema.bean.Bean_Home_Advertisement;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;
import com.mp.private_cinema.utils.Constants;
import com.mp.private_cinema.viewholder.HitCinemasViewHolder;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途
 */

public class Fragment_Home extends BaseFragment implements
        RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener,
        RecyclerArrayAdapter.OnItemClickListener, RecyclerArrayAdapter.OnItemLongClickListener,
        RecyclerArrayAdapter.OnNoMoreListener, RecyclerArrayAdapter.OnErrorListener {

    private RecyclerView hitFilm_RecyclerView;
    private List<Bean_Home_Advertisement> advertisements = new ArrayList<>();
    private RecyclerArrayAdapter<Bean_Home_HitCinemas> cinemasAdapter;
    private Adapter_Home_Advertisement advertisementAdapter;

    private Integer OFFSET = 0;
    private Integer LIMIT = 5;

    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        easyRecyclerView.setLayoutManager(layoutManager);
        easyRecyclerView.setAdapterWithProgress(cinemasAdapter = new RecyclerArrayAdapter<Bean_Home_HitCinemas>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new HitCinemasViewHolder(parent);
            }
        });
        cinemasAdapter.setMore(R.layout.view_more, this);
        cinemasAdapter.setNoMore(R.layout.view_nomore, this);
        cinemasAdapter.setError(R.layout.view_error, this);
        cinemasAdapter.setOnItemClickListener(this);
        cinemasAdapter.setOnItemLongClickListener(this);
        cinemasAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View header = LayoutInflater.from(mContext).inflate(R.layout.home_recyclerview_headerview, null);
                RollPagerView rollPagerView = ButterKnife.findById(header, R.id.rollPagerView);
                rollPagerView.setHintView(new ColorPointHintView(mContext, Color.YELLOW, Color.GRAY));
                rollPagerView.setAdapter(advertisementAdapter = new Adapter_Home_Advertisement(rollPagerView, mContext, advertisements));

                hitFilm_RecyclerView = ButterKnife.findById(header, R.id.hitFilm_RecyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                hitFilm_RecyclerView.setLayoutManager(layoutManager);
                Adapter_Home_HitFilms adapter_home_hitFilms = new Adapter_Home_HitFilms(mContext, new ArrayList<Bean_Home_HitFilms>());
                adapter_home_hitFilms.setOnItemClickListener(new Adapter_Home_HitFilms.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, Bean_Home_HitFilms hitFilms) {
                        ToastUtils.show(mContext, hitFilms.getMovie_name());
                    }
                });
                hitFilm_RecyclerView.setAdapter(adapter_home_hitFilms);
                return header;
            }

            @Override
            public void onBindView(View headerView) {
                ((ViewGroup) headerView).requestDisallowInterceptTouchEvent(true);
            }
        });
        easyRecyclerView.setRefreshListener(this);
    }

    private void initAdvertisement(List<Bean_Home_Advertisement> ads) {
        advertisements.addAll(ads);
        advertisementAdapter.notifyDataSetChanged();
    }

    private void initHitFilms(List<Bean_Home_HitFilms> films) {
        if (null != hitFilm_RecyclerView) {
            ((Adapter_Home_HitFilms) hitFilm_RecyclerView.getAdapter()).addData(films);
        }
    }

    private void initHitCinemas(List<Bean_Home_HitCinemas> cinemas) {
        if (cinemas != null) {
            if (cinemas.size() < LIMIT) {
                cinemasAdapter.stopMore();
            }
            cinemasAdapter.addAll(cinemas);
        } else {
            cinemasAdapter.pauseMore();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, responseListener);
            addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, responseListener);
            getData();
        }
    }

    private OnResponseListener<String> responseListener = new OnResponseListener<String>() {
        @Override
        public void onStart(int what) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    break;
                case Constants.REQUEST_FLAG.CINEMA_RECOMMEND:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onSucceed(int what, Response response) {
            if (getResultCode(response).equals(LibConstants.ResultKey.code_success)) {
                switch (what) {
                    case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                        initAdvertisement(getResultContentJsonArray(response, Bean_Home_Advertisement.class));
                        break;
                    case Constants.REQUEST_FLAG.HOME_HITFILMS:
                        initHitFilms(getResultContentJsonArray(response, Bean_Home_HitFilms.class));
                        break;
                    case Constants.REQUEST_FLAG.CINEMA_RECOMMEND:
                        initHitCinemas(getResultContentJsonArray(response, Bean_Home_HitCinemas.class));
                        break;
                    default:
                        break;
                }
            } else {
                ToastUtils.show(mContext, response.get().toString());
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            switch (what) {
                case Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP:
                    break;
                case Constants.REQUEST_FLAG.HOME_HITFILMS:
                    break;
                case Constants.REQUEST_FLAG.CINEMA_RECOMMEND:
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
                case Constants.REQUEST_FLAG.CINEMA_RECOMMEND:
                    break;
                default:
                    break;
            }
        }
    };

    private void getData() {
        Map<String, String> paramsCinema = new HashMap<>();
        paramsCinema.put(Constants.params.offset, OFFSET + "");
        paramsCinema.put(Constants.params.limit, LIMIT + "");
        addGetRequest(Constants.CMD.CINEMA_RECOMMEND, Constants.REQUEST_FLAG.CINEMA_RECOMMEND, paramsCinema, responseListener);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        cinemasAdapter.clear();
        OFFSET = 0;
        getData();
    }

    @Override
    public void onMoreClick() {
//        OFFSET = OFFSET + LIMIT;
//        getData();
    }

    @Override
    public void onMoreShow() {
        OFFSET = OFFSET + LIMIT;
        getData();
    }

    @Override
    public void onErrorShow() {
        cinemasAdapter.pauseMore();
    }

    @Override
    public void onErrorClick() {
        cinemasAdapter.resumeMore();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onNoMoreShow() {

    }

    @Override
    public void onNoMoreClick() {

    }
}
