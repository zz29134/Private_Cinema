package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment_LoadData;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Fragment_Logo extends BaseFragment_LoadData {

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
    protected void lazyLoadData() {

    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }
}
