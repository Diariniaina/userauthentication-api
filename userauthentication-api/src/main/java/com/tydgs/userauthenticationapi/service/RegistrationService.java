package com.tydgs.userauthenticationapi.service;

import org.springframework.stereotype.Service;

import com.tydgs.userauthenticationapi.model.AppUser;
import com.tydgs.userauthenticationapi.model.AppUserRole;
import com.tydgs.userauthenticationapi.model.RegistrationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	private final AppUserService appUserService;
	private final EmailValidator emailValidator;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("email not valid");
		} 		
		return appUserService.signUpUser(
			new AppUser(
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPassword(),
				AppUserRole.USER
			)
		);
	}

}
