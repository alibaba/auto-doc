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
 * @date ：Created in 2020/10/16 4:15 下午
 * @description：
 */
public class AutoDocException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public AutoDocException(String errorCode) {
        this(errorCode, "error with code: " + errorCode);
    }

    public AutoDocException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public AutoDocException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
