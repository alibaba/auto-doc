package com.alibaba.auto.doc.model.request;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestParam extends AbsParam {

    /**
     * is enum
     */
    private boolean isEnum;

    /**
     * enum type
     */
    private EnumType enumType;

    /**
     * is collection
     */
    private boolean isCollection;

    /**
     * collection type
     */
    private CollectionType collectionType;

    /**
     * childs
     */
    private List<RequestParam> childs;

    public boolean getEnum() {
        return isEnum;
    }

    public void setEnum(boolean anEnum) {
        isEnum = anEnum;
    }

    public EnumType getEnumType() {
        return enumType;
    }

    public void setEnumType(EnumType enumType) {
        this.enumType = enumType;
    }

    public boolean getCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public List<RequestParam> getChilds() {
        return childs;
    }

    public void setChilds(List<RequestParam> childs) {
        this.childs = childs;
    }
}
