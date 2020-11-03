package com.alibaba.auto.doc.model.comment;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 11:00 上午
 * @description：
 */
public class NoClassCommentFound extends NoCommentFound {

    public NoClassCommentFound(String className, Integer lineNumber) {
        super(className, lineNumber);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("No comment found for ").append(super.getClassName());
        sb.append(" in line ").append(super.getLineNumber());
        return sb.toString();
    }
}
