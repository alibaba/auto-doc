package com.alibaba.auto.doc.builder;

import java.util.List;

import com.alibaba.auto.doc.model.ApiClass;
import com.alibaba.auto.doc.model.ApiConfig;
import com.alibaba.auto.doc.model.ApiMethod;
import com.alibaba.auto.doc.utils.JavaClassUtil;
import com.alibaba.auto.doc.utils.StringUtil;

import com.thoughtworks.qdox.model.JavaClass;

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
        apiClass.setClassFullName(javaClass.getCanonicalName());
        apiClass.setAuthor(JavaClassUtil.getClassAuthor(javaClass));
        apiClass.setApiMethodList(apiMethods);
        apiClass.setDesc(StringUtil.replaceHtmlTag(javaClass.getComment()));
        return apiClass;
    }
}
