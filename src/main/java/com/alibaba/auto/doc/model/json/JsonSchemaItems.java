package com.alibaba.auto.doc.model.json;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 11:35 上午
 * @description：
 */
public class JsonSchemaItems {

    @JSONField(name = "$id")
    private String id;

    private List<JsonSchema> anyOf;

    public List<JsonSchema> getAnyOf() {
        return anyOf;
    }

    public void setAnyOf(List<JsonSchema> anyOf) {
        this.anyOf = anyOf;
    }
}
