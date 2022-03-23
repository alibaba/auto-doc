package com.alibaba.auto.doc.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.constants.SpringAnnotationParam;
import com.alibaba.auto.doc.enums.HttpMethodEnum;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/29 3:41 下午
 * @description：
 */
public class JavaAnnotationUtil {

    private static final Logger log = LoggerFactory.getLogger(JavaAnnotationUtil.class);

    /**
     * is match
     *
     * @param annotation
     * @param annotationNames
     * @return
     */
    public static boolean isMatch(final JavaAnnotation annotation, String... annotationNames) {
        for (String annotationName : annotationNames) {
            if (annotation.getType().getName().equals(annotationName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * is match
     *
     * @param annotations
     * @param annotationNames
     * @return
     */
    public static boolean isMatch(final List<JavaAnnotation> annotations, String... annotationNames) {
        for (JavaAnnotation annotation : annotations) {
            for (String annotationName : annotationNames) {
                if (annotation.getType().getCanonicalName().equals(annotationName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if annotation exist
     *
     * @param annotations
     * @param annotationName
     * @return
     */
    public static boolean exist(final List<JavaAnnotation> annotations, final String annotationName) {
        for (JavaAnnotation annotation : annotations) {
            if (annotation.getType().getCanonicalName().endsWith(annotationName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * get url
     *
     * @param annotation
     * @return
     */
    public static String getBaseUrl(final JavaAnnotation annotation) {
        Object urls = annotation.getNamedParameter(SpringAnnotationParam.PROP_VALUE);
        if (urls == null) {
            urls = annotation.getNamedParameter(SpringAnnotationParam.PROP_PATH);
        }
        String url = SpecialCharacter.BLANK;

        if (urls instanceof List) {
            url = ((List) urls).get(0).toString();
            log.warn("there are multi value for annotation: {}, choose {}", annotation.getType().getCanonicalName(), url);
        } else if (urls instanceof String) {
            url = urls.toString();
        }
        url = StringUtil.removeQuotes(url);
        if (!url.startsWith(SpecialCharacter.SLASH)) {
            url = SpecialCharacter.SLASH + url;
        }
        if (!url.endsWith(SpecialCharacter.SLASH)) {
            url = url + SpecialCharacter.SLASH;
        }
        return url;
    }

    /**
     * get request mapping url
     *
     * @param annotation
     * @param baseUrl
     * @return
     */
    public static List<String> getApiUrls(final JavaAnnotation annotation, String baseUrl) {
        List<String> urlList = new LinkedList<>();
        List<String> list = new LinkedList<String>();
        Object urls = annotation.getNamedParameter(SpringAnnotationParam.PROP_VALUE);
        if (urls == null) {
            // value取不到就取path
            urls = annotation.getNamedParameter(SpringAnnotationParam.PROP_PATH);
        }
        if (urls != null) {
            if (urls instanceof List) {
                list.addAll((List<String>) urls);
            } else {
                list.add(urls.toString());
            }
        } else {
            list.add(SpecialCharacter.BLANK);
        }

        for (String url : list) {
            url = StringUtil.removeQuotes(url).trim();
            if (url.startsWith(SpecialCharacter.SLASH)) {
                url = url.substring(1);
            }
            url = baseUrl + url;

            if (url.endsWith(SpecialCharacter.SLASH)) {
                url = url.substring(0, url.length() - 1);
            }
            urlList.add(url);
        }
        return urlList;
    }

    /**
     * get http methods
     *
     * @param annotation
     * @return
     */
    public static String getHttpMethods(final JavaAnnotation annotation, final JavaMethod javaMethod) {
        Object method = annotation.getNamedParameter(SpringAnnotationParam.PROP_METHOD);
        if (method != null) {
            return HttpMethodEnum.getHttpMethod(method.toString());
        } else {
            List<JavaAnnotation> annotations = new ArrayList<>();
            for (JavaParameter parameter : javaMethod.getParameters()) {
                annotations.addAll(parameter.getAnnotations());
            }
            if (JavaAnnotationUtil.isMatch(annotations, SpringAnnotation.REQUEST_BODY_FULLY)) {
                return HttpMethodEnum.getAllMethodWithRequestBody();
            } else {
                return HttpMethodEnum.getAllMethod();
            }
        }
    }
}
