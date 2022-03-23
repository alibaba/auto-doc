package com.alibaba.auto.doc.model.template;

import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.utils.TypeConvertUtil;

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
        if(StringUtils.isBlank(name)) {
            return SpecialCharacter.HTML_SPACE;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if(StringUtils.isBlank(type)) {
            return SpecialCharacter.HTML_SPACE;
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSimpleType() {
        return TypeConvertUtil.getBasicType(this.type);
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
        if(StringUtils.isBlank(comment)) {
            return SpecialCharacter.HTML_SPACE;
        }
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
