package com.alibaba.auto.doc.handler;

import java.util.List;

import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.enums.HttpMethodEnum;
import com.alibaba.auto.doc.model.request.RequestMapping;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;

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
