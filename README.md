# 1.支持的注解
为了保持对代码的零侵入，auto-doc自己没有任何注解，但支持一些Java Validation通用的注解，如：
```
@NotNull 定义在字段上，表示非空
@NotBlank 定义在字段上，表示非空
```
# 2.支持的JavaDoc标签
为了实现一些更高级的控制，auto-doc支持JavaDoc标签，标签定义在类或方法注释里面，如：
```
/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/31 2:22 下午
 * @description：用户信息接口
 */
```
下面是auto-doc特有的标签
```
@ignore 定义在方法或类上，生成文档时忽略
@required 定义在字段上，表示必填
```
下面是JavaDoc原生标签，auto-doc同样支持
```
@apiNote
@author
@param
@since
@return
@Deprecated
@description
```

# 3.一些约定
```
3.1如果一个方法通过@RequestMapping定义了多个url，auto-doc不建议这种做法，默认会取第一个
```