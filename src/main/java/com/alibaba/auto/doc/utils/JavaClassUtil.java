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
package com.alibaba.auto.doc.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.auto.doc.builder.ProjectBuilder;
import com.alibaba.auto.doc.constants.AutoDocTag;
import com.alibaba.auto.doc.constants.BasicJavaType;
import com.alibaba.auto.doc.constants.Constants;
import com.alibaba.auto.doc.constants.JavaTag;
import com.alibaba.auto.doc.constants.NonCustomPackage;
import com.alibaba.auto.doc.constants.SpecialCharacter;
import com.alibaba.auto.doc.constants.SpringAnnotation;
import com.alibaba.auto.doc.exception.AutoDocException;
import com.alibaba.auto.doc.exception.ErrorCodes;
import com.alibaba.auto.doc.model.comment.NoClassCommentFound;
import com.alibaba.auto.doc.model.comment.NoCommentFound;
import com.alibaba.auto.doc.model.request.CollectionType;
import com.alibaba.auto.doc.model.request.EnumType;

import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;
import com.thoughtworks.qdox.model.expression.Expression;
import com.thoughtworks.qdox.model.impl.DefaultJavaParameterizedType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle JavaClass
 *
 * @author 舲扬 2019/12/21.
 */
public class JavaClassUtil {

    private static final Logger log = LoggerFactory.getLogger(JavaClassUtil.class);


    public static CollectionType getCollectionType(final JavaClass javaClass) {
        CollectionType collectionType = new CollectionType();
        collectionType.setType(getGenericType(javaClass).getGenericValue());
        return collectionType;
    }

    /**
     * get EnumType
     * @param javaClass
     * @return
     */
    public static EnumType getEnumType(final JavaClass javaClass) {
        if(javaClass == null || !javaClass.isEnum()) {
            return null;
        }
        EnumType enumType = new EnumType();
        if(javaClass.getConstructors().size() == 1 && javaClass.getConstructors().get(0).getParameters().size() >= 1) {
            JavaParameter javaParameter = javaClass.getConstructors().get(0).getParameters().get(0);
            String type = javaParameter.getJavaClass().getGenericFullyQualifiedName();
            enumType.setType(type);

            List<Object> values = new ArrayList<>();
            for (JavaField field : javaClass.getFields()) {
                if(field.getModifiers().contains(Constants.MODIFIED_PUBLIC) && field.getType().getGenericFullyQualifiedName().equals(javaClass.getGenericFullyQualifiedName())) {
                    List<Expression> enumConstantArguments = field.getEnumConstantArguments();
                    Expression expression = enumConstantArguments.get(0);
                    Object parameterValue = expression.getParameterValue();
                    if(parameterValue instanceof String) {
                        values.add(StringUtil.removeQuotes(parameterValue.toString()));
                    } else {
                        values.add(parameterValue);
                    }

                }
            }
            enumType.setValues(values);
        } else {
            log.warn("can not parse enum {}", javaClass.getGenericCanonicalName());
        }
        return enumType;
    }

    /**
     * get generic type
     *
     * @param javaClass
     * @return
     */
    public static JavaClass getGenericType(final JavaClass javaClass) {
        if (javaClass instanceof DefaultJavaParameterizedType) {
            DefaultJavaParameterizedType defaultJavaParameterizedType = (DefaultJavaParameterizedType)javaClass;
            for (JavaType actualTypeArgument : defaultJavaParameterizedType.getActualTypeArguments()) {
                return ProjectBuilder.getJavaProjectBuilder().getClassByName(actualTypeArgument.getGenericFullyQualifiedName());
            }
        }
        return null;
    }

