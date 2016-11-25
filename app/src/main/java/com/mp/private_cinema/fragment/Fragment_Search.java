package com.mp.private_cinema.fragment;

import android.os.Bundle;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/25
 * 说明
 */

public class Fragment_Search extends BaseFragment {

    public static Fragment_Search newInstance() {
        Bundle args = new Bundle();
        Fragment_Search fragment = new Fragment_Search();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentID() {
        return R.layout.search_fragment;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {

    }
}
