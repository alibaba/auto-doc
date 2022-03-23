package com.alibaba.auto.doc.utils;

import java.util.*;

import com.alibaba.auto.doc.constants.*;

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
     * @param setGetMethods
     * @return
     */
    public static List<JavaField> getFields(final JavaClass javaClass, Set<String> setGetMethods, boolean checkSetMethod, boolean checkGetMethod) {
        List<JavaField> fieldList = new ArrayList<>();
        if (setGetMethods == null) {
            setGetMethods = new HashSet<String>();
        }
        for (JavaMethod method : javaClass.getMethods()) {
            if (method.getModifiers().contains(Constants.MODIFIED_PUBLIC)) {
                if (method.getName().startsWith(Constants.METHOD_PREFIX_SET)) {
                    if (method.getParameters().size() == 1) {
                        setGetMethods.add(method.getName());
                    }
                }
                if (method.getName().startsWith(Constants.METHOD_PREFIX_GET)) {
                    if (method.getParameters().size() == 0) {
                        setGetMethods.add(method.getName());
                    }
                }
            }
        }

        // 获取有效属性（非静态、有set/get方法）
        fieldList.addAll(getRealParamField(javaClass.getFields(), setGetMethods, checkSetMethod, checkGetMethod));

        // 获取父类属性
        JavaClass superJavaClass = javaClass.getSuperJavaClass();
        if (superJavaClass != null && JavaClassUtil.isCustomClass(superJavaClass)) {
            fieldList.addAll(getFields(javaClass.getSuperJavaClass(), setGetMethods, checkSetMethod, checkGetMethod));
        }
        return fieldList;
    }

    /**
     * field exclude
     *
     * @param fieldList
     * @param setGetMethods
     * @return
     */
    private static List<JavaField> getRealParamField(List<JavaField> fieldList, Set<String> setGetMethods, boolean checkSetMethod, boolean checkGetMethod) {
        List<JavaField> list = new LinkedList<>();
        for (JavaField field : fieldList) {
            if (!(field.isFinal() || field.isStatic())) {
                if (checkSetMethod) {
                    String setMethodName = Constants.METHOD_PREFIX_SET + StringUtil.firstToUpperCase(field.getName());
                    if (setGetMethods.contains(setMethodName)) {
                        list.add(field);
                    }
                }
                if (checkGetMethod) {
                    String getMethodName = Constants.METHOD_PREFIX_GET + StringUtil.firstToUpperCase(field.getName());
                    if (setGetMethods.contains(getMethodName)) {
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
        return (javaField.getTagByName(AutoDocTag.REQUIRED) != null || JavaAnnotationUtil.isMatch(javaField.getAnnotations(), ValidateAnnotations.NOT_NULL, ValidateAnnotations.NOT_BLANK)) ? true : null;
    }
}
