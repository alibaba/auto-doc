package com.alibaba.auto.doc.handler;

import com.alibaba.auto.doc.model.request.RequestBodyParam;
import com.alibaba.auto.doc.utils.JavaClassUtil;
import com.alibaba.auto.doc.utils.JavaMethodUtil;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 8:17 下午
 * @description：
 */
public class ResponseBodyHandler {

    private static final Logger log = LoggerFactory.getLogger(ResponseBodyHandler.class);

    /**
     * handle
     *
     * @param javaMethod
     * @return
     */
    public RequestBodyParam handle(final JavaMethod javaMethod) {
        JavaClass returnJavaClass = javaMethod.getReturns();
        if (JavaClassUtil.isBasicType(returnJavaClass, false, true)) {
            RequestBodyParam responseBodyParam = new RequestBodyParam();
            responseBodyParam.setType(returnJavaClass.getGenericFullyQualifiedName());
            responseBodyParam.setIsEnum(returnJavaClass.isEnum());
            responseBodyParam.setIsCollection(JavaClassUtil.isCollection(returnJavaClass));
            String methodReturnComment = JavaMethodUtil.getMethodReturnComment(javaMethod);
            if (StringUtils.isNotBlank(methodReturnComment)) {
                responseBodyParam.setComment(methodReturnComment);
            }
            return responseBodyParam;
        } else {
            String comment = JavaMethodUtil.getMethodReturnComment(javaMethod);
            return RequestBodyHandler.buildRequestBodyParam(returnJavaClass, javaMethod.getReturnType().getValue(), comment, false, true);
        }
    }
}
