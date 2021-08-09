package com.tydgs.userauthenticationapi.service;

import org.springframework.stereotype.Service;

import com.tydgs.userauthenticationapi.model.ConfirmationToken;
import com.tydgs.userauthenticationapi.repository.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
		
	}

}
