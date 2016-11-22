package com.mp.private_cinema.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentID());
        initTools();
        if (savedInstanceState == null) {
            loadRootFragment(getFragmentContentLayoutID(), getRootFragment());
        }
    }

    private void initTools() {
        ButterKnife.bind(this);

    }

    /**
     * 获取布局LayoutID
     */
    protected abstract int getContentID();

    /**
     * 获取当前Activity要加载的根Fragment
     */
    protected abstract SupportFragment getRootFragment();

    /**
     * 获取当前Activity中要放置Fragment的控件ID
     */
    protected abstract int getFragmentContentLayoutID();

}
