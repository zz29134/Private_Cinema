package com.mp.private_cinema.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.LoadingLayoutBase;
import com.mp.private_cinema.R;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 创建人 Zhangzhe <br/>
 * 日期   2016/12/8 <br/>
 * 说明
 */

public class DefaultRefreshLayout extends LoadingLayoutBase {

    private FrameLayout mInnerLayout;
    private TextView mHeaderText;
    private TextView mSubHeaderText;
    private ImageView mImage;

    private CharSequence mPullLabel;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;

    private Animation mAnimRotate;

    public DefaultRefreshLayout(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.widget_default_header, this);
        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mSubHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        mImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh);

        LayoutParams lp = (LayoutParams) mInnerLayout.getLayoutParams();
        lp.gravity = Gravity.BOTTOM;

        // Load in labels
        mPullLabel = context.getString(R.string.header_pull_label);
        mRefreshingLabel = context.getString(R.string.header_refreshing_label);
        mReleaseLabel = context.getString(R.string.header_release_label);

        mAnimRotate = AnimationUtils.loadAnimation(context, R.anim.default_refresh_header);

        reset();
    }

    // 获取"加载头部"高度
    @Override
    public int getContentSize() {
        return mInnerLayout.getHeight();
    }

    // 开始下拉时的回调
    @Override
    public void pullToRefresh() {
        mHeaderText.setText(mPullLabel);
    }

    // "加载头部"完全显示时的回调
    @Override
    public void releaseToRefresh() {
        mHeaderText.setText(mReleaseLabel);
    }

    // 下拉拖动时的回调
    @Override
    public void onPull(float scaleOfLayout) {
        scaleOfLayout = scaleOfLayout > 1.0f ? 1.0f : scaleOfLayout;
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mImage, "rotation", 250 * scaleOfLayout),
                ObjectAnimator.ofFloat(mImage, "scaleX", 1.0f * scaleOfLayout),
                ObjectAnimator.ofFloat(mImage, "alpha", 1 * scaleOfLayout)
        );

    }

    // 释放后刷新时的回调
    @Override
    public void refreshing() {
        mHeaderText.setText(mRefreshingLabel);
        mImage.startAnimation(mAnimRotate);
    }

    @Override
    public void reset() {
        mImage.clearAnimation();
    }

    @Override
    public void setPullLabel(CharSequence pullLabel) {
        mPullLabel = pullLabel;
    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {
        mRefreshingLabel = refreshingLabel;
    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {
        mReleaseLabel = releaseLabel;
    }

}
