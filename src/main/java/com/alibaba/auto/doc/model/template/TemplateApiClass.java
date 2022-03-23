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
package com.alibaba.auto.doc.model.template;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 10:06 下午
 * @description：
 */
public class TemplateApiClass implements Serializable {

    /**
     * class id (build from classFullName by md5)
     */
    private String id;

    /**
     * class name
     */
    private String className;

    /**
     * class full name
     */
    private String classFullName;

    /**
     * class author
     */
    private String author;

    /**
     * class desc
     */
    private String desc;

    /**
     * class method list
     */
    private List<TemplateApiMethod> apiMethodList;

    /**
     * order
     */
    private int order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<TemplateApiMethod> getApiMethodList() {
        return apiMethodList;
    }

    public void setApiMethodList(List<TemplateApiMethod> apiMethodList) {
        this.apiMethodList = apiMethodList;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
