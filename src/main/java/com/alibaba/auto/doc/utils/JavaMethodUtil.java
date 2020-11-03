package com.alibaba.auto.doc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.auto.doc.constants.ContentType;
import com.alibaba.auto.doc.constants.JavaTag;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;
import com.alibaba.auto.doc.model.comment.NoCommentFound;
import com.alibaba.auto.doc.model.comment.NoMethodCommentFound;
import com.alibaba.auto.doc.model.comment.NoMethodTagCommentFound;

import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/17 6:53 下午
 * @description：
 */
public class JavaMethodUtil {

    private static final Logger log = LoggerFactory.getLogger(JavaMethodUtil.class);

    /**
     * get method return comment
     *
     * @param javaMethod
     * @return
     */
    public static String getMethodReturnComment(final JavaMethod javaMethod) {
        DocletTag tag = javaMethod.getTagByName(JavaTag.RETURN);
        if (tag != null) {
            return tag.getValue();
        }
        return SpecialCharacter.BLANK;
    }

    /**
     * get param comment map
     *
     * @param javaMethod
     * @param isStrict
     * @return
     */
    public static Map<String, String> getParamCommentMap(final JavaMethod javaMethod, boolean isStrict) {
        Map<String, String> paramMap = new HashMap<String, String>();
        List<DocletTag> tags = javaMethod.getTagsByName(JavaTag.PARAM);
        for (DocletTag tag : tags) {
            String pName = null;
            String pValue = null;
            String value = tag.getValue();
            int idx = value.indexOf(SpecialCharacter.NEW_LINE);
            if (idx > -1) {
                pName = value.substring(0, idx).trim();
                pValue = value.substring(idx + 1).trim();
            } else {
                pName = value.contains(SpecialCharacter.SPACE) ? value.substring(0, value.indexOf(SpecialCharacter.SPACE)).trim() : value.trim();
                pValue = value.contains(SpecialCharacter.SPACE) ? value.substring(value.indexOf(SpecialCharacter.SPACE) + 1).trim() : SpecialCharacter.BLANK;
            }
            if (StringUtils.isBlank(pValue)) {
                NoCommentFound noCommentFound = new NoMethodTagCommentFound(javaMethod.getDeclaringClass().getCanonicalName(), tag.getLineNumber(), javaMethod.getName(), tag.getName());
                log.warn(noCommentFound.toString());
                if (isStrict) {
                    throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, noCommentFound.toString());
                }
            }
            paramMap.put(pName, pValue);
        }
        return paramMap;
    }

    /**
     * get content type
     *
     * @param javaMethod
     * @return
     */
    public static String getContentType(final JavaMethod javaMethod) {
        for (JavaParameter parameter : javaMethod.getParameters()) {
            if (JavaAnnotationUtil.exist(parameter.getAnnotations(), SpringAnnotation.REQUEST_BODY)) {
                return ContentType.JSON_CONTENT_TYPE;
            }
        }
        return ContentType.FORM_CONTENT_TYPE;
    }

    /**
     * is method deprecated
     *
     * @param javaMethod
     * @return
     */
    public static boolean isDeprecated(final JavaMethod javaMethod) {
        for (JavaAnnotation annotation : javaMethod.getAnnotations()) {
            if (annotation.getType().getName().equals(JavaTag.DEPRECATED)) {
                return true;
            }
        }
        return false;
    }

    /**
     * build method id
     *
     * @param javaMethod
     * @return
     */
    public static String buildMethodId(final JavaMethod javaMethod) {
        String method = javaMethod.getDeclaringClass().getCanonicalName() + SpecialCharacter.COLON + javaMethod.getDeclarationSignature(true);
        return DigestUtils.md5Hex(method);
    }

    /**
     * check method comment
     *
     * @param javaMethod
     * @param isStrict
     */
    public static void checkMethodComment(final JavaMethod javaMethod, boolean isStrict) {
        if (StringUtils.isBlank(javaMethod.getComment())) {
            NoCommentFound noCommentFound = new NoMethodCommentFound(javaMethod.getDeclaringClass().getCanonicalName(), javaMethod.getLineNumber(), javaMethod.getName());
            log.warn(noCommentFound.toString());
            if (isStrict) {
                throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, noCommentFound.toString());
            }
        }
    }

    /**
     * check not api method
     *
     * @param javaMethod
     * @return
     */
    public static boolean notApiMethod(final JavaMethod javaMethod) {
        for (JavaAnnotation annotation : javaMethod.getAnnotations()) {
            String annotationName = annotation.getType().getName();
            if (SpringAnnotation.REQUEST_MAPPINGS.contains(annotationName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * get tag value of method
     *
     * @param javaMethod
     * @param tag
     * @return
     */
    public static String getTagValue(final JavaMethod javaMethod, final String tag) {
        for (DocletTag docletTag : javaMethod.getTagsByName(tag)) {
            if (docletTag.getName().equals(tag)) {
                String tagValue = docletTag.getValue();
                if (StringUtils.isNotBlank(tagValue)) {
                    tagValue = StringUtil.removeColon(tagValue);
                    tagValue = StringUtil.removeSpace(tagValue);
                }
                return tagValue;
            }
        }
        return null;
    }

    /**
     * get author
     *
     * @param javaMethod
     * @return
     */
    public static String getAuthor(final JavaMethod javaMethod) {
        return getTagValue(javaMethod, JavaTag.AUTHOR);
    }
}
