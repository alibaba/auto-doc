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

import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.constants.SpringAnnotationParam;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.alibaba.auto.doc.utils.StringUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestHeaderHandler {

    private RequestParam buildRequestHeader(final JavaAnnotation annotation) {
        RequestParam requestHeaderParam = new RequestParam();
        Object value = annotation.getNamedParameter(SpringAnnotationParam.PROP_VALUE);
        if(value != null) {
            requestHeaderParam.setName(StringUtil.removeQuotes(value.toString()));
        }

        Object required = annotation.getNamedParameter(SpringAnnotationParam.PROP_REQUIRED);
        if(required != null) {
            requestHeaderParam.setRequired(Boolean.parseBoolean(StringUtil.removeQuotes(required.toString())));
        }

        Object defaultValue = annotation.getNamedParameter(SpringAnnotationParam.PROP_DEFAULT_VALUE);
        if(defaultValue != null) {
            requestHeaderParam.setDefaultValue(StringUtil.removeQuotes(defaultValue.toString()));
            requestHeaderParam.setRequired(false);
        }

        return requestHeaderParam;
    }

    /**
     * handle Spring MVC Request Header
     *
     * @param javaMethod JavaMethod
     * @return list of RequestHeaderParam
     */
    public List<RequestParam> handle(final JavaMethod javaMethod, Map<String, String> paramCommentMap) {
        List<RequestParam> requestHeaderParams = new ArrayList<>();
        for (JavaParameter javaParameter : javaMethod.getParameters()) {
            String paramName = javaParameter.getName();
            String paramType = javaParameter.getType().getGenericFullyQualifiedName();
            for (JavaAnnotation annotation : javaParameter.getAnnotations()) {
                if(JavaAnnotationUtil.isMatch(annotation, SpringAnnotation.REQUEST_HEADER_FULLY)) {
                    RequestParam requestParam = buildRequestHeader(annotation);
                    if(StringUtils.isEmpty(requestParam.getName())) {
                        requestParam.setName(paramName);
                    }
                    requestParam.setComment(paramCommentMap.get(paramName));
                    requestParam.setType(paramType);
                    requestParam.setLineNumber(javaParameter.getLineNumber());
                    requestParam.setEnum(javaParameter.getJavaClass().isEnum());
                    requestHeaderParams.add(requestParam);
                }
            }
        }
        return requestHeaderParams;
    }
}
