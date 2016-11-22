package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Fragment_Logo extends BaseFragment {

    private static final int LOAD_ADVERTISMENT = 0x00F;

    public static Fragment_Logo newInstance() {
        Bundle args = new Bundle();
        Fragment_Logo fragment = new Fragment_Logo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentID() {
        return R.layout.logo_fragment;
    }
}
