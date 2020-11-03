package com.alibaba.auto.doc.model.request;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestBodyParam extends AbsParam {

    /**
     * is enum
     */
    private Boolean isEnum;

    /**
     * is collection
     */
    private Boolean isCollection;

    /**
     * childs
     */
    private List<RequestBodyParam> childs;

    public boolean getIsEnum() {
        return isEnum;
    }

    public void setIsEnum(boolean isEnum) {
        this.isEnum = isEnum;
    }

    public boolean getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }

    public List<RequestBodyParam> getChilds() {
        return childs;
    }

    public void setChilds(List<RequestBodyParam> childs) {
        this.childs = childs;
    }
}
