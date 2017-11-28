package com.lhy.si.common.validator;

import java.lang.annotation.Documented;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
* @ClassName: Pattern
* @Description: 自定义正则表达式注解 支持非字符串校验
* @author  hyluan
* @date 2017年7月20日 下午2:04:39
* @Copyright: Copyright (c) 2017 wisedu
 */
@Constraint(validatedBy = PatternValidator.class) // 具体的实现
@Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface Pattern {

	String message() default "非法内容";

	/**
	 * @return the regular expression to match
	 */
	String regexp();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link Size} annotations on the same element.
	 *
	 * @see Size
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Pattern[] value();
	}
}
