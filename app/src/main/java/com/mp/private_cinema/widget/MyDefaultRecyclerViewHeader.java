package com.mp.private_cinema.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mp.private_cinema.R;
import com.mrw.wzmrecyclerview.PullToRefresh.PullToRefreshRecyclerView;
import com.mrw.wzmrecyclerview.PullToRefresh.RefreshHeaderCreator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/13 <br/>
 * 说明
 */

public class MyDefaultRecyclerViewHeader extends RefreshHeaderCreator {

    private View mRefreshView;
    private ImageView iv;
    private TextView tv;

    private int rotationDuration = 200;

    private int loadingDuration = 1000;
    private ValueAnimator ivAnim;


    @Override
    public boolean onStartPull(float distance,int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT ) {
            iv.setImageResource(R.drawable.arrow_down);
            iv.setRotation(0f);
            tv.setText("下拉刷新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_RELEASE_TO_REFRESH) {
            startArrowAnim(0);
            tv.setText("下拉刷新");
        }
        return true;
    }

    @Override
    public void onStopRefresh() {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
    }


    @Override
    public boolean onReleaseToRefresh(float distance,int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT ) {
            iv.setImageResource(R.drawable.arrow_down);
            iv.setRotation(-180f);
            tv.setText("松手立即刷新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_PULLING) {
            startArrowAnim(-180f);
            tv.setText("松手立即刷新");
        }
        return true;
    }

    @Override
    public void onStartRefreshing() {
        iv.setImageResource(R.drawable.loading);
        startLoadingAnim();
        tv.setText("正在刷新...");
    }

    @Override
    public View getRefreshView(Context context, RecyclerView recyclerView) {
        mRefreshView = LayoutInflater.from(context).inflate(R.layout.layout_ptr_ptl,recyclerView,false);
        iv = (ImageView) mRefreshView.findViewById(R.id.iv);
        tv = (TextView) mRefreshView.findViewById(R.id.tv);
        return mRefreshView;
    }

    private void startArrowAnim(float roration) {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
        float startRotation = iv.getRotation();
        ivAnim = ObjectAnimator.ofFloat(startRotation,roration).setDuration(rotationDuration);
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

//    private Context context;
//    private View refreshView;
//    private ImageView imageView;
//
//    public MyDefaultRecyclerViewHeader(Context context) {
//        this.context = context;
//    }
//
//    /**
//     * 下拉
//     *
//     * @param distance  距离
//     * @param lastState
//     * @return 下拉距离，返回true表示可以继续下拉
//     */
//    @Override
//    public boolean onStartPull(float distance, int lastState) {
//        float refreshViewHeight = refreshView.getHeight();
//        float scaleOfLayout = distance / refreshViewHeight;
//        Log.e("distance:" + distance, "lastState:" + lastState);
//        scaleOfLayout = scaleOfLayout > 1.0f ? 1.0f : scaleOfLayout;
//
//        //缩放动画
//        ViewHelper.setPivotY(imageView, imageView.getMeasuredHeight());   // 设置中心点
//        ViewHelper.setPivotX(imageView, imageView.getMeasuredWidth() / 2);
//        ObjectAnimator animPX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 1).setDuration(300);
//        animPX.setCurrentPlayTime((long) (scaleOfLayout * 300));
//        ObjectAnimator animPY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 1).setDuration(300);
//        animPY.setCurrentPlayTime((long) (scaleOfLayout * 300));
//
//        return true;
//    }
//
//    /**
//     * 松手刷新
//     *
//     * @param distance  距离
//     * @param lastState
//     * @return 下拉距离，返回true表示可以继续下拉
//     */
//    @Override
//    public boolean onReleaseToRefresh(float distance, int lastState) {
//
//        return true;
//    }
//
//    /**
//     * 开始刷新
//     */
//    @Override
//    public void onStartRefreshing() {
//        imageView.clearAnimation();
//        imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.default_refresh_header));
//    }
//
//    @Override
//    public View getRefreshView(Context context, RecyclerView recyclerView) {
//        refreshView = LayoutInflater.from(context).inflate(R.layout.widget_recyclerview_headerview, recyclerView, false);
//        imageView = ButterKnife.findById(refreshView, R.id.pull_to_refresh);
//        return refreshView;
//    }
//
//    /**
//     * 刷新结束
//     */
//    @Override
//    public void onStopRefresh() {
//        imageView.clearAnimation();
//    }
}
