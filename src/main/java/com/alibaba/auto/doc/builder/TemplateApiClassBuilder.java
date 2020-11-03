package com.alibaba.auto.doc.builder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.model.ApiClass;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.model.request.RequestBodyParam;
import com.alibaba.auto.doc.model.request.RequestParam;
import com.alibaba.auto.doc.model.template.TemplateApiClass;
import com.alibaba.auto.doc.model.template.TemplateApiMethod;
import com.alibaba.auto.doc.model.template.TemplateRequestParam;
import com.alibaba.auto.doc.utils.StringUtil;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 10:14 下午
 * @description：
 */
public class TemplateApiClassBuilder {

    /**
     * build TemplateApiClass
     *
     * @param apiClassList
     * @return
     */
    public static List<TemplateApiClass> build(final List<ApiClass> apiClassList) {
        List<TemplateApiClass> templateApiClassList = new LinkedList<>();
        int order = 0;
        for (ApiClass apiClass : apiClassList) {
            order++;
            TemplateApiClass templateApiClass = new TemplateApiClass();
            templateApiClass.setId(DigestUtils.md5Hex(apiClass.getClassFullName()));
            templateApiClass.setClassName(apiClass.getClassName());
            templateApiClass.setClassFullName(apiClass.getClassFullName());
            templateApiClass.setAuthor(apiClass.getAuthor());
            templateApiClass.setDesc(apiClass.getDesc());
            templateApiClass.setApiMethodList(buildMethods(apiClass.getApiMethodList()));
            templateApiClass.setOrder(order);
            templateApiClassList.add(templateApiClass);
        }
        return templateApiClassList;
    }

    /**
     * build TemplateApiMethodList
     *
     * @param apiMethodList
     * @return
     */
    private static List<TemplateApiMethod> buildMethods(final List<ApiMethod> apiMethodList) {
        List<TemplateApiMethod> templateApiMethodList = new LinkedList<>();
        int order = 0;
        for (ApiMethod apiMethod : apiMethodList) {
            order++;
            TemplateApiMethod templateApiMethod = new TemplateApiMethod();
            templateApiMethod.setMethodId(apiMethod.getMethodId());
            templateApiMethod.setMethodName(apiMethod.getMethodName());
            templateApiMethod.setMethodSignature(apiMethod.getMethodSignature());
            templateApiMethod.setAuthor(apiMethod.getAuthor());
            templateApiMethod.setComment(apiMethod.getComment());
            templateApiMethod.setLineNumber(apiMethod.getLineNumber());
            templateApiMethod.setApiNote(apiMethod.getApiNote());
            templateApiMethod.setDeprecated(apiMethod.getDeprecated());
            templateApiMethod.setUrl(apiMethod.getUrl());
            templateApiMethod.setHttpMethod(apiMethod.getHttpMethod());
            templateApiMethod.setContentType(apiMethod.getContentType());

            templateApiMethod.setRequestHeaderParams(buildTemplateRequestParams(apiMethod.getRequestHeaderParams()));
            templateApiMethod.setRequestParams(buildTemplateRequestParams(apiMethod.getRequestParams()));
            templateApiMethod.setRequestBodyParams(buildTemplateRequestParams(buildFlatRequestParams(apiMethod.getRequestBodyParam(), 0)));
            templateApiMethod.setResponseBodyParams(buildTemplateRequestParams(buildFlatRequestParams(apiMethod.getResponseBodyParam(), 0)));

            templateApiMethod.setRequestExample(apiMethod.getRequestExample().buildCurlString());
            templateApiMethod.setResponseExample(apiMethod.getResponseExample());
            templateApiMethod.setOrder(order);
            templateApiMethodList.add(templateApiMethod);
        }
        return templateApiMethodList;
    }

    /**
     * conver tree to flat
     *
     * @param requestBodyParam
     * @return
     */
    private static List<RequestParam> buildFlatRequestParams(RequestBodyParam requestBodyParam, int deep) {
        List<RequestParam> requestParamList = new LinkedList<>();

        if(requestBodyParam == null) {
            return requestParamList;
        }

        RequestParam requestParam = new RequestParam();
        requestParam.setName(getPrefix(deep) + requestBodyParam.getName());
        requestParam.setType(requestBodyParam.getType());
        requestParam.setDefaultValue(requestBodyParam.getDefaultValue());
        requestParam.setComment(StringUtil.replaceHtmlTag(requestBodyParam.getComment()));
        requestParam.setRequired(requestBodyParam.getRequired());
        requestParam.setSince(requestBodyParam.getSince());
        requestParam.setLineNumber(requestBodyParam.getLineNumber());
        requestParam.setIsEnum(requestBodyParam.getIsEnum());
        requestParamList.add(requestParam);
        if (requestBodyParam.getChilds() != null) {
            deep++;
            for (RequestBodyParam childRequestBodyParam : requestBodyParam.getChilds()) {
                requestParamList.addAll(buildFlatRequestParams(childRequestBodyParam, deep));
            }
        }
        return requestParamList;
    }

    private static String getPrefix(int deep) {
        if (deep > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < deep - 1; i++) {
                sb.append(SpecialCharacter.HTML_SPACE)
                    .append(SpecialCharacter.HTML_SPACE)
                    .append(SpecialCharacter.HTML_SPACE)
                    .append(SpecialCharacter.HTML_SPACE);
            }
            sb.append(SpecialCharacter.TREE_ICON);
            return sb.toString();
        }
        return SpecialCharacter.BLANK;
    }

    private static List<TemplateRequestParam> buildTemplateRequestParams(List<RequestParam> requestParams) {
        List<TemplateRequestParam> templateRequestParams = new ArrayList<>();
        for (RequestParam requestParam : requestParams) {
            TemplateRequestParam templateRequestParam = new TemplateRequestParam();
            templateRequestParam.setName(requestParam.getName());
            templateRequestParam.setType(requestParam.getType());
            templateRequestParam.setDefaultValue(requestParam.getDefaultValue());
            templateRequestParam.setComment(requestParam.getComment());
            templateRequestParam.setRequired(requestParam.getRequired());
            templateRequestParam.setSince(requestParam.getSince());
            templateRequestParam.setLineNumber(requestParam.getLineNumber());
            templateRequestParam.setEnum(requestParam.isEnum());
            templateRequestParams.add(templateRequestParam);
        }
        return templateRequestParams;
    }

}
