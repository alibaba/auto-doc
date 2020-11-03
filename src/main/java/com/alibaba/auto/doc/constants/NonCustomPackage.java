package com.alibaba.auto.doc.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/31 3:23 下午
 * @description：
 */
public interface NonCustomPackage {

    String PACKAGE_JAVA = "java";

    String PACKAGE_SPRING = "org.springframework";

    String PACKAGE_SERVLET = "javax.servlet";

    List<String> NON_CUSTOM_PACKAGES = Arrays.asList(PACKAGE_JAVA, PACKAGE_SPRING, PACKAGE_SERVLET);

    List<String> NON_CUSTOM_REQUEST_PARAM_PACKAGES = Arrays.asList(PACKAGE_SPRING, PACKAGE_SERVLET);

}
