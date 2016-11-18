package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment_LoadData;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Home extends BaseFragment_LoadData {

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

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
    protected void lazyLoadData() {

    }

}
