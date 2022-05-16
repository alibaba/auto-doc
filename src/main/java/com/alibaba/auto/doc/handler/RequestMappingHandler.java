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
import com.alibaba.auto.doc.enums.HttpMethodEnum;
import com.alibaba.auto.doc.model.request.RequestMapping;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class RequestMappingHandler {

    /**
     * handle spring request mapping
     *
     * @param baseUrl      base url
     * @param method       JavaMethod
     * @return RequestMapping
     */
    public RequestMapping handle(String baseUrl, JavaMethod method) {

        String methodType = null;
        List<String> urls = null;

        List<JavaAnnotation> annotations = method.getAnnotations();
        for (JavaAnnotation annotation : annotations) {
            String annotationName = annotation.getType().getCanonicalName();
            String[] arr = annotationName.split("\\.");
            annotationName = arr[arr.length - 1];

            if (SpringAnnotation.REQUEST_MAPPINGS.contains(annotationName)) {
                urls = JavaAnnotationUtil.getApiUrls(annotation, baseUrl);
            }

            if (SpringAnnotation.REQUEST_MAPPING.equals(annotationName)) {
                methodType = JavaAnnotationUtil.getHttpMethods(annotation, method);
            } else if (SpringAnnotation.GET_MAPPING.equals(annotationName)) {
                methodType = HttpMethodEnum.GET.getValue();
            } else if (SpringAnnotation.POST_MAPPING.equals(annotationName)) {
                methodType = HttpMethodEnum.POST.getValue();
            } else if (SpringAnnotation.PUT_MAPPING.equals(annotationName)) {
                methodType = HttpMethodEnum.PUT.getValue();
            } else if (SpringAnnotation.PATCH_MAPPING.equals(annotationName)) {
                methodType = HttpMethodEnum.PATCH.getValue();
            } else if (SpringAnnotation.DELETE_MAPPING.equals(annotationName)) {
                methodType = HttpMethodEnum.DELETE.getValue();
            }
        }

        RequestMapping requestMapping = new RequestMapping(urls, methodType);
        return requestMapping;
    }
}
