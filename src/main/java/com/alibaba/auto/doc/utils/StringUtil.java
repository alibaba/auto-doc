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

import com.alibaba.auto.doc.constants.SpecialCharacter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 2:50 下午
 * @description：
 */
public class StringUtil {

    /**
     * replace html tag
     *
     * @param input
     * @return
     */
    public static String replaceHtmlTag(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        return input;
    }

    /**
     * remove space
     *
     * @param input
     * @return
     */
    public static String removeSpace(String input) {
        while (input.startsWith(SpecialCharacter.SPACE)) {
            input = input.substring(1);
        }
        return input;
    }

    /**
     * remove colon
     *
     * @param input
     * @return
     */
    public static String removeColon(String input) {
        while (input.startsWith(SpecialCharacter.COLON) || input.startsWith(SpecialCharacter.COLON_CN)) {
            input = input.substring(1);
        }
        return input;
    }

    /**
     * first to lower case
     *
     * @param input
     * @return
     */
    public static String firstToLowerCase(String input) {
        if (StringUtils.isNotBlank(input)) {
            return input.substring(0, 1).toLowerCase() + input.substring(1);
        }
        return input;
    }

    /**
     * first to lower case
     *
     * @param input
     * @return
     */
    public static String firstToUpperCase(String input) {
        if (StringUtils.isNotBlank(input)) {
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        return input;
    }

    /**
     * remove quotes
     *
     * @param str
     * @return
     */
    public static String removeQuotes(String str) {
        return StringUtils.isNotBlank(str) ? str.replaceAll(SpecialCharacter.SINGLE_QUOTES, SpecialCharacter.BLANK).replaceAll(SpecialCharacter.DOUBLE_QUOTES, SpecialCharacter.BLANK)
            : SpecialCharacter.BLANK;
    }

}
