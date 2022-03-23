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
package com.alibaba.auto.doc.utils;

import com.alibaba.auto.doc.constants.BasicJavaType;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/9 5:45 下午
 * @description：
 */
public class TypeConvertUtil {

    public static String getBasicType(String type) {
        switch (type) {
            case BasicJavaType.BYTE_WRAP:
                return BasicJavaType.BYTE;
            case BasicJavaType.SHORT_WRAP:
                return BasicJavaType.SHORT;
            case BasicJavaType.INT_WRAP:
                return BasicJavaType.INT;
            case BasicJavaType.LONG_WRAP:
                return BasicJavaType.LONG;
            case BasicJavaType.FLOAT_WRAP:
                return BasicJavaType.FLOAT;
            case BasicJavaType.DOUBLE_WRAP:
                return BasicJavaType.DOUBLE;
            case BasicJavaType.CHAR_WRAP:
                return BasicJavaType.CHAR;
            case BasicJavaType.BOOLEAN_WRAP:
                return BasicJavaType.BOOLEAN;
            case BasicJavaType.STRING:
                return BasicJavaType.STRING_SHORT;
            case BasicJavaType.DATE:
                return BasicJavaType.DATE_SHORT;
            default:
                return type;
        }
    }
}
