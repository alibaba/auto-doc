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
