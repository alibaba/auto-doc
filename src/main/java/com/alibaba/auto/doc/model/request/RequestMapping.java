package com.alibaba.auto.doc.model.request;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestMapping {

    /**
     * url
     */
    private List<String> urls;

    /**
     * request method type
     */
    private String methodType;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public RequestMapping(List<String> urls, String methodType) {
        this.urls = urls;
        this.methodType = methodType;
    }
}
