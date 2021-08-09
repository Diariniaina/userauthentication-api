package com.tydgs.userauthenticationapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {
	
	@Id
	@SequenceGenerator(
			name = "confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "confirmation_token_sequence"
			)
	private Long idLong;
	
	@Column(nullable = false)
	private String tokenString;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "app_user_id"
			)
	private AppUser appUser;
	
	
	public ConfirmationToken(String tokenString, LocalDateTime createdAt, LocalDateTime expiresAt,
			 AppUser appUser) {
		super();
		this.tokenString = tokenString;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.appUser = appUser;
	}
	
	
}
