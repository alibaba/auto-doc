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
