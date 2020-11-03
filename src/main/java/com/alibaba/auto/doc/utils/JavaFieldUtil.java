package com.alibaba.auto.doc.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.constants.JavaTag;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.ValidateAnnotations;

import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/17 6:53 下午
 * @description：
 */
public class JavaFieldUtil {

    /**
     * get class field with set and get
     *
     * @param javaClass
     * @param setMethodList
     * @return
     */
    public static List<JavaField> getFields(final JavaClass javaClass, List<String> setMethodList, boolean checkSetMethod, boolean checkGetMethod) {
        List<JavaField> fieldList = new ArrayList<>();
        if (setMethodList == null) {
            setMethodList = new ArrayList<String>();
        }
        if (javaClass.getFields() == null || javaClass.getFields().size() < 1) {
            return fieldList;
        }
        for (JavaMethod method : javaClass.getMethods()) {
            if (method.getModifiers().contains(Constants.MODIFIED_PUBLIC)) {
                if (method.getName().startsWith(Constants.METHOD_PREFIX_SET)) {
                    if (method.getParameters().size() == 1) {
                        setMethodList.add(method.getName());
                    }
                }
                if (method.getName().startsWith(Constants.METHOD_PREFIX_GET)) {
                    if (method.getParameters().size() == 0) {
                        setMethodList.add(method.getName());
                    }
                }
            }
        }

        fieldList.addAll(fieldExclude(javaClass.getFields(), setMethodList, checkSetMethod, checkGetMethod));
        if (JavaClassUtil.isCustomClass(javaClass.getSuperJavaClass())) {
            fieldList.addAll(getFields(javaClass.getSuperJavaClass(), setMethodList, checkSetMethod, checkGetMethod));
        }
        return fieldList;
    }

    /**
     * field exclude
     *
     * @param fieldList
     * @param setMethodList
     * @return
     */
    private static List<JavaField> fieldExclude(List<JavaField> fieldList, List<String> setMethodList, boolean checkSetMethod, boolean checkGetMethod) {
        List<JavaField> list = new LinkedList<>();
        for (JavaField field : fieldList) {
            if (!(field.isFinal() || field.isStatic())) {
                if (checkSetMethod) {
                    String setMethodName = Constants.METHOD_PREFIX_SET + StringUtil.firstToUpperCase(field.getName());
                    if (setMethodList.contains(setMethodName)) {
                        list.add(field);
                    }
                }
                if (checkGetMethod) {
                    String getMethodName = Constants.METHOD_PREFIX_GET + StringUtil.firstToUpperCase(field.getName());
                    if (setMethodList.contains(getMethodName)) {
                        list.add(field);
                    }
                }

            }
        }
        return list;
    }

    /**
     * get since tag value
     *
     * @param javaField
     * @return
     */
    public static String getSince(final JavaField javaField) {
        DocletTag tag = javaField.getTagByName(JavaTag.SINCE);
        if (tag != null && StringUtils.isNotBlank(tag.getValue())) {
            return tag.getValue().trim();
        }
        return SpecialCharacter.BLANK;
    }

    /**
     * check field is required
     *
     * @param javaField
     * @return
     */
    public static Boolean isRequired(final JavaField javaField) {
        return JavaAnnotationUtil.isMatch(javaField.getAnnotations(), ValidateAnnotations.NOT_NULL, ValidateAnnotations.NOT_BLANK) ? true : null;
    }
}
