package com.alibaba.auto.doc.model;

import java.util.List;

import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.enums.DocLanguage;
import com.alibaba.auto.doc.model.request.RequestParam;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 10:06 下午
 * @description：
 */
public class ApiConfig {

    /**
     * Web server base url
     */
    private String projectEndpoint = "http://localhost:8080";

    /**
     * Set comments check mode
     */
    private boolean isStrict;

    /**
     * source path
     */
    private String srcPath = Constants.PROJECT_CODE_PATH;

    /**
     * output path
     */
    private String outPath;

    /**
     * list of Request headers
     */
    private List<RequestParam> requestHeaderParams;

    /**
     * controller package filters
     */
    private String packageFilters;

    /**
     * language support
     *
     * @since 1.7+
     */
    private DocLanguage language;

    /**
     * @since 1.7.5
     * project name
     */
    private String projectName;

    public String getProjectEndpoint() {
        return projectEndpoint;
    }

    public void setProjectEndpoint(String projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }

    public boolean isStrict() {
        return isStrict;
    }

    public void setStrict(boolean strict) {
        isStrict = strict;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public List<RequestParam> getRequestHeaderParams() {
        return requestHeaderParams;
    }

    public void setRequestHeaderParams(List<RequestParam> requestHeaderParams) {
        this.requestHeaderParams = requestHeaderParams;
    }

    public String getPackageFilters() {
        return packageFilters;
    }

    public void setPackageFilters(String packageFilters) {
        this.packageFilters = packageFilters;
    }

    public DocLanguage getLanguage() {
        return language;
    }

    public void setLanguage(DocLanguage language) {
        this.language = language;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}