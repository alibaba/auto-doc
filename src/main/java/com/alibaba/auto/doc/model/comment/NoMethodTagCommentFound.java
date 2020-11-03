package com.alibaba.auto.doc.model.comment;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 11:00 上午
 * @description：
 */
public class NoMethodTagCommentFound extends NoCommentFound {

    /**
     * method name
     */
    private String methodName;
    /**
     * tag name
     */
    private String tagName;


    public NoMethodTagCommentFound(String className, Integer lineNumber, String methodName, String tagName) {
        super(className, lineNumber);
        this.methodName = methodName;
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("No comment found for method tag ").append(super.getClassName());
        sb.append(".").append(methodName);
        sb.append(" @" + tagName);
        sb.append(" in line ").append(super.getLineNumber());
        return sb.toString();
    }
}
