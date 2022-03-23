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
package com.alibaba.auto.doc.config;

import java.util.List;

import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.enums.DocLanguageEnum;
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
    private boolean isStrict = false;

    /**
     * source path
     */
    private String srcPath = Constants.DEFAULT_SOURCE_CODE_PATH;

    /**
     * output path
     */
    private String outPath = Constants.DEFAULT_DOC_OUT_PATH;

    /**
     * list of Request headers
     */
    private List<RequestParam> requestHeaderParams;

    /**
     * packageInclude
     */
    private String packageInclude;

    /**
     * classInclude
     */
    private String classInclude;

    /**
     * classExclude
     */
    private String classExclude;

    /**
     * remove regular expression in url
     */
    private Boolean urlRemoveRegExp = false;

    /**
     * choose the first url if multi
     */
    private Boolean urlFirstIfMulti = false;

    /**
     * language support
     */
    private DocLanguageEnum language;


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

    public String getPackageInclude() {
        return packageInclude;
    }

    public void setPackageInclude(String packageInclude) {
        this.packageInclude = packageInclude;
    }

    public String getClassInclude() {
        return classInclude;
    }

    public void setClassInclude(String classInclude) {
        this.classInclude = classInclude;
    }

    public String getClassExclude() {
        return classExclude;
    }

    public void setClassExclude(String classExclude) {
        this.classExclude = classExclude;
    }

    public Boolean getUrlRemoveRegExp() {
        return urlRemoveRegExp;
    }

    public void setUrlRemoveRegExp(Boolean urlRemoveRegExp) {
        this.urlRemoveRegExp = urlRemoveRegExp;
    }

    public Boolean getUrlFirstIfMulti() {
        return urlFirstIfMulti;
    }

    public void setUrlFirstIfMulti(Boolean urlFirstIfMulti) {
        this.urlFirstIfMulti = urlFirstIfMulti;
    }

    public DocLanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(DocLanguageEnum language) {
        this.language = language;
    }
}