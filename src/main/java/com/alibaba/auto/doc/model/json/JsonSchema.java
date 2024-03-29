/*
 * Copyright 1999-2021 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.auto.doc.model.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 11:25 上午
 * @description：
 */
public class JsonSchema {

    @JSONField(name = "$schema")
    private String schema;

    @JSONField(name = "$id")
    private String id;

    private String type;

    @JSONField(name = "enum")
    private List<Object> enums;

    private String title;

    private String description;

    @JSONField(name = "default")
    private Object defaultValue;

    private List<Object> examples;

    private List<String> required;

    private Map<String, JsonSchema> properties;

    private JsonSchema items;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getEnums() {
        return enums;
    }

    public void setEnums(List<Object> enums) {
        this.enums = enums;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<Object> getExamples() {
        return examples;
    }

    public void setExamples(List<Object> examples) {
        this.examples = examples;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public Map<String, JsonSchema> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, JsonSchema> properties) {
        this.properties = properties;
    }

    public JsonSchema getItems() {
        return items;
    }

    public void setItems(JsonSchema items) {
        this.items = items;
    }
}