    /**
     * is basic type
     *
     * @param javaClass
     * @return
     */
    public static boolean isBasicType(final JavaClass javaClass, boolean checkSetMethod, boolean checkGetMethod) {
        JavaClass newJavaClass = javaClass;
        if (JavaClassUtil.isCollection(javaClass)) {
            newJavaClass = getCollectionGenericJavaClass(javaClass, null);
        }
        if (BasicJavaType.BASIC_TYPES.contains(newJavaClass.getCanonicalName())) {
            return true;
        }
        if (JavaFieldUtil.getFields(newJavaClass, null, checkSetMethod, checkGetMethod).size() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * get collection java class
     *
     * @param javaClass
     * @return
     */
    public static JavaClass getCollectionGenericJavaClass(final JavaClass javaClass, final JavaClass defaultJavaClass) {
        if (JavaClassUtil.isCollection(javaClass)) {
            if (javaClass instanceof DefaultJavaParameterizedType) {
                DefaultJavaParameterizedType defaultJavaParameterizedType = (DefaultJavaParameterizedType)javaClass;
                for (JavaType actualTypeArgument : defaultJavaParameterizedType.getActualTypeArguments()) {
                    JavaClass genericJavaClass = ProjectBuilder.getJavaProjectBuilder().getClassByName(actualTypeArgument.getCanonicalName());
                    if (isT(genericJavaClass)) {
                        if (defaultJavaClass != null) {
                            return defaultJavaClass;
                        }
                    }
                    return genericJavaClass;
                }
            }
        }
        return javaClass;
    }

    private static boolean isT(final JavaClass javaClass) {
        if (javaClass.getPackage() == null) {
            return true;
        }
        return false;
    }

    /**
     * is custom class
     *
     * @return
     */
    public static boolean isCustomClass(final JavaClass javaClass) {
        for (String nonCustomPackage : NonCustomPackage.NON_CUSTOM_PACKAGES) {
            if (javaClass.getPackageName().startsWith(nonCustomPackage)) {
                return false;
            }
            if (javaClass.isEnum()) {
                return false;
            }
        }
        return true;
    }

    /**
     * is collection
     *
     * @param javaType
     * @return
     */
    public static boolean isCollection(final JavaType javaType) {
        switch (javaType.getCanonicalName()) {
            case "java.util.List":
            case "java.util.LinkedList":
            case "java.util.ArrayList":
            case "java.util.Set":
            case "java.util.TreeSet":
            case "java.util.HashSet":
            case "java.util.SortedSet":
            case "java.util.Collection":
            case "java.util.ArrayDeque":
            case "java.util.PriorityQueue":
                return true;
            default:
                return false;
        }
    }

    /**
     * check class comment
     *
     * @param javaClass
     * @param isStrict
     */
    public static void checkClassComment(final JavaClass javaClass, boolean isStrict) {
        String comment = getClassComment(javaClass);
        if (StringUtils.isBlank(comment)) {
            NoCommentFound noCommentFound = new NoClassCommentFound(javaClass.getCanonicalName(), javaClass.getLineNumber());
            log.warn(noCommentFound.toString());
            if (isStrict) {
                throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, noCommentFound.toString());
            }
        }
    }

    /**
     * check class need handler
     *
     * @param javaClass
     * @return
     */
    public static boolean ignoreClass(final JavaClass javaClass) {
        if (hasIgnoreTag(javaClass)) {
            return true;
        }
        if (!isSpringController(javaClass)) {
            return true;
        }
        return false;
    }

    public static boolean isInclude(final JavaClass javaClass, String packageInclude, String classInclude, String classExclude) {
        String include = SpecialCharacter.BLANK;
        if (StringUtils.isNotBlank(packageInclude)) {
            include += packageInclude;
        }
        if (StringUtils.isNotBlank(classInclude)) {
            if (StringUtils.isNotBlank(include)) {
                include += SpecialCharacter.COMMA;
            }
            include += classInclude;
        }
        if (StringUtils.isNotBlank(include)) {
            if (isInclude(include, javaClass.getGenericFullyQualifiedName())) {
                if (!isInclude(classExclude, javaClass.getGenericFullyQualifiedName())) {
                    return true;
                }
            }
        } else {
            if (!isInclude(classExclude, javaClass.getGenericFullyQualifiedName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInclude(String include, String classFullName) {
        if (StringUtils.isNotBlank(include)) {
            for (String cls : include.split(SpecialCharacter.COMMA)) {
                if (classFullName.startsWith(cls) || classFullName.endsWith(cls)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSpringController(JavaClass javaClass) {
        return JavaAnnotationUtil.isMatch(javaClass.getAnnotations(),
            SpringAnnotation.CONTROLLER,
            SpringAnnotation.CONTROLLER_FULLY,
            SpringAnnotation.REST_CONTROLLER,
            SpringAnnotation.REST_CONTROLLER_FULLY,
            SpringAnnotation.COSMO_CONTROLLER,
            SpringAnnotation.COSMO_CONTROLLER_FULLY);
    }

    /**
     * check class has ignore tag
     *
     * @param javaClass
     * @return
     */
    public static boolean hasIgnoreTag(final JavaClass javaClass) {
        String classTagsValue = getTagValue(javaClass, AutoDocTag.IGNORE, Boolean.FALSE);
        return classTagsValue != null;
    }

    /**
     * get class author
     *
     * @param javaClass
     * @return
     */
    public static String getClassAuthor(final JavaClass javaClass) {
        return getTagValue(javaClass, JavaTag.AUTHOR, Boolean.FALSE);
    }

    /**
     * get class comment, if blank replace with @description
     * @param javaClass
     * @return
     */
    public static String getClassComment(final JavaClass javaClass) {
        String comment = javaClass.getComment();
        if(StringUtils.isBlank(comment)) {
            List<DocletTag> tags = javaClass.getTags();
            for (DocletTag tag : tags) {
                if(tag.getName().startsWith(JavaTag.DESCRIPTION)) {
                    String description = tag.getName().substring(JavaTag.DESCRIPTION.length());
                    description = StringUtil.removeColon(description);
                    description = StringUtil.removeSpace(description);
                    comment = description;
                }
            }
        }
        if(StringUtils.isBlank(comment)) {
            return SpecialCharacter.BLANK;
        }
        return comment;
    }

    /**
     * 通过name获取类标签的value
     *
     * @param javaClass     类
     * @param tagName       需要获取的标签name
     * @param checkComments 检查注释
     * @return 类标签的value
     */
    public static String getTagValue(final JavaClass javaClass, final String tagName, boolean checkComments) {
        List<DocletTag> tags = javaClass.getTagsByName(tagName);
        for (int i = 0; i < tags.size(); i++) {
            if (tagName.equals(tags.get(i).getName())) {
                String value = tags.get(i).getValue();
                if (StringUtils.isNotBlank(value)) {
                    value = StringUtil.removeColon(value);
                    value = StringUtil.removeSpace(value);
                }
                if (StringUtils.isBlank(value) && checkComments) {
                    throw new AutoDocException(ErrorCodes.AD_MISSING_COMMENT, javaClass.getGenericFullyQualifiedName() + " missing comment for @" + tagName);
                }
                return value;
            }
        }
        return null;
    }
}
