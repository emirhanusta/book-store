package com.socialNetwork.ws.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUserName, String>{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		Users user = userRepository.findByUserName(userName);

		if(user!=null)
			return false;
		return true;
	}

}
