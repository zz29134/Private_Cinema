package com.mp.private_cinema.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

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

    private ImageView iv;
    private ValueAnimator ivAnim;

    @Override
    public boolean onStartPull(float distance, int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            iv.setRotation(0f);
        } else if (lastState == PullToRefreshRecyclerView.STATE_PULLING) {
            float scaleOfLayout = distance / mRefreshView.getHeight();
            ObjectAnimator translate = ObjectAnimator.ofFloat(iv, "Y", mRefreshView.getHeight() - iv.getHeight() / 2,
                    (mRefreshView.getHeight() - iv.getHeight()) / 2).setDuration(300);
            translate.setCurrentPlayTime((long) (scaleOfLayout * 300));
            //缩放动画
            ObjectAnimator animPX = ObjectAnimator.ofFloat(iv, "scaleX", 0, 1).setDuration(300);
            animPX.setCurrentPlayTime((long) (scaleOfLayout * 300));
            ObjectAnimator animPY = ObjectAnimator.ofFloat(iv, "scaleY", 0, 1).setDuration(300);
            animPY.setCurrentPlayTime((long) (scaleOfLayout * 300));
        } else if (lastState == PullToRefreshRecyclerView.STATE_RELEASE_TO_REFRESH) {
            startArrowAnim(0);
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
    public boolean onReleaseToRefresh(float distance, int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            iv.setRotation(-180f);
        } else if (lastState == PullToRefreshRecyclerView.STATE_PULLING) {
            startArrowAnim(-180f);
        }
        return true;
    }

    @Override
    public void onStartRefreshing() {
        startLoadingAnim();
    }

    @Override
    public View getRefreshView(Context context, RecyclerView recyclerView) {
        mRefreshView = LayoutInflater.from(context).inflate(R.layout.widget_recyclerview_headerview, recyclerView, false);
        iv = (ImageView) mRefreshView.findViewById(R.id.pull_to_refresh);
        return mRefreshView;
    }

    private void startArrowAnim(float rotation) {
        if (ivAnim != null) {
            ivAnim.cancel();
        }
//        ViewHelper.setPivotY(iv, iv.getMeasuredHeight() / 2);   // 设置中心点
//        ViewHelper.setPivotX(iv, iv.getMeasuredWidth() / 2);
        float startRotation = iv.getRotation();
        ivAnim = ObjectAnimator.ofFloat(startRotation, rotation).setDuration(200);
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
        ivAnim = ObjectAnimator.ofFloat(0, 360).setDuration(1000);
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
}
