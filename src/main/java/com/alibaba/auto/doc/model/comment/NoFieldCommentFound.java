package com.alibaba.auto.doc.model.comment;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 11:00 上午
 * @description：
 */
public class NoFieldCommentFound extends NoCommentFound {

    /**
     * field name
     */
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public NoFieldCommentFound(String className, Integer lineNumber, String fieldName) {
        super(className, lineNumber);
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("No comment found for field ").append(super.getClassName());
        sb.append(".").append(fieldName);
         sb.append(" in line ").append(super.getLineNumber());
        return sb.toString();
    }
}
