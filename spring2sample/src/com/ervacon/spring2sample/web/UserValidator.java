package com.ervacon.spring2sample.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ervacon.spring2sample.domain.User;

public class UserValidator implements Validator {

	public boolean supports(Class candidate) {
		return User.class.isAssignableFrom(candidate);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required", "Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required", "Field is required.");
	}
}
