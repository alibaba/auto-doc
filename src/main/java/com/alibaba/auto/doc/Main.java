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
package com.alibaba.auto.doc;

import com.alibaba.auto.doc.builder.ApiDocBuilder;
import com.alibaba.auto.doc.config.ApiConfig;
import com.alibaba.auto.doc.model.ApiClass;

import java.util.List;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/16 12:07 下午
 * @description：
 */
public class Main {

    public static void main(String[] args) {
        ApiConfig config = new ApiConfig();
        config.setSrcPath("/Users/yangfan/code/dataq/dataq-adam");
        config.setOutPath("/Users/yangfan/Desktop/docTest");
        List<ApiClass> build = ApiDocBuilder.build(config);
    }
}
