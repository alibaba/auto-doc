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
