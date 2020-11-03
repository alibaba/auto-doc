package com.alibaba.auto.doc.enums;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 5:33 下午
 * @description：
 */
public enum DocLanguage {
    ENGLISH("en-US"),
    CHINESE("zh-CN");

    public String code;

    DocLanguage(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
