package com.mp.private_cinema.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.mp.pc_library.app.AppManager;
import com.mp.pc_library.utils.SPUtils;
import com.mp.private_cinema.R;
import com.mp.pc_library.base.BaseActivity;
import com.mp.private_cinema.fragment.Fragment_Logo;
import com.mp.private_cinema.fragment.Fragment_Navigator;

import me.yokeyword.fragmentation.SupportFragment;

import static com.mp.pc_library.utils.LibConstants.FLAG_NAVIGATOR;

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
    protected SupportFragment getRootFragment() {
        if (SPUtils.getBoolean(this, FLAG_NAVIGATOR, true)) {
            return Fragment_Navigator.newInstance();
        } else {
            return Fragment_Logo.newInstance();
        }
    }

    @Override
    protected int getFragmentContentLayoutID() {
        return R.id.index_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().killActivity(this);
    }
}
