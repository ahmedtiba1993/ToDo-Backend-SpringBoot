package com.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer>{

	Optional<PasswordResetToken> findByToken (String resetToken);
	
	Optional<PasswordResetToken> findByUtilisateurId(Integer id);


}
