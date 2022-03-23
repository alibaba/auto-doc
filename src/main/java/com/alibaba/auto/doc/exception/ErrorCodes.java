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
package com.alibaba.auto.doc.exception;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/16 4:40 下午
 * @description：
 */
public interface ErrorCodes {

    String AD_CONFIG_IS_NULL = "AD_API_CONFIG_IS_NULL";
    String AD_CONFIG_SOURCE_PATH_ILLEGAL = "AD_CONFIG_SOURCE_PATH_ILLEGAL";
    String AD_CONFIG_OUT_PATH_IS_EMPTY = "AD_CONFIG_OUT_PATH_IS_EMPTY";

    String AD_MISSING_COMMENT = "AD_MISSING_COMMENT";

    String AD_TWO_SLASHES_COMMENT_INLINE_WITH_SOURCE_CODE = "AD_TWO_SLASHES_COMMENT_INLINE_WITH_SOURCE_CODE";

    String AD_NO_BEETL_TEMPLATE_FOUND = "AD_NO_BEETL_TEMPLATE_FOUND";
}
