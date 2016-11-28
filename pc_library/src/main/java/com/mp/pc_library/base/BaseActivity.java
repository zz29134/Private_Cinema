package com.mp.pc_library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.mp.pc_library.app.AppManager;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseActivity extends SupportActivity {

    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
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

    @Override
    public void onBackPressedSupport() {
        doGoBackOrExit();
    }

    /**
     * 手动返回（返回按钮等类似行为）
     */
    public void onGoBack() {
        doGoBackOrExit();
    }

    /**
     * 退出当前界面<br/>
     * 判断：<br/>
     * 1、Fragment栈中是否有其他Fragment<br/>
     *    如还有其他Fragment，则结束并出栈当前Fragment<br/>
     *    如无其他Fragment，则判断当前Activity栈中是否有其他Activity<br/>
     * 2、Activity栈中是否有其他Activity<br/>
     *    如还有其他Activity，则结束并出栈当前Activity<br/>
     *    如无其他Activity，则进入两秒退出APP倒计时并提示用户
     */
    private void doGoBackOrExit() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (AppManager.getInstance().getActivityStackCount() > 1) {
                AppManager.getInstance().killActivity(this);
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序",
                            Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    AppManager.getInstance().AppExit(this);
                }
            }
        }
    }
}
