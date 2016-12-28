package com.mp.private_cinema.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.mp.pc_library.base.BaseFragment;
import com.mp.private_cinema.R;

import butterknife.BindView;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Cinema extends BaseFragment {

    @BindView(R.id.area)
    AppCompatSpinner area;
    @BindView(R.id.order)
    AppCompatSpinner order;
    @BindView(R.id.feature)
    AppCompatSpinner feature;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;

    @Override
    protected int getContentID() {
        return R.layout.cinema_fragment;
    }

    public static Fragment_Cinema newInstance() {
        Bundle args = new Bundle();
        Fragment_Cinema fragment = new Fragment_Cinema();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        initSpinnerView();
    }

    private void initSpinnerView() {

    }
}
