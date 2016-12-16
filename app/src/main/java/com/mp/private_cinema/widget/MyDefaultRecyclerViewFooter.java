package com.mp.private_cinema.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mp.private_cinema.R;
import com.mrw.wzmrecyclerview.AutoLoad.AutoLoadFooterCreator;

import butterknife.ButterKnife;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/13 <br/>
 * 说明
 */

public class MyDefaultRecyclerViewFooter extends AutoLoadFooterCreator {

    private View loadView;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected View getLoadView(Context context, RecyclerView recyclerView) {
        initView(context, recyclerView);
        progressBar.setVisibility(View.VISIBLE);
        textView.setText(R.string.loading_has_data);
        return loadView;
    }

    @Override
    protected View getNoMoreView(Context context, RecyclerView recyclerView) {
        return null;
    }

    private void initView(Context context, RecyclerView recyclerView) {
        if (null == loadView) {
            loadView = LayoutInflater.from(context).inflate(R.layout.widget_recyclerview_footerview, recyclerView, false);
            progressBar = ButterKnife.findById(loadView, R.id.bottom_progress_bar);
            textView = ButterKnife.findById(loadView, R.id.bottom_load_text);
        }
    }
}
