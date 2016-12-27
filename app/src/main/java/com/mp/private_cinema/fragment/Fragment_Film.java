package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.mp.pc_library.base.BaseFragment;
import com.mp.private_cinema.R;

import butterknife.BindView;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Film extends BaseFragment implements
        RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener,
        RecyclerArrayAdapter.OnItemClickListener, RecyclerArrayAdapter.OnItemLongClickListener,
        RecyclerArrayAdapter.OnNoMoreListener, RecyclerArrayAdapter.OnErrorListener {


    @BindView(R.id.type)
    LinearLayout type;
    @BindView(R.id.area)
    LinearLayout area;
    @BindView(R.id.year)
    LinearLayout year;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }

    public static Fragment_Film newInstance() {
        Bundle args = new Bundle();
        Fragment_Film fragment = new Fragment_Film();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.film_fragment;
    }

    @Override
    public void onErrorShow() {

    }

    @Override
    public void onErrorClick() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onMoreShow() {

    }

    @Override
    public void onMoreClick() {

    }

    @Override
    public void onNoMoreShow() {

    }

    @Override
    public void onNoMoreClick() {

    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {

    }
}
