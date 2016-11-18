package com.mp.private_cinema.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentID());
        initTools();

    }

    private void initTools() {
        ButterKnife.bind(this);

    }

    /**
     * 获取布局LayoutID
     */
    protected abstract int getContentID();

}
