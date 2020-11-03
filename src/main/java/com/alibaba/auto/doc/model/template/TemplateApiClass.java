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
