package com.alibaba.auto.doc.model.request;

import java.io.Serializable;

import com.alibaba.auto.doc.constants.SpecialCharacter;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 11:48 上午
 * @description：
 */
public abstract class AbsParam implements Serializable {

    /**
     * param name
     */
    private String name = SpecialCharacter.BLANK;
    /**
     * param type
     */
    private String type;
    /**
     * default value
     */
    private String defaultValue;
    /**
     * comment
     */
    private String comment;
    /**
     * required flag
     */
    private Boolean required;
    /**
     * since
     */
    private String since;
    /**
     * lineNumber
     */
    private Integer lineNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
}
