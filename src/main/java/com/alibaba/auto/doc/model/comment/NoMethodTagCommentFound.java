/*
 * Copyright 1999-2021 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
