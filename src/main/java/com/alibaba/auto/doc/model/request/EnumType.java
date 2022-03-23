package com.alibaba.auto.doc.model.request;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/9 11:58 上午
 * @description：
 */
public class EnumType {

    /**
     * type
     */
    private String type;

    /**
     * enum values
     */
    private List<Object> values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
