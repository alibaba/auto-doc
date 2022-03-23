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
 * @date ：Created in 2020/10/31 6:23 下午
 * @description：
 */
public interface BasicJavaType {

    String BYTE = "byte";
    String BYTE_WRAP = "java.lang.Byte";

    String SHORT = "short";
    String SHORT_WRAP = "java.lang.Short";

    String INT = "int";
    String INT_WRAP = "java.lang.Integer";

    String LONG = "long";
    String LONG_WRAP = "java.lang.Long";

    String FLOAT = "float";
    String FLOAT_WRAP = "java.lang.Float";

    String DOUBLE = "double";
    String DOUBLE_WRAP = "java.lang.Double";

    String CHAR = "char";
    String CHAR_WRAP = "java.lang.Character";

    String BOOLEAN = "boolean";
    String BOOLEAN_WRAP = "java.lang.Boolean";

    String STRING = "java.lang.String";
    String STRING_SHORT = "string";

    String DATE = "java.util.Date";
    String DATE_SHORT = "date";

    List<String> BASIC_TYPES = Arrays.asList(
        BYTE, BYTE_WRAP,
        SHORT, SHORT_WRAP,
        INT, INT_WRAP,
        LONG, LONG_WRAP,
        FLOAT, FLOAT_WRAP,
        DOUBLE, DOUBLE_WRAP,
        CHAR, CHAR_WRAP,
        BOOLEAN, BOOLEAN_WRAP,
        STRING);

}
