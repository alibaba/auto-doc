package com.alibaba.auto.doc.constants;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/31 2:22 下午
 * @description：
 */
public interface Constants {

    String METHOD_PREFIX_SET = "set";

    String METHOD_PREFIX_GET = "get";

    String MODIFIED_PUBLIC = "public";

    String DEFAULT_CHARSET = "utf-8";

    int REQUEST_BODY_PARAM_DEEP = 10;

    String FILE_SEPARATOR = System.getProperty("file.separator");

    String PROJECT_CODE_PATH = "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "java";

    String DOC_LANGUAGE = "doc_language";

    String API_HTML = "API.html";

    String API_CSS = "API.css";

    String API_MARKDOWN = "API.md";

    String JAVA_OBJECT_FULLY = "java.lang.Object";

    String JAVA_LIST_FULLY = "java.util.List";

    String NO_COMMENTS_FOUND = "No comments found.";

    String TEMPLATE_API_CLASS_LIST = "templateApiClassList";

}
