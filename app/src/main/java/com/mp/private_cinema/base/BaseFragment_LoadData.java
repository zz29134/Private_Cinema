package com.mp.private_cinema.base;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/18
 * 说明
 */

public abstract class BaseFragment_LoadData extends BaseFragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadData();
        }
    }

    protected abstract void lazyLoadData();

}
