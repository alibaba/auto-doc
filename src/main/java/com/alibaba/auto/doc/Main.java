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
