package com.alibaba.auto.doc.enums;

import com.alibaba.auto.doc.model.request.RequestParam;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/8 8:40 下午
 * @description：
 */
public enum JsonSchemaTypeEnum {
    OBJECT("object"),
    ARRAY("array"),
    INTEGER("integer"),
    NUMBER("number"),
    NULL("null"),
    BOOLEAN("boolean"),
    STRING("string");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    JsonSchemaTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static JsonSchemaTypeEnum getByJavaType(RequestParam requestParam) {
        String type = requestParam.getType();
        if(BooleanUtils.isTrue(requestParam.getCollection())) {
            return ARRAY;
        }
        if(BooleanUtils.isTrue(requestParam.getEnum())) {
            String enumType = requestParam.getEnumType().getType();
            if(StringUtils.isNotEmpty(enumType)) {
                type = enumType;
            }
        }
        // if not full class name, convert to lower case
        if(type.indexOf(".") == -1) {
            type = type.toLowerCase(Locale.ROOT);
        }

        switch (type) {
            case "char":
            case "character":
            case "java.lang.Character":
            case "string":
            case "java.lang.String":
            case "date":
            case "java.util.Date":
                return STRING;
            case "byte":
            case "java.lang.Byte":
            case "short":
            case "java.lang.Short":
            case "int":
            case "integer":
            case "java.lang.Integer":
                return INTEGER;
            case "long":
            case "java.lang.Long":
            case "float":
            case "java.lang.Float":
            case "double":
            case "java.lang.Double":
                return NUMBER;
            case "java.util.List":
            case "java.util.LinkedList":
            case "java.util.ArrayList":
            case "java.util.Set":
            case "java.util.TreeSet":
            case "java.util.HashSet":
            case "java.util.SortedSet":
            case "java.util.Collection":
            case "java.util.ArrayDeque":
            case "java.util.PriorityQueue":
                return ARRAY;
            case "boolean":
            case "java.lang.Boolean":
                return BOOLEAN;
            default:
                return OBJECT;
        }
    }
}