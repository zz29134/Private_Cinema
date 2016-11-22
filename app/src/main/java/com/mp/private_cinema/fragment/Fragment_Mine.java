package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/16
 * 用途   .
 */

public class Fragment_Mine extends BaseFragment {

    public static Fragment_Mine newInstance() {
        Bundle args = new Bundle();
        Fragment_Mine fragment = new Fragment_Mine();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }
}
