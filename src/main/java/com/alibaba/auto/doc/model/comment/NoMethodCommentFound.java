package com.alibaba.auto.doc.model.comment;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 11:00 上午
 * @description：
 */
public class NoMethodCommentFound extends NoCommentFound {

    /**
     * method name
     */
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public NoMethodCommentFound(String className, Integer lineNumber, String methodName) {
        super(className, lineNumber);
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("No comment found for method ").append(super.getClassName());
        sb.append(".").append(methodName);
        sb.append(" in line ").append(super.getLineNumber());
        return sb.toString();
    }
}
