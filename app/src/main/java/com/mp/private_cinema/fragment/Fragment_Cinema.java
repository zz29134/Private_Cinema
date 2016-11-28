package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.pc_library.base.BaseFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Cinema extends BaseFragment {

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }

    public static Fragment_Cinema newInstance() {
        Bundle args = new Bundle();
        Fragment_Cinema fragment = new Fragment_Cinema();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.cinema_fragment;
    }
}
