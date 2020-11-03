package com.alibaba.auto.doc.model.template;

import java.util.List;

import com.alibaba.auto.doc.model.request.RequestParam;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 10:06 下午
 * @description：
 */
public class TemplateApiMethod {

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
    private List<TemplateRequestParam> requestHeaderParams;

    /**
     * http request params
     */
    private List<TemplateRequestParam> requestParams;

    /**
     * http request body
     */
    private List<TemplateRequestParam> requestBodyParams;

    /**
     * response param
     */
    private List<TemplateRequestParam> responseBodyParams;

    /**
     * request example
     */
    private String requestExample;

    /**
     * response example
     */
    private String responseExample;

    /**
     * order
     */
    private Integer order;

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

    public List<TemplateRequestParam> getRequestHeaderParams() {
        return requestHeaderParams;
    }

    public void setRequestHeaderParams(List<TemplateRequestParam> requestHeaderParams) {
        this.requestHeaderParams = requestHeaderParams;
    }

    public List<TemplateRequestParam> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<TemplateRequestParam> requestParams) {
        this.requestParams = requestParams;
    }

    public List<TemplateRequestParam> getRequestBodyParams() {
        return requestBodyParams;
    }

    public void setRequestBodyParams(List<TemplateRequestParam> requestBodyParams) {
        this.requestBodyParams = requestBodyParams;
    }

    public List<TemplateRequestParam> getResponseBodyParams() {
        return responseBodyParams;
    }

    public void setResponseBodyParams(List<TemplateRequestParam> responseBodyParams) {
        this.responseBodyParams = responseBodyParams;
    }

    public String getRequestExample() {
        return requestExample;
    }

    public void setRequestExample(String requestExample) {
        this.requestExample = requestExample;
    }

    public String getResponseExample() {
        return responseExample;
    }

    public void setResponseExample(String responseExample) {
        this.responseExample = responseExample;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
