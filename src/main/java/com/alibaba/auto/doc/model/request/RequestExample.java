package com.alibaba.auto.doc.model.request;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestExample {

    /**
     * method
     */
    private String method;

    /**
     * content type
     */
    private String contentType;

    /**
     * url
     */
    private String url;

    /**
     * json body
     */
    private String jsonBody;

    /**
     * header
     */
    private String header;

    /**
     * form data
     */
    private String formData;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String buildCurlString() {
        return "";
    }
}
