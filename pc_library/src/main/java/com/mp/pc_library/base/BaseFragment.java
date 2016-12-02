package com.mp.pc_library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mp.pc_library.bean.ResponseData;
import com.mp.pc_library.lib_event.StartBrotherEvent;
import com.mp.pc_library.lib_event.StartParentEvent;
import com.mp.pc_library.net.NoHttpUtils;
import com.mp.pc_library.utils.GsonUtil;
import com.orhanobut.logger.Logger;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

import static com.mp.pc_library.utils.LibConstants.URLHead;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseFragment extends SupportFragment {

    /**
     * ButterKnife绑定/解绑器
     */
    private Unbinder unbinder;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentID(), container, false);
        mContext = getContext();
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateView(savedInstanceState);
    }

    /**
     * 打开一个同级的Fragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    /**
     * 打开一个ParentFragment级别的Fragment
     * @param event
     */
    public void startParent(StartParentEvent event) {
        if (getParentFragment() instanceof BaseFragment) {
            ((BaseFragment) getParentFragment()).start(event.targetFragment);
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    /**
     * Get方法获取网络数据请求（String类型）
     *
     * @param cmd      请求路径
     * @param what     请求标志
     * @param map      参数表
     * @param listener 返回监听
     */
    protected void addGetRequest(String cmd, int what, Map<String, String> map, OnResponseListener<String> listener) {
        NoHttpUtils.getInstance().asyncGetStringRequest(URLHead + cmd, what, map, listener);
    }

    /**
     * Post方法获取网络数据请求（String类型）
     *
     * @param cmd      请求路径
     * @param what     请求标志
     * @param map      参数列表
     * @param listener 返回监听
     */
    protected void addPostRequest(String cmd, int what, Map<String, String> map, OnResponseListener<String> listener) {
        NoHttpUtils.getInstance().asyncPostStringRequest(URLHead + cmd, what, map, listener);
    }

    /**
     * 下载文件请求
     *
     * @param cmd      请求路径
     * @param what     请求标志
     * @param map      参数列表
     * @param filePath 存储位置
     * @param listener 下载监听
     */
    protected void addDownLoadFileRequest(String cmd, int what, Map<String, String> map, String filePath, DownloadListener listener) {
        NoHttpUtils.getInstance().asyncDownLoadFileRequest(URLHead + cmd, what, map, filePath, listener);
    }

    /**
     * 分块级上传文件 提交表单数据
     *
     * @param cmd      请求路径
     * @param what     网络请求标识
     * @param forMap   网络请求提交表单数据
     * @param fileMap  网络请求提交上传文件
     * @param listener 网络请求监听
     */
    protected void addUpLoadFileRequest(String cmd, int what, Map<String, String> forMap, Map<String, String> fileMap, OnResponseListener<String> listener) {
        NoHttpUtils.getInstance().asyncUpLoadFile(URLHead + cmd, what, forMap, fileMap, listener);
    }

    /**
     * 取消一般请求
     * @param what
     */
    protected void cancelRequest(int what) {
        NoHttpUtils.getInstance().cancelRequestBySign(what);
    }

    /**
     * 取消下载请求
     * @param what
     */
    protected void cancelDownLoad(int what) {
        NoHttpUtils.getInstance().cancelDownLoadBySign(what);
    }

    /**
     * 取消一般请求
     * @param what
     */
    protected void cancelUpLoad(int what) {
        NoHttpUtils.getInstance().cancelUpLoadBySign(what);
    }

    /**
     * 获取结果Code
     * @param response
     * @return
     */
    protected String getResultCode(Response response) {

        return GsonUtil.fromJson(response.get().toString(), ResponseData.class).getCode();
    }

    /**
     * 获取结果State
     * @param response
     * @return
     */
    protected String getResultState(Response response) {
        return GsonUtil.fromJson(response.get().toString(), ResponseData.class).getState();
    }

    /**
     * 获取结果Content,String类型
     * @param response
     * @return
     */
    protected String getResultContentString(Response response) {
        Logger.json(GsonUtil.fromJson(response.get().toString(), ResponseData.class).getContent().toString());
        return GsonUtil.fromJson(response.get().toString(), ResponseData.class).getContent().toString();
    }

    /**
     * 获取结果Content,JsonObject类型
     * @param response
     * @return
     */
    protected <T> T getResultContentJsonObject(Response response, Class<T> cls) {
        return GsonUtil.fromJson(getResultContentString(response), cls);
    }

    /**
     * 获取结果Content,JsonArray类型
     * @param response
     * @return
     */
    protected <T> List<T> getResultContentJsonArray(Response response, Class<T> cls) {
        return GsonUtil.fromJsonArray(getResultContentString(response), cls);
    }

    protected abstract int getContentID();

    protected abstract void onCreateView(Bundle savedInstanceState);


}
