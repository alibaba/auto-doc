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
package com.alibaba.auto.doc.handler;

import com.alibaba.auto.doc.builder.ProjectBuilder;
import com.alibaba.auto.doc.constants.NonCustomPackage;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.constants.SpringAnnotationParam;
import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.alibaba.auto.doc.utils.JavaClassUtil;
import com.alibaba.auto.doc.utils.JavaFieldUtil;
import com.alibaba.auto.doc.utils.StringUtil;
import com.thoughtworks.qdox.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 8:17 下午
 * @description：
 */
public class RequestParamHandler {

    private static final Logger log = LoggerFactory.getLogger(RequestParamHandler.class);

    /**
     * build request param from annotation
     *
     * @param annotation
     * @return
     */
    private RequestParam buildRequestParam(final JavaAnnotation annotation) {
        RequestParam requestParam = new RequestParam();
        Object value = annotation.getNamedParameter(SpringAnnotationParam.PROP_VALUE);
        if (value != null) {
            requestParam.setName(StringUtil.removeQuotes(value.toString()));
        }

        Object required = annotation.getNamedParameter(SpringAnnotationParam.PROP_REQUIRED);
        if (required != null) {
            requestParam.setRequired(Boolean.parseBoolean(StringUtil.removeQuotes(required.toString())));
        }

        Object defaultValue = annotation.getNamedParameter(SpringAnnotationParam.PROP_DEFAULT_VALUE);
        if (defaultValue != null) {
            requestParam.setDefaultValue(StringUtil.removeQuotes(defaultValue.toString()));
            requestParam.setRequired(false);
        }

        return requestParam;
    }

    /**
     * build request param
     *
     * @param javaClass
     * @return
     */
    private List<RequestParam> buildRequestParam(final JavaClass javaClass) {
        List<RequestParam> requestParams = new ArrayList<>();
        List<JavaField> fields = JavaFieldUtil.getFields(javaClass, null, true, false);
        for (JavaField field : fields) {
            RequestParam requestParam = new RequestParam();
            requestParam.setName(field.getName());
            requestParam.setType(field.getType().getGenericValue());
            if (StringUtils.isNotBlank(field.getComment())) {
                requestParam.setComment(field.getComment());
            }
            requestParam.setRequired(JavaFieldUtil.isRequired(field));
            if(field.getType().isEnum()) {
                requestParam.setEnum(true);
                requestParam.setEnumType(JavaClassUtil.getEnumType(field.getType()));
            }

            String since = JavaFieldUtil.getSince(field);
            if (StringUtils.isNotBlank(since)) {
                requestParam.setSince(since);
            }

            String defaultValue = field.getInitializationExpression();
            defaultValue = StringUtil.removeQuotes(defaultValue);
            if (StringUtils.isNotBlank(defaultValue)) {
                if(defaultValue.indexOf(SpecialCharacter.JAVA_COMMENT) != -1) {
                    log.warn("two slashes comment inline with code in " + javaClass.getGenericFullyQualifiedName() + SpecialCharacter.DOT + field.getName() + ", lineNumber: " + field.getLineNumber());
                    if(ProjectBuilder.getApiConfig().isStrict()) {
                        throw new AutoDocException(ErrorCodes.AD_TWO_SLASHES_COMMENT_INLINE_WITH_SOURCE_CODE,
                            "two slashes comment inline with code in " + javaClass.getGenericFullyQualifiedName() + SpecialCharacter.DOT + field.getName() + ", lineNumber: " + field.getLineNumber());
                    }
                } else {
                    requestParam.setDefaultValue(defaultValue);
                }
            }

            requestParam.setLineNumber(field.getLineNumber());
            requestParams.add(requestParam);
        }
        return requestParams;
    }

    /**
     * handle
     *
     * @param javaMethod
     * @return
     */
    public List<RequestParam> handle(final JavaMethod javaMethod, Map<String, String> paramCommentMap) {
        List<RequestParam> params = new ArrayList<>();
        for (JavaParameter parameter : javaMethod.getParameters()) {
            JavaAnnotation requestParamAnnotation = getRequestParamAnnotation(parameter);
            if (requestParamAnnotation != null) {
                RequestParam requestParam = buildRequestParam(requestParamAnnotation);
                if (requestParam == null) {
                    requestParam = new RequestParam();
                }
                if (StringUtils.isEmpty(requestParam.getName())) {
                    requestParam.setName(parameter.getName());
                }
                if (StringUtils.isEmpty(requestParam.getType())) {
                    requestParam.setType(parameter.getType().getGenericFullyQualifiedName());
                }
                if (StringUtils.isEmpty(requestParam.getComment())) {
                    requestParam.setComment(paramCommentMap.get(parameter.getName()));
                }
                if(parameter.getJavaClass().isEnum()) {
                    requestParam.setEnum(true);
                    requestParam.setEnumType(JavaClassUtil.getEnumType(parameter.getJavaClass()));
                }
                if(JavaClassUtil.isCollection(parameter.getJavaClass())) {
                    requestParam.setCollection(true);
                    requestParam.setCollectionType(JavaClassUtil.getCollectionType(parameter.getJavaClass()));
                }

                requestParam.setLineNumber(parameter.getLineNumber());
                params.add(requestParam);
            } else {
                if (isRequestParam(parameter)) {
                    if (JavaFieldUtil.getFields(parameter.getJavaClass(), null, true, false).size() == 0) {
                        RequestParam requestParam = new RequestParam();
                        requestParam.setName(parameter.getName());
                        requestParam.setType(parameter.getJavaClass().getGenericValue());
                        if (StringUtils.isNotBlank(paramCommentMap.get(parameter.getName()))) {
                            requestParam.setComment(paramCommentMap.get(parameter.getName()));
                        }

                        if(parameter.getJavaClass().isEnum()) {
                            requestParam.setEnum(true);
                            requestParam.setEnumType(JavaClassUtil.getEnumType(parameter.getJavaClass()));
                        }
                        if(JavaClassUtil.isCollection(parameter.getJavaClass())) {
                            requestParam.setCollection(true);
                            requestParam.setCollectionType(JavaClassUtil.getCollectionType(parameter.getJavaClass()));
                        }
                        requestParam.setLineNumber(parameter.getLineNumber());
                        params.add(requestParam);
                    } else {
                        params.addAll(buildRequestParam(parameter.getJavaClass()));
                    }
                }
            }
        }
        return params;
    }

    private JavaAnnotation getRequestParamAnnotation(final JavaParameter parameter) {
        for (JavaAnnotation annotation : parameter.getAnnotations()) {
            if (JavaAnnotationUtil.isMatch(annotation, SpringAnnotation.REQUEST_PARAM, SpringAnnotation.REQUEST_PARAM_FULLY)) {
                return annotation;
            }
        }
        return null;
    }

    private boolean isRequestParam(final JavaParameter parameter) {
        if (JavaAnnotationUtil.isMatch(parameter.getAnnotations(), SpringAnnotation.REQUEST_BODY_FULLY, SpringAnnotation.REQUEST_BODY, SpringAnnotation.REQUEST_HEADER_FULLY, SpringAnnotation.REQUEST_HEADER)) {
            return false;
        }
        for (String nonCustomRequestParamPackage : NonCustomPackage.NON_CUSTOM_REQUEST_PARAM_PACKAGES) {
            if (parameter.getJavaClass().getGenericFullyQualifiedName().startsWith(nonCustomRequestParamPackage)) {
                return false;
            }
        }
        return true;
    }
}
