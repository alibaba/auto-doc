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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.auto.doc.builder.ProjectBuilder;
import com.alibaba.auto.doc.cache.GlobalFieldCache;
import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;
import com.alibaba.auto.doc.model.comment.NoFieldCommentFound;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.alibaba.auto.doc.utils.JavaClassUtil;
import com.alibaba.auto.doc.utils.JavaFieldUtil;
import com.alibaba.auto.doc.utils.StringUtil;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 8:17 下午
 * @description：
 */
public class RequestBodyHandler {

    private static final Logger log = LoggerFactory.getLogger(RequestBodyHandler.class);

    /**
     * build request body param from JavaField
     *
     * @param javaField
     * @return
     */
    private static RequestParam buildRequestBodyParam(final JavaField javaField) {
        RequestParam requestParam = new RequestParam();
        requestParam.setName(javaField.getName());
        requestParam.setType(javaField.getType().getGenericValue());
        requestParam.setComment(javaField.getComment());
        if (StringUtils.isBlank(javaField.getComment())) {
            NoFieldCommentFound noFieldCommentFound = new NoFieldCommentFound(javaField.getDeclaringClass().getGenericFullyQualifiedName(), javaField.getLineNumber(), javaField.getName());
            if(!GlobalFieldCache.notExist(javaField)) {
                log.warn(noFieldCommentFound.toString());
            }
            if(ProjectBuilder.getApiConfig().isStrict()) {
                throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, noFieldCommentFound.toString());
            }
        }

        if(javaField.getType().isEnum()) {
            requestParam.setEnum(true);
            requestParam.setEnumType(JavaClassUtil.getEnumType(javaField.getType()));
        }

        if(JavaClassUtil.isCollection(javaField.getType())) {
            requestParam.setCollection(true);
            requestParam.setCollectionType(JavaClassUtil.getCollectionType(javaField.getType()));
        }

        if (StringUtils.isNotBlank(JavaFieldUtil.getSince(javaField))) {
            requestParam.setSince(JavaFieldUtil.getSince(javaField));
        }
        requestParam.setRequired(JavaFieldUtil.isRequired(javaField));

        String defaultValue = javaField.getInitializationExpression();
        defaultValue = StringUtil.removeQuotes(defaultValue);
        if (StringUtils.isNotBlank(defaultValue)) {
            requestParam.setDefaultValue(defaultValue);
        }
        requestParam.setLineNumber(javaField.getLineNumber());

        // set cache
        GlobalFieldCache.put(javaField);
        return requestParam;
    }

    /**
     * recursion build
     *
     * @param javaClass
     * @param deep
     * @return
     */
    private static List<RequestParam> recursionBuild(final JavaClass javaClass, int deep, boolean checkSetMethod, boolean checkGetMethod) {

        if(javaClass.getCanonicalName().endsWith("CanvasCrtOpt")) {
            System.out.println(1);
        }

        deep++;
        if (deep > Constants.REQUEST_BODY_PARAM_DEEP) {
            log.warn("param deep size over {}, current class is {}", Constants.REQUEST_BODY_PARAM_DEEP, javaClass.getGenericFullyQualifiedName());
            return null;
        }
        List<RequestParam> paramList = new ArrayList<>();

        List<JavaField> fieldList = JavaFieldUtil.getFields(javaClass, null, checkSetMethod, checkGetMethod);
        for (JavaField field : fieldList) {
            RequestParam requestParam = buildRequestBodyParam(field);
            JavaClass realJavaClass = JavaClassUtil.getCollectionGenericJavaClass(field.getType(), JavaClassUtil.getGenericType(javaClass));
            if (JavaClassUtil.isCustomClass(realJavaClass)) {
                if(!realJavaClass.getGenericFullyQualifiedName().equals(javaClass.getGenericFullyQualifiedName())) {
                    List<RequestParam> childParams = recursionBuild(realJavaClass, deep, checkSetMethod, checkGetMethod);
                    if (childParams != null && childParams.size() > 0) {
                        requestParam.setChilds(childParams);
                    }
                }
            }
            paramList.add(requestParam);
        }
        return paramList;
    }

    /**
     * build request body param from JavaClass
     *
     * @param javaClass
     * @param name
     * @param comment
     * @return
     */
    public static RequestParam buildRequestBodyParam(final JavaClass javaClass, String name, String comment, boolean checkSetMethod, boolean checkGetMethod) {
        RequestParam requestParam = new RequestParam();
        requestParam.setName(name);
        requestParam.setType(javaClass.getGenericValue());

        if(javaClass.isEnum()) {
            requestParam.setEnum(true);
            requestParam.setEnumType(JavaClassUtil.getEnumType(javaClass));
        }

        if(JavaClassUtil.isCollection(javaClass)) {
            requestParam.setCollection(true);
            requestParam.setCollectionType(JavaClassUtil.getCollectionType(javaClass));
        }

        requestParam.setComment(comment);
        requestParam.setChilds(recursionBuild(JavaClassUtil.getCollectionGenericJavaClass(javaClass, null), 0, checkSetMethod, checkGetMethod));
        return requestParam;
    }

    /**
     * handle
     *
     * @param javaMethod
     * @return
     */
    public RequestParam handle(final JavaMethod javaMethod, Map<String, String> paramCommentMap) {
        for (JavaParameter parameter : javaMethod.getParameters()) {
            for (JavaAnnotation annotation : parameter.getAnnotations()) {
                if (JavaAnnotationUtil.isMatch(annotation, SpringAnnotation.REQUEST_BODY_FULLY, SpringAnnotation.REQUEST_BODY)) {
                    String comment = paramCommentMap.get(parameter.getName());
                    return buildRequestBodyParam(parameter.getJavaClass(), parameter.getName(), comment, true, false);
                }
            }
        }
        return null;
    }
}
