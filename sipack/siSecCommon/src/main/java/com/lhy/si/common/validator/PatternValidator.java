package com.lhy.si.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatternValidator implements ConstraintValidator<Pattern, Object> {

	private String regexp;

	@Override
	public void initialize(Pattern constraintAnnotation) {
		  this.regexp = constraintAnnotation.regexp();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		if (value.toString().matches(regexp)) {
			return true;
		}
		return false;
	}

}
