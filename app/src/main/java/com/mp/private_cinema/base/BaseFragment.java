package com.mp.private_cinema.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseFragment extends SupportFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentID(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onCreateView(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    protected abstract int getContentID();

    protected abstract void onCreateView(Bundle savedInstanceState);
}
