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
import com.alibaba.auto.doc.model.request.RequestBodyParam;
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
    private static RequestBodyParam buildRequestBodyParam(final JavaField javaField) {
        RequestBodyParam requestBodyParam = new RequestBodyParam();
        requestBodyParam.setName(javaField.getName());
        requestBodyParam.setType(javaField.getType().getGenericFullyQualifiedName());
        requestBodyParam.setComment(javaField.getComment());
        if (StringUtils.isBlank(javaField.getComment())) {
            NoFieldCommentFound noFieldCommentFound = new NoFieldCommentFound(javaField.getDeclaringClass().getCanonicalName(), javaField.getLineNumber(), javaField.getName());
            if(!GlobalFieldCache.notExist(javaField)) {
                log.warn(noFieldCommentFound.toString());
            }
            if(ProjectBuilder.getApiConfig().isStrict()) {
                throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, noFieldCommentFound.toString());
            }
        }
        requestBodyParam.setIsEnum(javaField.getType().isEnum());
        requestBodyParam.setIsCollection(JavaClassUtil.isCollection(javaField.getType()));
        if (StringUtils.isNotBlank(JavaFieldUtil.getSince(javaField))) {
            requestBodyParam.setSince(JavaFieldUtil.getSince(javaField));
        }
        requestBodyParam.setRequired(JavaFieldUtil.isRequired(javaField));

        String defaultValue = javaField.getInitializationExpression();
        defaultValue = StringUtil.removeQuotes(defaultValue);
        if (StringUtils.isNotBlank(defaultValue)) {
            requestBodyParam.setDefaultValue(defaultValue);
        }
        requestBodyParam.setLineNumber(javaField.getLineNumber());

        // set cache
        GlobalFieldCache.put(javaField);
        return requestBodyParam;
    }

    /**
     * recursion build
     *
     * @param javaClass
     * @param deep
     * @return
     */
    private static List<RequestBodyParam> recursionBuild(final JavaClass javaClass, int deep, boolean checkSetMethod, boolean checkGetMethod) {
        deep++;
        if (deep > Constants.REQUEST_BODY_PARAM_DEEP) {
            log.warn("param deep size over {}, current class is {}", Constants.REQUEST_BODY_PARAM_DEEP, javaClass.getCanonicalName());
            return null;
        }
        List<RequestBodyParam> paramList = new ArrayList<>();

        List<JavaField> fieldList = JavaFieldUtil.getFields(javaClass, null, checkSetMethod, checkGetMethod);
        for (JavaField field : fieldList) {

            RequestBodyParam requestBodyParam = buildRequestBodyParam(field);
            JavaClass realJavaClass = JavaClassUtil.getCollectionGenericJavaClass(field.getType(), JavaClassUtil.getGenericType(javaClass));
            if (JavaClassUtil.isCustomClass(realJavaClass)) {
                if(!realJavaClass.getCanonicalName().equals(javaClass.getCanonicalName())) {
                    List<RequestBodyParam> childParams = recursionBuild(realJavaClass, deep, checkSetMethod, checkGetMethod);
                    if (childParams != null && childParams.size() > 0) {
                        requestBodyParam.setChilds(childParams);
                    }
                }
            }
            paramList.add(requestBodyParam);
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
    public static RequestBodyParam buildRequestBodyParam(final JavaClass javaClass, String name, String comment, boolean checkSetMethod, boolean checkGetMethod) {
        RequestBodyParam requestBodyParam = new RequestBodyParam();
        requestBodyParam.setName(name);
        requestBodyParam.setType(javaClass.getGenericFullyQualifiedName());
        requestBodyParam.setIsEnum(javaClass.isEnum());
        requestBodyParam.setIsCollection(JavaClassUtil.isCollection(javaClass));
        requestBodyParam.setComment(comment);
        requestBodyParam.setChilds(recursionBuild(JavaClassUtil.getCollectionGenericJavaClass(javaClass, null), 0, checkSetMethod, checkGetMethod));
        return requestBodyParam;
    }

    /**
     * build request body param
     *
     * @param javaParameter
     * @return
     */
    private RequestBodyParam buildRequestBodyParam(final JavaParameter javaParameter, final Map<String, String> paramCommentMap, boolean checkSetMethod, boolean checkGetMethod) {
        String comment = paramCommentMap.get(javaParameter.getName());
        return buildRequestBodyParam(javaParameter.getJavaClass(), javaParameter.getName(), comment, checkSetMethod, checkGetMethod);
    }

    /**
     * handle
     *
     * @param javaMethod
     * @return
     */
    public RequestBodyParam handle(final JavaMethod javaMethod, Map<String, String> paramCommentMap) {
        for (JavaParameter parameter : javaMethod.getParameters()) {
            for (JavaAnnotation annotation : parameter.getAnnotations()) {
                if (JavaAnnotationUtil.isMatch(annotation, SpringAnnotation.REQUEST_BODY_FULLY)) {
                    return buildRequestBodyParam(parameter, paramCommentMap, true, false);
                }
            }
        }
        return null;
    }
}
