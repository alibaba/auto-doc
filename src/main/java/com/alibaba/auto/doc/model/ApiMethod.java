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
package com.alibaba.auto.doc.model;

import com.alibaba.auto.doc.model.request.RequestExample;
import com.alibaba.auto.doc.model.request.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 2:55 下午
 * @description：
 */
public class ApiMethod implements Serializable {

    /**
     * method id (hash value of method signature)
     */
    private String methodId;

    /**
     * method name
     */
    private String methodName;

    /**
     * method signature
     */
    private String methodSignature;

    /**
     * author
     */
    private String author;

    /**
     * method comment
     */
    private String comment;

    /**
     * line number in source code
     */
    private int lineNumber;

    /**
     * value of tag @apiNote
     */
    private String apiNote;

    /**
     * is deprecated
     */
    private boolean deprecated = false;

    /**
     * url
     */
    private String url;

    /**
     * http method
     */
    private String httpMethod;

    /**
     * content type
     */
    private String contentType;

    /**
     * http request headers
     */
    private List<RequestParam> requestHeaderParams;

    /**
     * http request params
     */
    private List<RequestParam> requestParams;

    /**
     * http request body
     */
    private RequestParam requestBody;

    /**
     * response param
     */
    private RequestParam responseBody;

    /**
     * request example
     */
    private RequestExample requestExample;

    /**
     * response example
     */
    private String responseExample;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getApiNote() {
        return apiNote;
    }

    public void setApiNote(String apiNote) {
        this.apiNote = apiNote;
    }

    public boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<RequestParam> getRequestHeaderParams() {
        return requestHeaderParams;
    }

    public void setRequestHeaderParams(List<RequestParam> requestHeaderParams) {
        this.requestHeaderParams = requestHeaderParams;
    }

    public List<RequestParam> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<RequestParam> requestParams) {
        this.requestParams = requestParams;
    }

    public RequestParam getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestParam requestBody) {
        this.requestBody = requestBody;
    }

    public RequestParam getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(RequestParam responseBody) {
        this.responseBody = responseBody;
    }

    public RequestExample getRequestExample() {
        return requestExample;
    }

    public void setRequestExample(RequestExample requestExample) {
        this.requestExample = requestExample;
    }

    public String getResponseExample() {
        return responseExample;
    }

    public void setResponseExample(String responseExample) {
        this.responseExample = responseExample;
    }
}
