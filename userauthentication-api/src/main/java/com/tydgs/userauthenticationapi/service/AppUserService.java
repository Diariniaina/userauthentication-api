package com.tydgs.userauthenticationapi.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tydgs.userauthenticationapi.model.AppUser;
import com.tydgs.userauthenticationapi.model.ConfirmationToken;
import com.tydgs.userauthenticationapi.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder BCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)) );
	}
	
	public String signUpUser(AppUser appUser) {
	boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
	
	if (userExists) {
		throw new IllegalStateException("email already taken");
	}
	
	String encodedPassword = BCryptPasswordEncoder.encode(appUser.getPassword());
	appUser.setPassword(encodedPassword);
	
	appUserRepository.save(appUser);
	
	String token = UUID.randomUUID().toString();
	
	ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser
			);
	
	confirmationTokenService.saveConfirmationToken(confirmationToken);
		
	return token;
	}

}
