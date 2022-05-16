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

import com.alibaba.auto.doc.config.ApiConfig;
import com.alibaba.auto.doc.constants.JavaTag;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.handler.*;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.model.request.RequestMapping;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.alibaba.auto.doc.utils.JavaMethodUtil;
import com.alibaba.auto.doc.utils.URLUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 9:52 下午
 * @description：
 */
public class ApiMethodBuilder {

    /**
     * build api method
     *
     * @param method
     * @param apiConfig
     * @param baseUrl
     * @return
     */
    private static ApiMethod buildApiMethod(final JavaMethod method, ApiConfig apiConfig, String baseUrl) {
        Map<String, String> paramCommentMap = JavaMethodUtil.getParamCommentMap(method, apiConfig.isStrict());
        ApiMethod apiMethod = new ApiMethod();
        apiMethod.setMethodName(method.getName());
        apiMethod.setMethodSignature(method.getDeclarationSignature(true));
        apiMethod.setMethodId(JavaMethodUtil.buildMethodId(method));
        apiMethod.setComment(JavaMethodUtil.getMethodComment(method, apiConfig.isStrict()));
        apiMethod.setLineNumber(method.getLineNumber());
        apiMethod.setApiNote(JavaMethodUtil.getTagValue(method, JavaTag.API_NOTE));
        apiMethod.setAuthor(JavaMethodUtil.getAuthor(method));
        apiMethod.setDeprecated(JavaMethodUtil.isDeprecated(method));
        RequestMapping requestMapping = new RequestMappingHandler().handle(baseUrl, method);

        List<String> urls = new LinkedList<>();
        if (apiConfig.getUrlFirstIfMulti()) {
            if (apiConfig.getUrlRemoveRegExp()) {
                urls.add(URLUtil.removeUrlRegExp(requestMapping.getUrls().get(0)));
            } else {
                urls.add(requestMapping.getUrls().get(0));
            }
        } else {
            for (String url : requestMapping.getUrls()) {
                if (apiConfig.getUrlRemoveRegExp()) {
                    urls.add(URLUtil.removeUrlRegExp(url));
                } else {
                    urls.add(url);
                }
            }
        }
        apiMethod.setUrl(StringUtils.join(urls, SpecialCharacter.COMMA));

        apiMethod.setHttpMethod(requestMapping.getMethodType());

        apiMethod.setContentType(JavaMethodUtil.getContentType(method));

        List<RequestParam> requestHeaderParams = new RequestHeaderHandler().handle(method, paramCommentMap);
        apiMethod.setRequestHeaderParams(requestHeaderParams);

        List<RequestParam> requestParams = new RequestParamHandler().handle(method, paramCommentMap);
        apiMethod.setRequestParams(requestParams);

        RequestParam requestBody = new RequestBodyHandler().handle(method, paramCommentMap);
        apiMethod.setRequestBody(requestBody);

        RequestParam responseBodyParam = new ResponseBodyHandler().handle(method);
        apiMethod.setResponseBody(responseBodyParam);


        // TODO
        //RequestExample requestExample = new RequestExample();
        //
        //JsonSchema requestBodyJsonSchema = JsonSchemaUtil.buildJsonSchema(requestBody);
        //requestExample.setJsonBody(StringUtil.replaceHtmlTag(JSON.toJSONString(requestBodyJsonSchema, SerializerFeature.PrettyFormat)));
        //apiMethod.setRequestExample(requestExample);
        //
        //JsonSchema responseBodyJsonSchema = JsonSchemaUtil.buildJsonSchema(responseBodyParam);
        //apiMethod.setResponseExample(StringUtil.replaceHtmlTag(JSON.toJSONString(responseBodyJsonSchema, SerializerFeature.PrettyFormat)));

        return apiMethod;
    }

    /**
     * build api methods
     *
     * @param javaClass
     * @param apiConfig
     * @return
     */
    public static List<ApiMethod> buildApiMethods(final JavaClass javaClass, ApiConfig apiConfig) {
        List<JavaAnnotation> classAnnotations = javaClass.getAnnotations();
        String baseUrl = SpecialCharacter.SLASH;
        for (JavaAnnotation annotation : classAnnotations) {
            String annotationName = annotation.getType().getCanonicalName();
            if (SpringAnnotation.REQUEST_MAPPING_FULLY.equals(annotationName) || SpringAnnotation.REQUEST_MAPPING.equals(annotationName)) {
                baseUrl = JavaAnnotationUtil.getBaseUrl(annotation);
            }
        }

        List<JavaMethod> methods = javaClass.getMethods();
        List<ApiMethod> apiMethodList = new ArrayList<>(methods.size());

        for (JavaMethod method : methods) {
            if(!apiConfig.getIgnoreAutoDocTag()) {
                if (JavaMethodUtil.hasIgnoreTag(method)) {
                    continue;
                }
            }
            if (JavaMethodUtil.notApiMethod(method)) {
                continue;
            }
            ApiMethod apiMethod = buildApiMethod(method, apiConfig, baseUrl);
            if (apiMethod != null) {
                apiMethodList.add(apiMethod);
            }
        }
        return apiMethodList;
    }
}
