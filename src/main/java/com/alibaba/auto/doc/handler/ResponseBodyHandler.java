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

import com.alibaba.auto.doc.model.request.RequestParam;
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
    public RequestParam handle(final JavaMethod javaMethod) {
        JavaClass returnJavaClass = javaMethod.getReturns();
        if (JavaClassUtil.isBasicType(returnJavaClass, false, true)) {
            RequestParam responseBodyParam = new RequestParam();
            responseBodyParam.setType(returnJavaClass.getGenericFullyQualifiedName());

            if(returnJavaClass.isEnum()) {
                responseBodyParam.setEnum(true);
                responseBodyParam.setEnumType(JavaClassUtil.getEnumType(returnJavaClass));
            }

            if(JavaClassUtil.isCollection(returnJavaClass)) {
                responseBodyParam.setCollection(true);
                responseBodyParam.setCollectionType(JavaClassUtil.getCollectionType(returnJavaClass));
            }

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
