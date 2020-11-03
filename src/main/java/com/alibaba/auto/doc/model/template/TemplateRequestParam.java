package com.alibaba.auto.doc.model.template;

import com.alibaba.auto.doc.constants.BasicJavaType;
import com.alibaba.auto.doc.constants.SpecialCharacter;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/3 11:07 下午
 * @description：
 */
public class TemplateRequestParam {

    /**
     * param name
     */
    private String name = SpecialCharacter.BLANK;
    /**
     * param type
     */
    private String type;
    /**
     * simple type
     */
    private String simpleType;
    /**
     * simple type for markdown
     */
    private String markdownSimpleType;
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
    /**
     * is enum
     */
    private boolean isEnum;

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

    public String getSimpleType() {
        if (BasicJavaType.BASIC_TYPES.contains(this.type)) {
            int index = type.lastIndexOf(SpecialCharacter.DOT);
            if (index != -1) {
                return type.substring(index + 1);
            }
        }
        return type;
    }

    public void setSimpleType(String simpleType) {
        this.simpleType = simpleType;
    }

    public String getMarkdownSimpleType() {
        String simpleType = this.getSimpleType();
        if(simpleType.indexOf(SpecialCharacter.DOLLAR) != -1) {
            simpleType = simpleType.replace(SpecialCharacter.DOLLAR, SpecialCharacter.MARDDOWN_DOLLAR);
        }
        return simpleType;
    }

    public void setMarkdownSimpleType(String markdownSimpleType) {
        this.markdownSimpleType = markdownSimpleType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        if(StringUtils.isNotEmpty(comment) && comment.contains(SpecialCharacter.NEW_LINE)) {
            return comment.replace(SpecialCharacter.NEW_LINE, SpecialCharacter.SPACE);
        }
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

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean anEnum) {
        isEnum = anEnum;
    }
}
