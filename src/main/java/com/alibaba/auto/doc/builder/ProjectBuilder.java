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
package com.alibaba.auto.doc.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.alibaba.auto.doc.cache.GlobalFieldCache;
import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.enums.DocLanguageEnum;
import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;
import com.alibaba.auto.doc.model.ApiClass;
import com.alibaba.auto.doc.config.ApiConfig;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.utils.JavaClassUtil;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 9:52 下午
 * @description：
 */
public class ProjectBuilder {

    private static final Logger log = LoggerFactory.getLogger(ProjectBuilder.class);

    private static JavaProjectBuilder javaProjectBuilder;

    private static ApiConfig apiConfig;

    /**
     * init
     *
     * @param apiConfig
     */
    public static void init(ApiConfig apiConfig) {
        ProjectBuilder.apiConfig = apiConfig;
        javaProjectBuilder = new JavaProjectBuilder();

        // 1.apiConfig not null
        if (null == apiConfig) {
            throw new AutoDocException(ErrorCodes.AD_CONFIG_IS_NULL, "ApiConfig can not be null.");
        }

        // 2.set language
        if (null != apiConfig.getLanguage()) {
            System.setProperty(Constants.DOC_LANGUAGE, apiConfig.getLanguage().getCode());
        } else {
            apiConfig.setLanguage(DocLanguageEnum.CHINESE);
            System.setProperty(Constants.DOC_LANGUAGE, DocLanguageEnum.CHINESE.getCode());
        }

        // 3.source path check
        if (StringUtils.isBlank(apiConfig.getSrcPath())) {
            apiConfig.setSrcPath(Constants.DEFAULT_SOURCE_CODE_PATH);
        }
        File file = new File(apiConfig.getSrcPath());
        if (file.exists() && file.isDirectory()) {
            javaProjectBuilder.addSourceTree(file);
        } else {
            throw new AutoDocException(ErrorCodes.AD_CONFIG_SOURCE_PATH_ILLEGAL, "source path [" + apiConfig.getSrcPath() + "] not a folder or not exist");
        }

        // 4.out path check
        if (StringUtils.isEmpty(apiConfig.getOutPath())) {
            throw new AutoDocException(ErrorCodes.AD_CONFIG_OUT_PATH_IS_EMPTY);
        }

        // 5.init javaProjectBuilder
        javaProjectBuilder.setEncoding(Constants.DEFAULT_CHARSET);

        // 6.remove cache
        GlobalFieldCache.empty();
    }

    /**
     * build
     *
     * @return
     */
    public static List<ApiClass> build() {
        ApiConfig apiConfig = ProjectBuilder.getApiConfig();
        List<ApiClass> apiClassList = new ArrayList<>();
        Collection<JavaClass> javaClasses = ProjectBuilder.getJavaProjectBuilder().getClasses();

        for (JavaClass javaClass : javaClasses) {
            if (JavaClassUtil.ignoreClass(javaClass)) {
                continue;
            }
            if(!JavaClassUtil.isInclude(javaClass, apiConfig.getPackageInclude(), apiConfig.getClassInclude(), apiConfig.getClassExclude())) {
                continue;
            }
            List<ApiMethod> apiMethods = ApiMethodBuilder.buildApiMethods(javaClass, apiConfig);
            ApiClass apiClass = ApiClassBuilder.buildApiClass(javaClass, apiMethods, apiConfig);
            apiClassList.add(apiClass);
        }
        Collections.sort(apiClassList);
        return apiClassList;
    }

    public static JavaProjectBuilder getJavaProjectBuilder() {
        return javaProjectBuilder;
    }

    public static ApiConfig getApiConfig() {
        return apiConfig;
    }
}
