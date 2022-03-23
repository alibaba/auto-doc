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
