package br.com.moto.desafio.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.moto.desafio.model.Member;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> object) {
		return Member.class.isAssignableFrom(object);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "field.required");
		try {
			Member newMember = (Member) object;
			Long.parseLong(newMember.getPhoneNumber());
		} catch (Exception e) {
			errors.rejectValue("phoneNumber", "field.numeric");
		}
	}

}
