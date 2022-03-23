package com.alibaba.auto.doc.cache;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.auto.doc.constants.SpecialCharacter;

import com.thoughtworks.qdox.model.JavaField;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/1 4:25 下午
 * @description：
 */
public class GlobalFieldCache {

    private static Set<String> fieldCache = new HashSet<>();

    public static void empty() {
        fieldCache = new HashSet<>();
    }

    public static boolean notExist(final JavaField javaField) {
        return fieldCache.contains(getKey(javaField));
    }

    public static void put(final JavaField javaField) {
        fieldCache.add(getKey(javaField));
    }

    private static String getKey(JavaField javaField) {
        return javaField.getDeclaringClass().getGenericFullyQualifiedName() + SpecialCharacter.DOT + javaField.getName();
    }
}
