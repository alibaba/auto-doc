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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/11/7 12:38 上午
 * @description：
 */
public class URLUtil {

    public static String removeUrlRegExp(String url) {
        String pattern = "(\\{\\w*:.*?[^0-9]\\})";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(url);

        while (m.find()) {
            String originalText = m.group(1);
            String paramName = SpecialCharacter.BRACE_LEFT + removeReg(originalText) + SpecialCharacter.BRACE_RIGHT;
            url = url.replace(m.group(1), paramName);
        }
        return url;
    }


    private static String removeReg(String originalText) {
        String reg = "\\{(\\w*):.*?\\}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(originalText);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return originalText;
    }
}
