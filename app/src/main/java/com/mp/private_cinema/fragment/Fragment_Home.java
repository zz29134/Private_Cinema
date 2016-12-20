package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.kevin.loopview.AdLoopView;
import com.kevin.loopview.internal.LoopData;
import com.mp.pc_library.base.BaseFragment;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.utils.LibConstants;
import com.mp.pc_library.utils.ToastUtils;
import com.mp.private_cinema.R;
import com.mp.private_cinema.adapter.Adapter_Home_HitFilms;
import com.mp.private_cinema.bean.Bean_Home_Advertisement;
import com.mp.private_cinema.bean.Bean_Home_HitCinemas;
import com.mp.private_cinema.bean.Bean_Home_HitFilms;
import com.mp.private_cinema.utils.Constants;
import com.mp.private_cinema.widget.MyDefaultRecyclerViewFooter;
import com.mp.private_cinema.widget.MyDefaultRecyclerViewHeader;
import com.mrw.wzmrecyclerview.AutoLoad.AutoLoadRecyclerView;
import com.mrw.wzmrecyclerview.HeaderAndFooter.OnItemClickListener;
import com.mrw.wzmrecyclerview.LayoutManager.WZMLinearLayoutManager;
import com.mrw.wzmrecyclerview.PullToLoad.OnLoadListener;
import com.mrw.wzmrecyclerview.PullToRefresh.OnRefreshListener;
import com.mrw.wzmrecyclerview.SimpleAdapter.SimpleAdapter;
import com.mrw.wzmrecyclerview.SimpleAdapter.ViewHolder;
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

public class Fragment_Home extends BaseFragment {

    private AdLoopView loopView;
    private RecyclerView hitFilm_RecyclerView;
    private ArrayList<Bean_Home_HitCinemas> hitCinemasList = new ArrayList<>();

    private Integer OFFSET = 0;
    private Integer LIMIT = 5;

    @BindView(R.id.autoLoadRecyclerView)
    AutoLoadRecyclerView autoLoadRecyclerView;

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
        initAutoLoadRecyclerView();
    }

    private void initAutoLoadRecyclerView() {
        autoLoadRecyclerView.setLayoutManager(new WZMLinearLayoutManager(WZMLinearLayoutManager.VERTICAL));
        autoLoadRecyclerView.setAdapter(new SimpleAdapter<Bean_Home_HitCinemas>(mContext, hitCinemasList, R.layout.home_item_hitcinemas) {
            @Override
            protected void onBindViewHolder(ViewHolder holder, Bean_Home_HitCinemas data) {
                Glide.with(mContext).load(data.getPICTURE_ADDRESS()).into(((ImageView) holder.getView(R.id.hitCinema_ImageView)));
                holder.setText(R.id.hitCinema_Name, data.getCINEMA_NAME());
                holder.setText(R.id.hitCinema_Address, data.getCINEMA_ADDRESS());
                holder.setText(R.id.hitCinema_Feature, data.getCINEMA_TELEPHONE());
                ((SimpleRatingBar) holder.getView(R.id.rb_cinemaRating)).setRating(Float.parseFloat(data.getCINEMA_SCORE()) / 2);
            }
        });
        autoLoadRecyclerView.setRefreshViewCreator(new MyDefaultRecyclerViewHeader());
        autoLoadRecyclerView.setAutoLoadViewCreator(new MyDefaultRecyclerViewFooter());
        autoLoadRecyclerView.addHeaderView(initHeaderView());
        autoLoadRecyclerView.setOnLoadListener(new OnLoadListener() {

            @Override
            public void onStartLoading(int skip) {
                OFFSET = OFFSET + LIMIT;
                getData();
            }
        });
        autoLoadRecyclerView.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onStartRefreshing() {
                hitCinemasList.clear();
                OFFSET = 0;
                getData();
            }
        });
        autoLoadRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                ToastUtils.show(mContext, hitCinemasList.get(position).getCINEMA_NAME());
            }
        });
    }

    private View initHeaderView() {
        View headerView = mInflater.inflate(R.layout.home_recyclerview_headerview, null);
        loopView = ButterKnife.findById(headerView, R.id.home_LoopView);
        hitFilm_RecyclerView = ButterKnife.findById(headerView, R.id.hitFilm_RecyclerView);
        hitFilm_RecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        Adapter_Home_HitFilms adapter_home_hitFilms = new Adapter_Home_HitFilms(mContext, new ArrayList<Bean_Home_HitFilms>());
        adapter_home_hitFilms.setOnItemClickListener(new Adapter_Home_HitFilms.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Bean_Home_HitFilms hitFilms) {
                ToastUtils.show(mContext, hitFilms.getMovie_name());
            }
        });
        hitFilm_RecyclerView.setAdapter(adapter_home_hitFilms);
        return headerView;
    }

    private void initAdvertisement(List<Bean_Home_Advertisement> advertisements) {
        LoopData loopData = new LoopData();
        loopData.items = new ArrayList<>();
        for (Bean_Home_Advertisement temp : advertisements) {
            LoopData.ItemData data = loopData.new ItemData("", temp.getADVERTISEMENT_IMAGEPATH(), "", "", "");
            loopData.items.add(data);
        }
        if (null != loopView) {
            loopView.setLoopViewPager(loopData);
        }
    }

    private void initHitFilms(List<Bean_Home_HitFilms> filmses) {
        if (null != hitFilm_RecyclerView) {
            ((Adapter_Home_HitFilms) hitFilm_RecyclerView.getAdapter()).addData(filmses);
        }
    }

    private void initHitCinemas(List<Bean_Home_HitCinemas> cinemases) {
        if (cinemases != null) {
            if (cinemases.size() < LIMIT) {
                autoLoadRecyclerView.setNoMore(true);
            } else {
                autoLoadRecyclerView.setNoMore(false);
            }
            hitCinemasList.addAll(cinemases);
            autoLoadRecyclerView.getAdapter().notifyDataSetChanged();
        } else {

        }
        autoLoadRecyclerView.completeRefresh();
        autoLoadRecyclerView.completeLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            addGetRequest(Constants.CMD.HOME_ADVERTISEMENT, Constants.REQUEST_FLAG.HOME_ADVERTISEMENT_TOP, null, responseListener);
            addGetRequest(Constants.CMD.HOME_FILMLIST, Constants.REQUEST_FLAG.HOME_HITFILMS, null, responseListener);
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
            if (getResultCode(response).equals(LibConstants.JsonName.code_success)) {
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
}
