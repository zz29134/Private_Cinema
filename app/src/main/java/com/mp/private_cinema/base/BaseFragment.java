package com.mp.private_cinema.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mp.private_cinema.net.NoHttpUtils;
import com.mp.private_cinema.utils.Constants;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.rest.OnResponseListener;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Zhangzhe on 2016/11/16.
 */

public abstract class BaseFragment extends SupportFragment {

    /**
     * ButterKnife绑定/解绑器
     */
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentID(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateView(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        NoHttpUtils.getInstance().asyncGetStringRequest(Constants.URLHead + cmd, what, map, listener);
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
        NoHttpUtils.getInstance().asyncPostStringRequest(Constants.URLHead + cmd, what, map, listener);
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
        NoHttpUtils.getInstance().asyncDownLoadFileRequest(Constants.URLHead + cmd, what, map, filePath, listener);
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
        NoHttpUtils.getInstance().asyncUpLoadFile(Constants.URLHead + cmd, what, forMap, fileMap, listener);
    }

    protected abstract int getContentID();

    protected abstract void onCreateView(Bundle savedInstanceState);


}
