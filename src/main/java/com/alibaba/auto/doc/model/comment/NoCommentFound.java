package com.alibaba.auto.doc.model.comment;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 11:00 上午
 * @description：
 */
public abstract class NoCommentFound {

    /**
     * class name
     */
    private String className;
    /**
     * line number
     */
    private Integer lineNumber;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public NoCommentFound(String className, Integer lineNumber) {
        this.className = className;
        this.lineNumber = lineNumber;
    }
}
