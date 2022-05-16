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
import com.alibaba.auto.doc.model.ApiClass;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.utils.JavaClassUtil;
import com.alibaba.auto.doc.utils.StringUtil;
import com.thoughtworks.qdox.model.JavaClass;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 11:26 上午
 * @description：
 */
public class ApiClassBuilder {

    public static ApiClass buildApiClass(JavaClass javaClass, List<ApiMethod> apiMethods, ApiConfig apiConfig) {
        JavaClassUtil.checkClassComment(javaClass, apiConfig.isStrict());

        ApiClass apiClass = new ApiClass();
        apiClass.setClassName(javaClass.getName());
        apiClass.setClassFullName(javaClass.getGenericFullyQualifiedName());
        apiClass.setAuthor(JavaClassUtil.getClassAuthor(javaClass));
        apiClass.setApiMethodList(apiMethods);
        apiClass.setDesc(StringUtil.replaceHtmlTag(JavaClassUtil.getClassComment(javaClass)));
        return apiClass;
    }
}
