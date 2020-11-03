package com.alibaba.auto.doc.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.auto.doc.constants.JavaTag;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.handler.RequestBodyHandler;
import com.alibaba.auto.doc.handler.RequestHeaderHandler;
import com.alibaba.auto.doc.handler.RequestMappingHandler;
import com.alibaba.auto.doc.handler.RequestParamHandler;
import com.alibaba.auto.doc.handler.ResponseBodyHandler;
import com.alibaba.auto.doc.model.ApiConfig;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.model.request.RequestBodyParam;
import com.alibaba.auto.doc.model.request.RequestExample;
import com.alibaba.auto.doc.model.request.RequestMapping;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.utils.JavaAnnotationUtil;
import com.alibaba.auto.doc.utils.JavaMethodUtil;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.commons.lang3.StringUtils;

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
        JavaMethodUtil.checkMethodComment(method, apiConfig.isStrict());

        Map<String, String> paramCommentMap = JavaMethodUtil.getParamCommentMap(method, apiConfig.isStrict());

        ApiMethod apiMethod = new ApiMethod();

        apiMethod.setMethodName(method.getName());
        apiMethod.setMethodSignature(method.getDeclarationSignature(true));
        apiMethod.setMethodId(JavaMethodUtil.buildMethodId(method));
        apiMethod.setComment(method.getComment());
        apiMethod.setLineNumber(method.getLineNumber());
        apiMethod.setApiNote(JavaMethodUtil.getTagValue(method, JavaTag.API_NOTE));
        apiMethod.setAuthor(JavaMethodUtil.getAuthor(method));
        apiMethod.setDeprecated(JavaMethodUtil.isDeprecated(method));

        RequestMapping requestMapping = new RequestMappingHandler().handle(baseUrl, method);
        apiMethod.setUrl(StringUtils.join(requestMapping.getUrls(), SpecialCharacter.COMMA));
        apiMethod.setHttpMethod(requestMapping.getMethodType());

        apiMethod.setContentType(JavaMethodUtil.getContentType(method));

        List<RequestParam> requestHeaderParams = new RequestHeaderHandler().handle(method, paramCommentMap);
        apiMethod.setRequestHeaderParams(requestHeaderParams);

        List<RequestParam> requestParams = new RequestParamHandler().handle(method, paramCommentMap);
        apiMethod.setRequestParams(requestParams);

        RequestBodyParam requestBodyParam = new RequestBodyHandler().handle(method, paramCommentMap);
        apiMethod.setRequestBodyParam(requestBodyParam);

        RequestBodyParam responseBodyParam = new ResponseBodyHandler().handle(method);
        apiMethod.setResponseBodyParam(responseBodyParam);

        // TODO
        apiMethod.setRequestExample(new RequestExample());
        apiMethod.setResponseExample(null);

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
            if (SpringAnnotation.REQUEST_MAPPING_FULLY.equals(annotationName)) {
                baseUrl = JavaAnnotationUtil.getBaseUrl(annotation);
            }
        }

        List<JavaMethod> methods = javaClass.getMethods();
        List<ApiMethod> apiMethodList = new ArrayList<>(methods.size());

        for (JavaMethod method : methods) {
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
