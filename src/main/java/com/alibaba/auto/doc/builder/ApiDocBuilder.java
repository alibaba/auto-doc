package com.alibaba.auto.doc.builder;

import java.util.List;

import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.model.ApiClass;
import com.alibaba.auto.doc.model.ApiConfig;
import com.alibaba.auto.doc.model.template.TemplateApiClass;
import com.alibaba.auto.doc.template.BeetlTemplateUtil;
import com.alibaba.auto.doc.utils.FileUtil;

import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/28 9:52 下午
 * @description：
 */
public class ApiDocBuilder {

    private static final Logger log = LoggerFactory.getLogger(ApiDocBuilder.class);

    /**
     * build for html
     *
     * @param apiConfig
     */
    public static void build(ApiConfig apiConfig) {
        long start = System.currentTimeMillis();
        ProjectBuilder.init(apiConfig);
        List<ApiClass> apiClassList = ProjectBuilder.build();

        List<TemplateApiClass> templateApiClassList = TemplateApiClassBuilder.build(apiClassList);

        // 1.html
        Template cssTemplate = BeetlTemplateUtil.getByName(Constants.API_CSS);
        FileUtil.nioWriteFile(cssTemplate.render(), apiConfig.getOutPath() + Constants.FILE_SEPARATOR + Constants.API_CSS);
        Template htmlTemplate = BeetlTemplateUtil.getByName(Constants.API_HTML);
        htmlTemplate.binding(Constants.TEMPLATE_API_CLASS_LIST, templateApiClassList);
        FileUtil.nioWriteFile(htmlTemplate.render(), apiConfig.getOutPath() + Constants.FILE_SEPARATOR + Constants.API_HTML);

        // 2.markdown
        Template markdownTemplate = BeetlTemplateUtil.getByName(Constants.API_MARKDOWN);
        markdownTemplate.binding(Constants.TEMPLATE_API_CLASS_LIST, templateApiClassList);
        FileUtil.nioWriteFile(markdownTemplate.render(), apiConfig.getOutPath() + Constants.FILE_SEPARATOR + Constants.API_MARKDOWN);

        // 3.postman jsons

        long end = System.currentTimeMillis();
        log.info("------------------------------------------------------------------------");
        log.info("Auto-Doc Build Success");
        log.info("Total time : {}s", (end - start) / 1000);
        log.info("------------------------------------------------------------------------");
    }

}
