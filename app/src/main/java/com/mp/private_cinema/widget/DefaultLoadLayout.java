package com.mp.private_cinema.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mp.private_cinema.R;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/8 <br/>
 * 说明
 */

public class DefaultLoadLayout extends FrameLayout {

    private Context context;
    private boolean hasMoreData = true;
    private ProgressBar progressBar;
    private TextView textView;

    @BindString(R.string.loading_has_data)
    String hasData;
    @BindString(R.string.loading_nomore_data)
    String noMoreData;

    public DefaultLoadLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.load_more_progress, this);
        progressBar = ButterKnife.findById(view, R.id.bottom_progress_bar);
        textView = ButterKnife.findById(view, R.id.bottom_load_text);
    }

    public void setHasData() {
        if (View.GONE == progressBar.getVisibility()) {
            progressBar.setVisibility(View.VISIBLE);
        }
        textView.setText(hasData);
        hasMoreData = true;
    }

    public void setNoData() {
        if (View.VISIBLE == progressBar.getVisibility()) {
            progressBar.setVisibility(View.GONE);
        }
        textView.setText(noMoreData);
        hasMoreData = false;
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }
}
