package com.alibaba.auto.doc.model;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 10:06 下午
 * @description：
 */
public class ApiClass implements Comparable<ApiClass> {

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
    private List<ApiMethod> apiMethodList;

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

    public List<ApiMethod> getApiMethodList() {
        return apiMethodList;
    }

    public void setApiMethodList(List<ApiMethod> apiMethodList) {
        this.apiMethodList = apiMethodList;
    }

    @Override
    public int compareTo(ApiClass apiClass) {
        return classFullName.compareTo(apiClass.getClassFullName());
    }
}
