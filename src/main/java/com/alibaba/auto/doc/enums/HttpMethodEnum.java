package com.alibaba.auto.doc.enums;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 5:33 下午
 * @description：
 */
public enum HttpMethodEnum {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS");

    private String value;

    HttpMethodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * get http method
     *
     * @param method
     * @return
     */
    public static String getHttpMethod(String method) {
        switch (method) {
            case "RequestMethod.GET":
                return GET.getValue();
            case "RequestMethod.POST":
                return POST.getValue();
            case "RequestMethod.PUT":
                return PUT.getValue();
            case "RequestMethod.DELETE":
                return DELETE.getValue();
            case "RequestMethod.PATCH":
                return PATCH.getValue();
            default:
                return "GET";
        }
    }

    public static String getAllMethod() {
        return StringUtils.join(Arrays.asList(GET.getValue(), POST.getValue(), PUT.getValue(), DELETE.getValue(), PATCH.getValue()), ",");
    }

    public static String getAllMethodWithRequestBody() {
        return StringUtils.join(Arrays.asList(POST.getValue()), ",");
    }
}
