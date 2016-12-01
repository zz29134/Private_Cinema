package com.mp.pc_library.net;

import android.os.Environment;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

import java.io.File;
import java.util.Map;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/21
 * 说明
 */

public class NoHttpUtils {

    private static NoHttpUtils mInstance;
    private static RequestQueue mRequestQueue;
    private static DownloadQueue mDownloadQueue;

    public NoHttpUtils() {
        getNoHttpRequestQueue();
    }

    public static NoHttpUtils getInstance () {
        if (null == mInstance) {
            synchronized (NoHttpUtils.class) {
                if (null == mInstance) {
                    mInstance = new NoHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取普通请求队列
     *
     * @param
     * @return
     */
    private static RequestQueue getNoHttpRequestQueue() {
        if (null == mRequestQueue) {
            synchronized (NoHttpUtils.class) {
                if (null == mRequestQueue) {
                    mRequestQueue = NoHttp.newRequestQueue();
                }
            }
        }
        return mRequestQueue;
    }

    /**
     * 获取下载文件队列
     *
     * @param
     * @return
     */
    private static DownloadQueue getNoHttpDownloadQueue() {
        if (null == mDownloadQueue) {
            synchronized (NoHttpUtils.class) {
                if (null == mDownloadQueue) {
                    mDownloadQueue = NoHttp.newDownloadQueue();
                }
            }
        }
        return mDownloadQueue;
    }

    /**
     * Post请求，返回的数据类型为String
     *
     * @param url      请求服务器地址
     * @param what     请求服务标识
     * @param map      请求参数添加
     * @param listener 响应结果监听
     */
    public void asyncPostStringRequest(String url, int what,
                                       Map<String, String> map,
                                       OnResponseListener<String> listener) {
        /**
         * 取消队列中已开始的请求
         */
        mRequestQueue.cancelBySign(what);
        /**
         * 创建请求对象
         */
        Request<String> request =
                NoHttp.createStringRequest(url, RequestMethod.POST);

        /**
         * 添加请求参数
         */
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    request.add(entry.getKey(), entry.getValue());
                }
            }
        }

        /**
         * 设置取消请求标识
         */
        request.setCancelSign(what);

        /**
         * what: 当多个请求同时使用同一个OnResponseListener时用来区分请求, 类似handler的what一样
         * request: 请求对象
         * onResponseListener 回调对象，接受请求结果
         */
        mRequestQueue.add(what, request, listener);
    }

    /**
     * Get请求，返回的数据类型为String
     *
     * @param url      请求服务器地址
     * @param what     请求服务标识
     * @param map      请求参数添加
     * @param listener 响应结果监听
     */
    public void asyncGetStringRequest(String url, int what,
                                      Map<String, String> map,
                                      OnResponseListener<String> listener) {
        /**
         * 取消队列中已开始的请求
         */
        mRequestQueue.cancelBySign(what);
        /**
         * 创建请求对象
         */
        Request<String> request =
                NoHttp.createStringRequest(url, RequestMethod.GET);

        /**
         * 添加请求参数
         */
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    request.add(entry.getKey(), entry.getValue());
                }
            }
        }

        /**
         * 设置取消请求标识
         */
        request.setCancelSign(what);

        /**
         * what: 当多个请求同时使用同一个OnResponseListener时用来区分请求, 类似handler的what一样
         * request: 请求对象
         * onResponseListener 回调对象，接受请求结果
         */
        mRequestQueue.add(what, request, listener);
    }

    /**
     * @param url      请求服务器链接
     * @param what     请求服务标识
     * @param map      请求参数封装
     * @param filePath 下载文件的保存路径与文件名
     * @param listener 下载监听
     */
    public void asyncDownLoadFileRequest(String url, int what,
                                     Map<String, String> map,
                                     String filePath, DownloadListener listener) {

        /**
         * url 下载服务器地址
         * RequestMethod.POST 发送请求方式
         * Environment.getExternalStorageDirectory().getPath() 文件下载保存路径
         * filePath 文件下载保存名称
         * true 支持断点下载
         * false 不覆盖已有的相同文件名称的文件
         */
        DownloadRequest downloadRequest
                = NoHttp.createDownloadRequest(url,
                RequestMethod.POST,
                Environment.getExternalStorageDirectory().getPath(),
                filePath, true, false);

        /**
         * 添加请求参数
         */
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    downloadRequest.add(entry.getKey(), entry.getValue());
                }
            }
        }
        /**
         * 设置请求标识
         */
        downloadRequest.setCancelSign(what);
        /**
         * 下载
         */
        mDownloadQueue.add(what, downloadRequest, listener);

    }

    /**
     * 分块级上传文件 提交表单数据
     *
     * @param url
     * @param what     网络请求标识
     * @param forMap   网络请求提交表单数据
     * @param fileMap  网络请求提交上传文件
     * @param listener 网络请求标识
     */
    public void asyncUpLoadFile(String url, int what, Map<String, String> forMap, Map<String, String> fileMap, OnResponseListener<String> listener) {
        mRequestQueue.cancelBySign(what);
        Request<String> stringRequest = NoHttp.createStringRequest(url, RequestMethod.POST);
        /**
         * 添加上传文件信息
         */
        if (fileMap != null && !fileMap.isEmpty()) {
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                if (entry.getKey() != null) {
                    File file = new File(entry.getValue());
                    String fileName = file.getName();
                    stringRequest.addHeader("Content-Disposition", "form-data;name=\"" + fileName + "\";filename=\"" + fileName + "\"");
                    stringRequest.add(entry.getKey(), file);

                }
            }
        }
        /**
         * 添加请求参数
         */
        if (forMap != null && !forMap.isEmpty()) {
            for (Map.Entry<String, String> entry : forMap.entrySet()) {
                if (entry.getKey() != null) {
                    stringRequest.add(entry.getKey(), entry.getValue());
                }
            }
        }

        /**
         * 网络取消标识
         */

        stringRequest.setCancelSign(what);
        /**
         * 发起请求
         */
        mRequestQueue.add(what, stringRequest, listener);

    }

    /**
     * 取消一般请求
     * @param what
     */
    public void cancelRequestBySign(int what) {
        if (null != mRequestQueue) {
            mRequestQueue.cancelBySign(what);
        }
    }

    /**
     * 取消下载请求
     * @param what
     */
    public void cancelDownLoadBySign(int what) {
        if (null != mDownloadQueue) {
            mDownloadQueue.cancelBySign(what);
        }
    }

    /**
     * 取消上传请求
     * @param what
     */
    public void cancelUpLoadBySign(int what) {
        if (null != mRequestQueue) {
            mRequestQueue.cancelBySign(what);
        }
    }

}
