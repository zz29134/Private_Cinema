package com.mp.private_cinema;

import android.app.Application;

import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import org.litepal.LitePal;

/**
 * Created by Zhangzhe on 2016/11/15.
 */

public class PC_Application extends Application {

    private static PC_Application _mInstance;

    public static PC_Application getInstance() {
        return _mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _mInstance = this;
        initTools();
    }

    private void initTools() {
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor())  // 使用OkHttp做网络层。
        );
        Logger.setDebug(true); // 开启NoHttp调试模式。
        LitePal.initialize(this);
    }
}
