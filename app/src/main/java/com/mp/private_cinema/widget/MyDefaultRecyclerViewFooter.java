package com.mp.private_cinema.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mp.private_cinema.R;
import com.mrw.wzmrecyclerview.PullToLoad.LoadFooterCreator;
import com.mrw.wzmrecyclerview.PullToLoad.PullToLoadRecyclerView;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/13 <br/>
 * 说明
 */

public class MyDefaultRecyclerViewFooter extends LoadFooterCreator {

    private View mLoadView;
    private ImageView iv;
    private TextView tv;


    private int rotationDuration = 200;

    private int loadingDuration = 1000;
    private ValueAnimator ivAnim;


    @Override
    public boolean onStartPull(float distance, int lastState) {
        if (lastState == PullToLoadRecyclerView.STATE_DEFAULT) {
            iv.setImageResource(R.drawable.arrow_down);
            iv.setRotation(-180f);
            tv.setText("上拉加载");
        } else if (lastState == PullToLoadRecyclerView.STATE_RELEASE_TO_LOAD) {
            startArrowAnim(-180f);
            tv.setText("上拉加载");
        }
        return true;
    }

    @Override
    public boolean onReleaseToLoad(float distance, int lastState) {
        if (lastState == PullToLoadRecyclerView.STATE_DEFAULT ) {
            iv.setImageResource(R.drawable.arrow_down);
            iv.setRotation(0f);
            tv.setText("松手立即加载");
        } else if (lastState == PullToLoadRecyclerView.STATE_PULLING) {
            startArrowAnim(0f);
            tv.setText("松手立即加载");
        }
        return true;
    }

    @Override
    public void onStartLoading() {
        iv.setImageResource(R.drawable.loading);
        startLoadingAnim();
        tv.setText("正在加载...");
    }

    @Override
    public void onStopLoad() {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
    }

    @Override
    public View getLoadView(Context context, RecyclerView recyclerView) {
        mLoadView = LayoutInflater.from(context).inflate(R.layout.layout_ptr_ptl,recyclerView,false);
        iv = (ImageView) mLoadView.findViewById(R.id.iv);
        tv = (TextView) mLoadView.findViewById(R.id.tv);
        return mLoadView;
    }


    private void startArrowAnim(float roration) {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
        float startRotation = iv.getRotation();
        ivAnim = ObjectAnimator.ofFloat(startRotation, roration).setDuration(rotationDuration);
        ivAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setRotation((Float) animation.getAnimatedValue());
            }
        });
        ivAnim.start();
    }

    private void startLoadingAnim() {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
        ivAnim = ObjectAnimator.ofFloat(0,360).setDuration(loadingDuration);
        ivAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setRotation((Float) animation.getAnimatedValue());
            }
        });
        ivAnim.setRepeatMode(ObjectAnimator.RESTART);
        ivAnim.setRepeatCount(ObjectAnimator.INFINITE);
        ivAnim.setInterpolator(new LinearInterpolator());
        ivAnim.start();
    }

    //    private View loadView;
//    private ProgressBar progressBar;
//    private TextView textView;
//
//    @Override
//    protected View getLoadView(Context context, RecyclerView recyclerView) {
//        initView(context, recyclerView);
//        progressBar.setVisibility(View.VISIBLE);
//        textView.setText(R.string.loading_has_data);
//        return loadView;
//    }
//
//    @Override
//    protected View getNoMoreView(Context context, RecyclerView recyclerView) {
//        return null;
//    }
//
//    private void initView(Context context, RecyclerView recyclerView) {
//        if (null == loadView) {
//            loadView = LayoutInflater.from(context).inflate(R.layout.widget_recyclerview_footerview, recyclerView, false);
//            progressBar = ButterKnife.findById(loadView, R.id.bottom_progress_bar);
//            textView = ButterKnife.findById(loadView, R.id.bottom_load_text);
//        }
//    }
}
