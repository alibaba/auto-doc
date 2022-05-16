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
package com.alibaba.auto.doc.cache;

import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.thoughtworks.qdox.model.JavaField;

import java.util.HashSet;
import java.util.Set;

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
