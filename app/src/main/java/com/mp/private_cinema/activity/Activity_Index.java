package com.mp.private_cinema.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mp.private_cinema.R;
import com.mp.private_cinema.base.BaseActivity;
import com.mp.private_cinema.base.BaseFragment;
import com.mp.private_cinema.fragment.Fragment_Logo;
import com.mp.private_cinema.fragment.Fragment_Navigator;
import com.mp.private_cinema.utils.Constants;
import com.mp.private_cinema.utils.SPUtils;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明   .
 */

public class Activity_Index extends BaseActivity {

    @Override
    protected int getContentID() {
        return R.layout.index_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SPUtils.getBoolean(this, Constants.FLAG_NAVIGATOR, true)) {
            loadFragment(Fragment_Navigator.newInstance());
        } else {
            loadFragment(Fragment_Logo.newInstance());
        }
    }

    private void loadFragment(BaseFragment fragment) {
        loadRootFragment(R.id.index_activity, fragment);
    }

}
