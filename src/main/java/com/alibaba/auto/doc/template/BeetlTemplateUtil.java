package com.alibaba.auto.doc.template;

import java.io.IOException;

import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/30 11:48 上午
 * @description：
 */
public class BeetlTemplateUtil {

    /**
     * get beetl template
     * @param templateName
     * @return
     */
    public static Template getByName(String templateName) {
        try {
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("/template/");
            Configuration cfg = Configuration.defaultConfiguration();
            cfg.add("/beetl.properties");
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            return gt.getTemplate(templateName);
        } catch (IOException e) {
            throw new AutoDocException(ErrorCodes.AD_NO_BEETL_TEMPLATE_FOUND, "Can't get Beetl template.");
        }
    }

}
